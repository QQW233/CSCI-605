""" 
File: tests.py
Description: Verify the LinkedHashTable class implementation. It contains 4 test cases
each testing different functions of the LinkedHashTable implementation.

Author: Steve Gao, sg2369@rit.edu
Author: Qiwen Quan, qq5575@rit.edu
"""

from linkedhashtable import LinkedHashTable

def print_set( a_set ):
    '''
    Prints a set.
    '''
    for word in a_set: # uses the iter method
        print( word, end=" " )
    print()

def test0():
    '''
    Tests the basic add and remove key function.
    '''
    table = LinkedHashTable( 100 )
    table.add( "to" )
    table.add( "do" )
    table.add( "is" )
    table.add( "to" )
    table.add( "be" )

    print_set( table )

    print( "'to' in table?", table.contains( "to" ) )
    table.remove( "to" )
    print( "'to' in table?", table.contains( "to" ) )

    print("Test case 0:\nExpected: do is be ")
    print("Actual: ", end = '')
    print_set( table )


def test1():
    '''
    Tests the corner case that all keys in the LinkedHashTable are removed 
    and then insert new keys. 
    '''

    table = LinkedHashTable(10)

    table.add('test')
    table.add('case')
    table.remove('test')
    table.remove('case')
    table.add('update')

    print("Test case 1:\nExpected: update ")
    print("Actual: ", end = '')
    print_set( table )

def test2():
    '''
    Tests if it works correctly when a table size extension and rehashing is needed.
    '''
    table = LinkedHashTable(10)

    for i in range(15):
        table.add(chr(ord('a') + i))

    print("Test case 2:\nExpected: a b c d e f g h i j k l m n o ")
    print("Actual: ", end = '')
    print_set(table)

def test3():
    '''
    Tests if it works correctly when a table size reduction and rehashing is needed.
    '''
    table = LinkedHashTable(10)

    for i in range(15):
        table.add(chr(ord('a') + i))

    for i in range(14, 1, -1):
        table.remove(chr(ord('a') + i))

    print("Test case 3:\nExpected: a b ")
    print("Actual: ", end = '')
    print_set(table)


if __name__ == '__main__':
    test0()
    test1()
    test2()
    test3()

