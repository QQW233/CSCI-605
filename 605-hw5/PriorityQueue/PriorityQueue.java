package PriorityQueue;

/**
 * Interface PriorityQueue. Accepting Generic type.
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public interface PriorityQueue <T>{

    /* Pushing a element into priority queue */
    void enqueue(T data);
    /* Popping the element with highest priority from the queue */
    T dequeue ();
    /* Checking if the queue is empty */
    boolean isEmpty();

}
