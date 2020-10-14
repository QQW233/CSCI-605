'''
File: genetester.py
Description: This program tests the implementation of DNAList data structure. It contains 24 different test cases,
each with a documentation specifying what is being tested.

Author: Steve Gao, sg2369@rit.edu
Author: Qiwen Quan, qq5575@rit.edu
'''


from dnalist import DNAList

# Number of the test cases constructed.
TESTCASE_COUNT = 0

# Compare the expected output with the actual output to see if there is a difference.
# Result of the comparison will be printed.
# :param string1: The expected output.
# :param string2: The actual output.
def test(string1, string2):
    global TESTCASE_COUNT
    TESTCASE_COUNT += 1
    if string1 == string2:
        print("Test case " + str(TESTCASE_COUNT) + " passed.")
    else:
        print("Test case " + str(TESTCASE_COUNT) + " failed. Expected: " + str(string1) + " . Actual: " + str(string2) + '.')

print("Begin tests...")

# Test basic DNAList construction and __str__ function
print("Testing basic DNAList construction:")

# Test normal list construction
# Test case 1
list1 = DNAList('GCCATT')
test('GCCATT', str(list1))

# Test case 2
list2 = DNAList('ATCGACG')
test('ATCGACG', str(list2))

# Test empty list construction
# Test case 3
list_empty = DNAList()
test('', str(list_empty))


# Test append function
print("Testing append function:")

# Test appending legal item
# Test case 4
list1.append('G')
test('GCCATTG', str(list1))

# Test case 5
list1.append('A')
test('GCCATTGA', str(list1))

# Test appending illegal item
# Test case 6
list1.append('')
test('GCCATTGA', str(list1))

# Test case 7
list1.append('1')
test('GCCATTGA', str(list1))


# Test join function
print("Testing join function:")

# Test join two non-empty list
# Test case 8
temp1, temp2 = DNAList('ACGT'), DNAList('GTCA')
temp1.join(temp2)
test('ACGTGTCA', str(temp1))

# Test join one empty list with a non-empty list
# Test case 9
temp1, temp2 = DNAList(''), DNAList('ACGT')
temp1.join(temp2)
test('ACGT', str(temp1))

# Test case 10
temp1, temp2 = DNAList('ACGT'), DNAList('')
temp1.join(temp2)
test('ACGT', str(temp1))


# Test splice function
print("Testing splice function:")

# Test splice at the front
# Test case 11
temp1, temp2 = DNAList('ACGT'), DNAList('GTCA')
temp1.splice(0, temp2)
test('GTCAACGT', str(temp1))

# Test splice in the middle
# Test case 12
temp1, temp2 = DNAList('ACGT'), DNAList('GTCA')
temp1.splice(1, temp2)
test('AGTCACGT', str(temp1))

# Test splice at the end
# Test case 13
temp1, temp2 = DNAList('ACGT'), DNAList('GTCA')
temp1.splice(4, temp2)
test('ACGTGTCA', str(temp1))


# Test snip function
print("Testing snip function:")

# Test snip middle part
# Test case 14
temp1 = DNAList('ACGTGA')
temp1.snip(1, 2)
test('AGTGA', str(temp1))

# Test snip front part
# Test case 15
temp1 = DNAList('ACGTGA')
temp1.snip(0, 2)
test('GTGA', str(temp1))

# Test snip end part
# Note: if the end index is out of bound, all items from the 
# begining index until the end of the DNAList will be deleted.
# Test case 16
temp1 = DNAList('ACGTGA')
temp1.snip(2, 10)
test('AC', str(temp1))

# Test replace function
print("Testing replace function:")

# Test case 17
temp1, repstr, temp2 = DNAList(''), 'ACG', DNAList('GTC')
test(False, temp1.replace(repstr, temp2))

# Test case 18
temp1, repstr, temp2 = DNAList('TCACGAA'), 'ACG', DNAList('GTC')
temp1.replace(repstr, temp2)
test('TCGTCAA', str(temp1))

# Test case 19
temp1, repstr, temp2 = DNAList('ATCGGAC'), 'ACG', DNAList('GTC')
test(False, temp1.replace(repstr, temp2))

# Test case 20
temp1, repstr, temp2 = DNAList('TCAACG'), 'ACG', DNAList('GTC')
temp1.replace(repstr, temp2)
test('TCAGTC', str(temp1))

# Test copy function
print("Testing copy function:")

# Test case 21
temp1 = DNAList('ACTG')
temp2 = temp1.copy()
test('ACTG', str(temp2))
# Check if it is a value copy or a reference copy
# Test case 22
temp1.append('G')
test('ACTG', str(temp2))


# Comprehensive test
print("Comprehensive test:")

# Test case 23
temp1 = DNAList('')
temp1.append('A')
temp1.append('G')
temp1.splice(0, DNAList('CGTA'))
temp1.join(DNAList('TTA'))
temp1.replace('TTA', DNAList('ACG'))
temp1.snip(0,2)
test('TAAGACG', str(temp1))

# Focus on testing the tail pointer
# Test case 24
temp1 = DNAList('')
temp1.join(DNAList('TCGA'))
temp1.join(DNAList('ACCG'))
temp1.snip(6,10)
temp1.append('A')
temp1.join(DNAList('TG'))
temp1.replace("ATG", DNAList('A'))
temp1.splice(6, DNAList('CC'))
test('TCGAACCCA', str(temp1))