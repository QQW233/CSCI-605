package test;

import PriorityQueue.node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for node
 * tested methods are getNext(), setNext() and getData()
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
class nodeTest {

    @Test
    void testgetNext() {
        node<Integer> test = new node<Integer>(1);
        assertEquals(null,test.getNext());
    }

    @Test
    void testsetNext() {
        node<Integer> test1 = new node<Integer>(1);
        node<Integer> test2 = new node<Integer>(2);
        test1.setNext(test2);
        assertEquals(test2,test1.getNext());

    }

    @Test
    void testgetData() {
        node<Integer> test1 = new node<Integer>(1);
        assertEquals(1,test1.getData());
    }
}