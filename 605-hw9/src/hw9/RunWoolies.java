package hw9;

import org.junit.Test;

/*
 * RunWoolies.java
 * Provided source file as a starter for a test suite.
 * See the todo text for what to complete.
 */

/**
 * Test the TrollsBridge and Woolies simulation.
 * Test by creating a bunch of Woolies and let them cross the TrollsBridge.
 *
 * @author CS @ RIT.EDU
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class RunWoolies {

    /**
     * SIDE_ONE is Merctran.
     */
    public final static String SIDE_ONE = "Merctran";

    /**
     * SIDE_TWO is Sicstine.
     */
    public final static String SIDE_TWO = "Sicstine";

    @Test
    /**
     * test0 is Test Scenario 0, an extremely simple, non-waiting test.
     * Woolie Bob will arrive at the bridge after Woolie Al leaves the bridge.
     *
     * test0 provides an example template/pattern for writing a test case.
     */
    public void test0() {

        System.out.println("Begin test0. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);

        // Set an optional, test delay to stagger the start of each woolie.
        int delay = 4000;

        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Al", 3, SIDE_ONE, trollBridge),
                new Woolie("Bob", 4, SIDE_TWO, trollBridge),
        };

        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
                break;
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
                break;
            }
        }
        System.out.println("\n=============================== End test0.");
    }


    @Test
    /**
     * test1 is Test Scenario 1, another fairly simple simulation run.
     * test1 provides another example for writing a test case.
     */
    public void test1() {

        System.out.println("Begin test1. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);

        int delay = 1000;

        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Al", 3, SIDE_ONE, trollBridge),
                new Woolie("Bob", 2, SIDE_ONE, trollBridge),
                new Woolie("Cathy", 2, SIDE_TWO, trollBridge),
                new Woolie("Doris", 3, SIDE_TWO, trollBridge),
                new Woolie("Edith", 3, SIDE_ONE, trollBridge),
                new Woolie("Fred", 2, SIDE_TWO, trollBridge),
        };

        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                Thread.sleep(delay);         // delay start of next woolie
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();              // wait for next woolie to finish
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }

        System.out.println("\n=============================== End test1.");
    }

    @Test
    /**
     * This test tests the scenario that a group of Woolies arrive a few seconds after another group of Woolies
     * to see if the program actually allows all Woolies to eventually get on the bridge and finish their crossing.
     */
    public void test2() {

        System.out.println("Begin test2. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);

        int delay = 1000;

        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Al", 7, SIDE_ONE, trollBridge),
                new Woolie("Bob", 4, SIDE_ONE, trollBridge),
                new Woolie("Cathy", 6, SIDE_TWO, trollBridge),
                new Woolie("Doris", 3, SIDE_TWO, trollBridge),
                new Woolie("Edith", 3, SIDE_ONE, trollBridge),
                new Woolie("Fred", 2, SIDE_TWO, trollBridge),
                new Woolie("Gianna", 4, SIDE_TWO, trollBridge),
                new Woolie("Harry", 3, SIDE_TWO, trollBridge),
                new Woolie("Issac", 3, SIDE_ONE, trollBridge),
                new Woolie("Kidd", 2, SIDE_TWO, trollBridge),
        };

        for (int j = 0; j < peds.length-3; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                Thread.sleep(delay);         // delay start of next woolie
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        try{
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            System.err.println("Abort. Unexpected thread interruption.");
        }
        for (int j = peds.length-3; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                Thread.sleep(delay);         // delay start of next woolie
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();              // wait for next woolie to finish
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        System.out.println("\n=============================== End test2.");
    }


    @Test
    /**
     * This test tests the scenario that multiple Woolies are created simultaneously to see if the queue
     * works correctly and Woolies are called to cross the bridge according to their order of arrival.
     */
    public void test3() {
        System.out.println("Begin test3. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies
        TrollsBridge trollBridge = new TrollsBridge(3);
        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Al", 6, SIDE_ONE, trollBridge),
                new Woolie("Bob", 5, SIDE_TWO, trollBridge),
                new Woolie("Cathy", 4, SIDE_ONE, trollBridge),
                new Woolie("Doris", 3, SIDE_TWO, trollBridge),
                new Woolie("Edith", 2, SIDE_ONE, trollBridge),
                new Woolie("Fred", 1, SIDE_TWO, trollBridge),
                new Woolie("Gianna", 4, SIDE_ONE, trollBridge),
                new Woolie("Harry", 3, SIDE_TWO, trollBridge),
                new Woolie("Issac", 2, SIDE_ONE, trollBridge),
                new Woolie("Kidd", 1, SIDE_TWO, trollBridge),
        };

        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            peds[j].start();
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();              // wait for next woolie to finish
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        System.out.println("\n=============================== End test3.");
    }
}