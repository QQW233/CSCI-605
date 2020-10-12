package Toll;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Toll Report is the entry point of the TollDataBase System. It will read the input file and print corresponding
 * toll data.
 * User then can ask to see the bill information for a given license tage or exit number.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */

public class TollReport {

    /**
     * A print menu method to display available options.
     */
    public void printmenu(){
        System.out.println("'b <string>' to see bill for license tag");
        System.out.println("'e <number>' to see activity at exit");
        System.out.println("'q' to quit");
        System.out.print("> ");
    }

    /**
     * Run the program. run method will first create the TollRoadDatabase object to read in all the information from
     * the given file, and display the information. Then it will print a menu of available options for user to choose.
     * Program will terminate if user choose option 'q'
     *
     * @param filename the database file to be read in
     * @throws Exception Runtime error (invalid/corrupted file)
     */
    public void run(String filename) throws Exception {
        //Create a database and print basic information, this step may result in error
        TollRoadDatabase database = new TollRoadDatabase(filename);
        database.reportIncompletedTrips();
        database.reportSpeeders();
        database.reportCompletedTrips();
        TollReport tollReport = new TollReport();
        Scanner s = new Scanner(System.in);
        String[] line;
        char option;
        tollReport.printmenu();
        // keep printing the menu until user choose to exit with option 'q'
        while (s.hasNext()){
            String input = s.nextLine();
            //System.out.println(input); //uncomment this line for perfect unit test result
            line = input.split(" ");
            //test the input
            if (line.length < 1 || line.length > 2 || line[0].length() > 1){
                System.out.println("Illegal command. Try again");
                tollReport.printmenu();
                continue;
            }
            option = line[0].charAt(0);
            if (option == 'q') break;
            switch (option) {
                case 'b':
                    //print billing information of given plate
                    try {
                        database.reportPlate(line[1]);
                    } catch (Exception e){
                        System.out.println("Incorrect arguments.");
                    }
                    break;
                case 'e':
                    //print activity at given exit
                    try {
                        database.reportExit(Integer.parseInt(line[1]));
                    } catch (Exception e){
                        System.out.println("Incorrect arguments.");
                    }
                    break;
                default:
                    System.out.println("Illegal command. Try again");
            }
            tollReport.printmenu();
        }
    }


    /**
     * Entry point of the program. It will first check if the program is executed with proper arguments. If so, call the
     * run() method to run the program.
     *
     * @param args the database file to be read in
     * @throws Exception Runtime error (invalid/corrupted file)
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("usage: java TollReport filename");
            exit(0);
        }
        TollReport main = new TollReport();
        main.run(args[0]);
     }
}
