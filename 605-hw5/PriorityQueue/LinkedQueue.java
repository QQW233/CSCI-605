package PriorityQueue;

/**
 * A priority queue implemented using linked list. High priority first.
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class LinkedQueue<T extends Comparable<T>> implements PriorityQueue<T>{
    /* track head node*/
    private node<T> head;

    /**
     * constructor of LinkedQueue
     */
    public LinkedQueue() {
        head = null;
    }

    /**
     * Inserting data to the Linked queue
     * @param data the data to be added
     */
    @Override
    public void enqueue(T data) {
        node<T> toInsert = new node<T>(data);
        if (head == null){
            head = toInsert;
            return;
        }
        node<T> current = head;
        node<T> former = current;
        while (current.getData().compareTo(data) > 0){
            former = current;
            current = current.getNext();
            if (current == null) break;
        }
        former.setNext(toInsert);
        if (current != null) toInsert.setNext(current);
    }

    /**
     * A dequeue method to remove the first node and return its content
     * @return data stored in head
     */
    @Override
    public T dequeue() {
        T data = head.getData();
        head = head.getNext();
        return data;
    }

    /**
     * A boolean method to test if the linked queue is empty
     * @return true if head is null, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * A head getter
     * @return head of the list
     */
    public node<T> getHead() {
        return head;
    }
}
