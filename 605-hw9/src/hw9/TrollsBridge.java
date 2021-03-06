package hw9;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * TrollsBridge handles the concurrency among Woolie threads.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class TrollsBridge {
    private final int capacity;
    private int currWoolie;
    private Queue<Woolie> woolies;
    private int running;
    static final Object lock = new Object();
    static final Object lock2 = new Object();

    /**
     * A constructor method.
     * @param capacity set the capacity of troll's bridge
     */
    public TrollsBridge(int capacity) {
        this.currWoolie = capacity;
        this.capacity = capacity;
        this.woolies = new ConcurrentLinkedQueue<>();
        this.running = 0;
        }

    /**
     * enterBridgePlease get woolies onto the bridge following FIFO principle.
     * woolie will wait if the bridge is full.
     * @param woolie A new incoming woolie
     */
    public void enterBridgePlease(Woolie woolie) {
        // Ensure that the printed order is the same as the actual order of Woolies in the queue.
        synchronized (lock2){
            woolies.add(woolie);
            System.out.printf("The troll scowls \"Get in line!\" when %s shows up at the bridge.%n", woolie.woolieName());
        }
        synchronized (woolie) {
            if (running >= capacity) {
                try {
                    woolie.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        woolies.remove(woolie);
        synchronized (lock) {
            running += 1;
        }
        System.out.printf("%s is starting to cross.%n", woolie.woolieName());
    }

    /**
     * leave gets woolie off the bridge and notify the next woolie to go.
     * @param woolie the woolie ready to get off the beidge
     */
    public void leave(Woolie woolie){
        synchronized (lock) {
            running -= 1;
        }
        System.out.printf("%s leaves at %s.%n",woolie.woolieName(),woolie.getDestination());
        if (!woolies.isEmpty()) {
            synchronized (woolies.peek()) {
                woolies.remove().notify();
            }
        }
    }
}
