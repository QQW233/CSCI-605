package Test;

import Toll.TollReport;
import Toll.TollRoadDatabase;
import org.junit.Test;

import java.io.*;
/**
 * test class for RoadDataBase and TollReport
 * the output is compared with expected output given by the instructor
 * Need to uncomment line 45 in TollReport before use to include user input in comparison.
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class TollRoadDatabaseTest {

    @Test
    public void random10000_test() throws Exception {
        OutputStream o = System.out;
        System.setOut(new PrintStream(
                new File("output/x_random10000.txt")));
        System.setIn(new FileInputStream(new File("random10000.txt")));
        TollReport.main(new String[]{"data/random10000.txt"});
        System.setOut((PrintStream) o);
        Diff.main(new String[]{"output/random10000.txt","output/x_random10000.txt"});
    }

    @Test
    public void random1000_test() throws Exception {
        OutputStream o = System.out;
        System.setOut(new PrintStream(
                new File("output/x_random1000.txt")));
        System.setIn(new FileInputStream(new File("random1000.txt")));
        TollReport.main(new String[]{"data/random1000.txt"});
        System.setOut((PrintStream) o);
        Diff.main(new String[]{"output/random1000.txt","output/x_random1000.txt"});
    }

    @Test
    public void fiveguys_test() throws Exception {
        OutputStream o = System.out;
        System.setOut(new PrintStream(
                new File("output/x_5guys.txt")));
        System.setIn(new FileInputStream(new File("5guys.txt")));
        TollReport.main(new String[]{"data/5guys.txt"});
        System.setOut((PrintStream) o);
        Diff.main(new String[]{"output/5guys.txt","output/x_5guys.txt"});
    }

    @Test
    public void small_test() throws Exception {
        OutputStream o = System.out;
        System.setOut(new PrintStream(
                new File("output/x_small.txt")));
        System.setIn(new FileInputStream(new File("small.txt")));
        TollReport.main(new String[]{"data/small.txt"});
        System.setOut((PrintStream) o);
        Diff.main(new String[]{"output/small.txt","output/x_small.txt"});
    }
}