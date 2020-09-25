package test;

import PriorityQueue.LinkedQueue;
import PriorityQueue.node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for linkedqueue
 * tested methods are enqueue(), dequeue() and isEmpty()
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
class LinkedQueueTest {

    @Test
    void testgetHead(){
        LinkedQueue<Integer> test = new LinkedQueue<Integer>();
        assertNull(test.getHead());
        test.enqueue(1);
    }

    @Test
    void testenqueue() {
        LinkedQueue<Integer> test = new LinkedQueue<Integer>();
        test.enqueue(1);
        assertEquals(1,test.getHead().getData());
    }

    @Test
    void testdequeue() {
        LinkedQueue<Integer> test = new LinkedQueue<Integer>();
        test.enqueue(1);
        assertEquals(1,test.dequeue());
    }

    @Test
    void testisEmpty() {
        LinkedQueue<Integer> test = new LinkedQueue<Integer>();
        assertTrue(test.isEmpty());
        test.enqueue(1);
        assertFalse(test.isEmpty());
    }
}