'''
File: linkedhashtable.py
Description: Implements the LinkedHashTable data structure. It is based on the HashTable
but is able to record the insertion order of the elements using a doubly linked list that
overlays on the HashTable implementation.

Author: Steve Gao, sg2369@rit.edu
Author: Qiwen Quan, qq5575@rit.edu
'''

# Import the abstract classes
from collections.abc import Iterable
from set import SetType


class ChainNode():
    '''
    A node that is used to store the information of a key and 
    build the LinkedHashTable.
    '''

    '''
    key: The key to be stored.
    prev: Pointer to the node in front of this node.
    fwd: Pointer to the next node in the same hash table bucket.
    next: Pointer to the node after this node.
    '''
    __slots__ = 'key', 'prev', 'fwd', 'next'


    def __init__(self, key):
        '''
        Constructor for the ChainNode class. Initializes the fields.
        :param key: The key to be stored.
        '''
        self.key = key
        self.prev = None
        self.fwd = None
        self.next = None
    
    def __str__(self):
        '''
        Return the key in the form of string.
        '''
        return self.key


class LinkedHashTable(SetType, Iterable):
    """
    A collection of keys where no key may be in the set more than once.
    Note that this is a set, not a map, so there are only keys, not values.
    """

    '''
    Threshold for the table load. If table is filled in a percentage higher than the
    threshold, number of buckets in the table will be doubled. If table is filled in 
    a percentage less than 1 - this threshold, number of buckets in the table will be halved.
    '''
    LOAD_LIMIT = 0.7
    # Minimal number of table size. Number of buckets in the table will be less than this value.
    MIN_BUCKETS = 10

    '''
    back: End of the double linked list
    front: Start of the double linked list
    size: Number of nodes currently in the LinkedHashTable
    table: List used to implement the LinkedHashTable
    number_of_buckets: Number of buckets currently in the LinkedHashTable
    '''
    __slots__ = 'back', 'front', 'size', 'table','number_of_buckets'

    def __init__(self, table_size):
        '''
        Constructor for the LinkedHashTable class. Initializes the fields

        :param table_size: Initial number of buckets in the LinkedHashTable.
        If table_size is less than the minimum bucket number, it will be replaced 
        by the minumum bucket number.
        '''
        if table_size < self.MIN_BUCKETS:
            table_size = self.MIN_BUCKETS
        self.back = None
        self.front = None
        self.size = 0
        self.table = [None] * table_size
        self.number_of_buckets = table_size


    def hash_function(self, key):
        '''
        Calculate the hash value of a given key. This value will always be in 
        range [0, number_of_buckets).

        :param key: The key which will be used to calculate the hash value.
        :return: the hash value of the given key.
        '''
        return (ord(key[0]) - ord('a')) % self.number_of_buckets


    def contains( self, obj ):
        """
        Checks if the obj is already in the LinkedHashTable. Overrides the method
        in the abstract class SetType.

        :param obj: The key to be checked.
        :return: True iff obj or its equivalent has been added to this LinkedHashTable
        and not removed.
        """
        ind = self.hash_function(obj)
        if (self.table[ind]) is not None:
            cur = self.table[ind]
            while(cur is not None):
                if cur.key == obj:
                    return True
                cur = cur.fwd
        return False


    def add_list(self, node):
        '''
        Add a node to its corresponding bucket determined by the hash value of this node.

        :param node: The node to be added to the bucket.
        '''
        ind = self.hash_function(node.key)
        cur = self.table[ind]
        prev = None
        # Go to the end of the bucket.
        while (cur is not None):
            prev = cur
            cur = cur.fwd
        # The bucket is initially empty.
        if prev is None:
            self.table[ind] = node
        else:
            prev.fwd = node


    def add( self, obj ):
        """
        Insert a new key into the LinkedHashTable. It will not be added if 
        self.contains(obj) returns True. If after insertion the table reaches
        its load limit, the number of buckets in the table will be doubled and
        all keys will be rehashed.
        Overrides method in the abstract class SetType.

        :param obj: The key to add.
        """
        if self.contains(obj):
            print('Object already exists!')
            return
        node = ChainNode(obj)
        # The LinkedHashTable is initially empty.
        if self.front is None:
            self.front = node
            self.back = node
        else:
            node.prev = self.back
            self.back.next = node
            self.back = node
        self.add_list(node)
        self.size += 1
        # Checks if the table has reached its load limit. If so, double the table size.
        if self.size / self.number_of_buckets > self.LOAD_LIMIT:
            self.table = list()
            self.number_of_buckets *= 2
            self.number_of_buckets = int(self.number_of_buckets)
            self.table = [None] * self.number_of_buckets
            cur = self.front
            while cur is not None:
                cur.fwd = None
                self.add_list(cur)
                cur = cur.next


    def remove( self, obj ):
        """
        Remove a key from the LinkedHashTable. If after removal, the table load is less
        than 1 - the threshold, the number of buckets in the table will be halved and all
        the keys will be rehashed.
        Overrides method in the abstract class SetType.

        :param obj: the key to remove
        """
        if not self.contains(obj):
            print("No such key in the LinkedHashTable!")
            return
        ind = self.hash_function(obj)
        cur = self.table[ind]
        prev = None
        while(cur is not None and cur.key != obj):
            prev = cur
            cur = cur.next
        # The first key in a bucket is to be removed.
        if prev is None:
            self.table[ind] = cur.fwd
        else:
            prev.fwd = cur.fwd
        # The key inserted earliest is to be removed.
        if cur.prev is None:
            self.front = cur.next
            if cur.next is not None:
                cur.next.prev = None
        else:
            cur.prev.next = cur.next
        # The key inserted latest is to be removed.
        if cur.next is None:
            self.back = cur.prev
            if cur.prev is not None:
                cur.prev.next = None
        else:
            cur.next.prev = cur.prev
        self.size -= 1
        # Checks if the table load is less than 1 - threshold. If so, 
        # the number of buckets in the table will be halved.
        if self.size / self.number_of_buckets < 1 - self.LOAD_LIMIT:
            if self.number_of_buckets <= self.MIN_BUCKETS:
                return
            self.table = list()
            self.number_of_buckets /= 2
            self.number_of_buckets = int(self.number_of_buckets)
            self.table = [None] * self.number_of_buckets
            cur = self.front
            while cur is not None:
                cur.fwd = None
                self.add_list(cur)
                cur = cur.next
        

    def __iter__( self ):
        """
        Build an iterator.
        Overrides method in the abstract class Iterable.

        :return: an iterator for the current elements in the LinkedHashTable.
        """
        cur = self.front
        while(cur is not None):
            yield cur
            cur = cur.next

