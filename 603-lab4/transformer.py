'''
File: transformer.py
Description: This program performs encryption and decryption of a text file according to specific rules.
It takes four command line inputs from user in the form of <messageFile instructionFile outputFile e|d> and performs the actions accordingly.
It reads original file from messageFile, and encrypt/decrypt it regarding option.
The result will be stored in outputFile as well as displayed in the console as standard output.

Notice: Apart from the encryption methods from the assignment paper, an extra method, matchLetter is implemented.
matchLetter uses a randomly generated code book to match the original letter to a new letter and vice versa.

Author: Steve Gao, sg2369@rit.edu
Author: Qiwen Quan, qq5575@rit.edu
'''

import sys
import random

# Set the seed to generate the code book for letter matching. This seed has to be the same in order for the encrypted message to be decrypted correctly.
random.seed(26)

# Generate the code book randomly
# This function is used to build the matchLetter function
# :return: the code book generated
def generateCodeBook():
    nums = []
    for i in range(26):
        nums.append(i)
    random.shuffle(nums)
    code_book = {}
    # Assign each letter a new randomly selected letter
    for i in range(26):
        code_book[chr(ord('A') + i)] = chr(ord('A') + nums[i])
    return code_book

code_book = generateCodeBook()

# Return the original letter using the new, encrypted letter.
# This function is used to build the matchLetter function
# :param c: the new letter that needs decryption
# :return: the decrypted letter
def return_key(c):
    for key, value in code_book.items():
        if value == c:
            return key


# Matches a letter to a new letter as encryption. Matching process is done using the code book.
# :param message: the message that needs encryption/decryption
# :param index: position in the list of the letter to be encrypted/decrypted
# :param option: 'e'/'d', encryption/decryption
# :return: processed message
def matchLetter(message, index, option):
    if option == 'e':
        return message[:index] + code_book[message[index]] + message[index+1:]
    else:
        return message[:index] + return_key(message[index]) + message[index+1:]


# Shifts a specific letter forward/backward by specific amount of letters in the alphabet
# :param message: the message that needs encryption/decryption
# :param index: position in the list of the letter to be encrypted/decrypted
# :param length: amount of letters to forward/backward in the alphabet
# :param option: 'e'/'d', encryption/decryption
# :return: processed message
def shiftLetter(message, index, length, option):
    # times the length with -1 for decryption
    if option == 'd':
        length = -1 * length
    while length < 0:
        length += 26
    return message[:index] + chr(ord('A') + ((ord(message[index]) - ord('A') + length) % 26)) + message[index+1:]


# Rotate the message by a specific amount of positions to the right/left
# :param message: the message that needs encryption/decryption
# :param length: amount of positions to rotate forward/backward
# :param option: 'e'/'d', encryption/decryption
# :return: processed message
def rotate(message, length, option):
    # times the length with -1 for decryption
    if option == 'd':
        length = -1 * length
    # Convert rotation to the left to the equivalent rotation to the right for simplicity
    while length < 0:
        length += len(message)
    length = length % len(message)
    return message[len(message)-length:] + message[:len(message)-length]


# Duplicate (in place) the letter at a specific position by a specific times.
# :param message: the message that needs encryption/decryption
# :param index: position of the letter that needs encryption/decryption
# :param length: amount of times that a specifc letter needs to be duplicated(for encryption)/deleted(for decryption)
# :param option: 'e'/'d', encryption/decryption
# :return: processed message
def duplicate(message, index, length, option):
    # Only allow positive exponent
    if length <= 0:
        raise ValueError("Non-positive exponent is illegal in duplicate operation.")
    # duplicate for encryption, delete for decryption
    if option == 'e':
        for i in range(length):
            message = message[:index] + message[index] + message[index:]
        return message
    else:
        for i in range(length):
            message = message[:index] + message[index+1:]
        return message


# swap the letters at two specific positions
# :param message: the message that needs encryption/decryption
# :param index1: position of the first letter
# :param index2: position of the second letter
# :return: processed message
def swap(message, index1, index2):
    # same operation for encyption and decryption
    return message[:index1] + message[index2] + message[index1+1:index2] + message[index1] + message[index2+1:]


# Helper function to facilitate the transformation. It takes one instruction for one message at a time.
# :param message: the message that needs encryption/decryption
# :param instruction: the single instruction to be processed
# :param option: 'e'/'d', encryption/decryption
# :return: processed message
def transformHelper(message, instruction, option):
    # Shift
    if instruction[0] == 'S':
        splits = instruction[1:].split(',')
        if len(splits) == 1:
            return shiftLetter(message, int(splits[0]), 1, option)
        else:
            return shiftLetter(message, int(splits[0]), int(splits[1]), option)
    # Rotate
    elif instruction[0] == 'R':
        if len(instruction) == 1:
            return rotate(message, 1, option)
        else:
            return rotate(message, int(instruction[1:]), option)
    # Duplicate
    elif instruction[0] == 'D':
        splits = instruction[1:].split(',')
        if len(splits) == 1:
            return duplicate(message, int(splits[0]), 1, option)
        else:
            return duplicate(message, int(splits[0]), int(splits[1]), option)
    # Swap
    elif instruction[0] == 'T':
        if instruction[1] != '(':
            splits = instruction[1:].split(',')
            return swap(message, int(splits[0]), int(splits[1]))
        # Swap by group
        else:
            parse = False
            temp = ''
            for i in instruction:
                if i == ')':
                    parse = False
                if parse:
                    temp += i
                if i == '(':
                    parse = True
            group_size = int(len(message) // int(temp))
            indexes = instruction.split(')')[1].split(",")
            index1 = int(indexes[0])
            index2 = int(indexes[1])
            for i in range(group_size):
                message = swap(message, index1 * group_size + i, index2 * group_size + i)
            return message
    # Matching
    elif instruction[0] == 'M':
        return matchLetter(message, int(instruction[1:]), option)
    else:
        raise ValueError("Unrecognized instruction")
                

# Collect all the messages and their corresponding instructions, then assign the tasks to the transformHelper function one by one. 
# Display the result as standard input in the console as well as write it to the ouput file.
# :param messages: all the messages that need encryption/decryption
# :param instructions: lines of constructions for each message
# :param fout: path of the output file. Result will be written to this file.
# :param option: 'e'/'d', encryption/decryption
def transform(messages, instructions, fout, option):
    count = 0
    result = ""
    # if there is a dismatch between number of messages and number of instructions, the program will continue normally but stops
    # whenever we run out of messages/instructions
    if len(messages) != len(instructions):
        print("Warning, number of messages and number of instructions are different.")
    for count in range(min(len(messages), len(instructions))):
        message = messages[count]
        instruction = instructions[count].split(';')
        count += 1
        if option == 'e':
            # process the instruction one by one
            for i in instruction:
                message = transformHelper(message, i, option)
        else:
            # reverse the instructions for decrption
            for i in reversed(instruction):
                message = transformHelper(message, i, option)
        result += message + '\n'
    result = result[:-1]
    fout.write(result)
    print("Successfully processed " + str(count) + " lines!")
    print(result)
    fout.close()


# Main function. It checks whether user inputs are valid.
# If valid, calls transform function to start the encryption/decryption process.
if __name__ == "__main__":
    if(len(sys.argv) != 5):
        print("Usage: python3 transformer.py message instruction output e/d")
    else:
        inputFile = sys.argv[1]
        instruction = sys.argv[2]
        outputFile = sys.argv[3]
        option = sys.argv[4]
        try:
            fm = open(inputFile, 'r')
            messages = fm.read().split('\n')
            fm.close()
            fins = open(instruction, 'r')
            instructions = fins.read().split('\n')
            fins.close()
            fout = open(outputFile, 'w')
            if option != 'e' and option != 'd':
                raise ValueError("Option must be either e or d.")
            # if the program can reach here, all inputs are valid and we can proceed
            transform(messages, instructions, fout, option)
        except Exception as e:
            print("Invalid input. " + str(e))

