package View;

import Controller.Controller;
import Model.ZipperException;
import java.util.Scanner;

/**
 * ZipperUtility program entry point. This is a command line utility that provides user several options to choose from
 * and perform the corresponding actions regarding compressing and decompressing.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class ZipperUtility {

    /**
     * Run the program. This program will continuously ask for user input to perform the corresponding action
     * until the user choose the "quit" option to quit the program.
     */
    public void run(){
        Controller controller = new Controller();
        String input;
        Scanner s = new Scanner(System.in);
        System.out.print("enter command >> ");
        while (s.hasNext()){
            input = s.nextLine().trim();
            if (input.equals("")) continue;
            if(input.equals("quit")) break;
            try {
                System.out.println(controller.commandParsing(input.split(" ")));
            } catch (ZipperException e) {
                System.out.println("Error processing most recent command!");
                System.out.println(e.getMessage());
            } finally {
                System.out.print("enter command >> ");
            }
        }
        System.out.println("Quitting...");
    }

    /**
     * Entry point of the program. It will create the ZipperUtility class and call the run() method to run the program.
     *
     * @param args command line inputs. Not used in this program.
     */
    public static void main(String[] args) {
        ZipperUtility main = new ZipperUtility();
        main.run();
    }
}
