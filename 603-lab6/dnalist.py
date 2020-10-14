'''
File: dnalist.py
Description: This file contains two classes used to simulate the DNA strand. 
Node class is used to represent one item of the DNA strand and DNAList class is a 
linked nodes which consist of multiple Node objects and represent the DNA strand.

Author: Steve Gao, sg2369@rit.edu
Author: Qiwen Quan, qq5575@rit.edu
'''

'''
Represents one item of the DNA strand. It contains two fields which are the value
of this Node and a pointer pointing to the next node linked.
'''
class Node:

    __slots__ = 'val', 'next'

    # Constructor for the Node class. Initializes the fields.
    def __init__(self, val, next = None):
        self.val = val
        self.next = next


'''
Represents a strand of DNA. It contains multiple Nodes which represents an item of DNA strand.
This class stores information of a DNA strand and supports relevant actions.
'''
class DNAList:
    
    # pointer to the head of the list and pointer to the end of the list
    __slots__ = 'head', 'tail'

    # Constructor for the DNAList class. This function will initialize the field
    # and append the given gene items to the list.
    # :param gene: gene items to be appended to the list, represented in a string
    def __init__(self, gene=''):
        self.head = None
        self.tail = None
        for i in range(len(gene)):
            self.append(gene[i])

    # Append one given item to the DNAList. Item will be added to the end of the DNAList
    # :param item: New gene item to add to the list. Represented as a char
    def append(self, item):
        if item != 'A' and item != 'T' and item != 'C' and item != 'G':
            return
        item = Node(item)
        if self.head is None:
            self.head = item
            self.tail = item
        else:
            self.tail.next = item
            self.tail = self.tail.next
    
    # Joins another DNAList to the end of this DNAList
    # :param other: The other DNAList to be appended to the end of this list
    def join(self, other):
        # Checks if this list is empty
        if self.head is None:
            self.head = other.head
            self.tail = other.tail
        else:
            self.tail.next = other.head
            self.tail = other.tail

    # Append another DNAList at a specific location of this list
    # :param ind: the position of the Node in this list after which the other DNAList will be appended.
    #             The first Node in this list has a position 0.
    # :param other: The other DNAList to be appened to this DNAList
    def splice(self, ind, other):
        if ind < 0:
            return
        if ind == 0:
            other.tail.next = self.head
            self.head = other.head
            return
        counter = 1
        temp = self.head
        while counter < ind:
            counter += 1
            temp = temp.next
            if temp.next is None and counter < ind:
                return 
        other.tail.next = temp.next
        if temp.next is None:
            self.tail = other.tail
        temp.next = other.head

    # Delete part of this DNAList
    # :param i1: The starting position of the part of DNAList to be deleted (start from 0, inclusive)
    # :param i2: The ending position of the part of DNAList to be deleted (exclusive) 
    def snip(self, i1, i2):
        counter = 0
        temp = self.head
        while(counter < i1 - 1):
            counter += 1
            temp = temp.next
        temp2 = temp
        while(counter < i2):
            counter += 1
            temp2 = temp2.next
            if temp2 is None:
                break
        temp.next = temp2
        if i1 == 0:
            self.head = temp2
        if temp2 is None:
            self.tail = temp

    # Replace the first appearance of a specified pattern in this DNAList with another DNAList
    # If this pattern cannot be found in this DNAList, this DNAList will not be modified.
    # :param repstr: The pattern to be replaced.
    # :param other: The DNAList used to replace the specified pattern.
    # :return: A boolean specifying whether a pattern has been found and replaced within this DNAList
    def replace(self, repstr, other):
        if len(repstr) == 0:
            return False
        temp = self.head
        first = True
        prev = None
        # Traverse the entire DNAList until there is a pattern match
        while(temp is not None):
            temp2 = temp
            found = True
            # Check if there is a pattern match using the current node as the starting point
            for i in range(len(repstr)):
                if temp2 is None:
                    return False
                if temp2.val == repstr[i]:
                    temp2 = temp2.next
                else:
                    found = False
                    break
            if found:
                if first:
                    self.head = other.head
                    other.tail.next = temp2
                else:
                    prev.next = other.head
                    other.tail.next = temp2
                if temp2 is None:
                    self.tail = other.tail
                return True
            first = False
            prev = temp
            temp = temp.next
        return False


    # Copy the current DNAList
    # :return: A replica of the current DNAList
    def copy(self):
        return DNAList(str(self))

    # Print the current DNAList
    def __str__(self):
        temp = self.head
        result = ''
        while(temp is not None):
            result += temp.val
            temp = temp.next
        return result
