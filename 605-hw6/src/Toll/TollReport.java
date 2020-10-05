package Toll;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Toll Report is the entry point of the TollDataBase System. It will read the input file and print corresponding
 * toll data.
 * User can ask to see the bill information for a given license tage or exit number.
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

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("usage: java TollReport filename");
            exit(0);
        }
        //Create a database and print basic information, this step may result in error
        TollRoadDatabase database = new TollRoadDatabase(args[0]);
        database.reportIncompletedTrips();
        database.reportSpeeders();
        database.reportCompletedTrips();
        TollReport tollReport = new TollReport();
        Scanner s = new Scanner(System.in);
        String[] line;
        char option;
        tollReport.printmenu();
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
                    database.reportPlate(line[1]);
                    break;
                case 'e':
                    //print activity at given exit
                    database.reportExit(Integer.parseInt(line[1]));
                    break;
                default:
                    System.out.println("Illegal command. Try again");
                }
                tollReport.printmenu();
            }
     }
}
