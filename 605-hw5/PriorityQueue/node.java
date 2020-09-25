package PriorityQueue;

/**
 *  A node class designed for priority queue
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class node<T extends Comparable<T>> {
    /* a reference to the next node */
    private node<T> next;
    /* data stored in node */
    private T data;

    /**
     * A constructor for node
     * @param data data to be stroed
     */
    public node(T data) {
        this.next = null;
        this.data = data;
    }

    /**
     * A next setter
     * @param next  new next
     */
    public void setNext(node<T> next) {
        this.next = next;
    }

    /**
     * A next getter
     * @return may return node or null
     */
    public node<T> getNext(){
        return next;
    }

    /**
     * A data getter
     * @return data stored in node
     */
    public T getData(){
        return data;
    }

}
