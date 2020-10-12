package View;

import Controller.Controller;
import Model.ZipperException;

import java.util.Scanner;
/**
 * Zipper handles system io.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Zipper {

    public static void main(String[] args) {
        Controller  controller = new Controller();
        String input;
        Scanner s = new Scanner(System.in);
        System.out.print("enter command >> ");
        while (s.hasNext()){
            input = s.nextLine().trim();
            if (input.equals("")) continue;
            if(input.equals("quit")) break;
            try {
                System.out.println(controller.command_parsing(input.split(" ")));
            } catch (ZipperException e) {
                System.out.println("Error processing most recent command!");
                System.out.println(e.getMessage());
            } finally {
                System.out.print("enter command >> ");
            }
        }
        System.out.println("Quitting...");
    }
}
