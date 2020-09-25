package Flight;

import Passenger.Passenger;
import PriorityQueue.*;
import java.util.Scanner;

/**
 * A flight class that can enqueue a user determined passenger to a priority queue, or dequeue continuously.
 * Will quit when 3 is read.
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class Flight {

    /**
     * Print available options,
     */
    public static void PrintOption(){
        System.out.println("Enter an option");
        System.out.println("1 to Add a passenger to the queue");
        System.out.println("2 to Remove and print the first passenger");
        System.out.println("3 to quit");
        System.out.print("Your choice: ");
    }

    /**
     * ask the user to enter all the information
     * needed to create a passenger object
     * @return a user determined passenger object
     */
    public static Passenger Add(){
        Scanner s = new Scanner(System.in);
        System.out.print("Passenger name: ");
        String name = s.nextLine();
        System.out.print("Boarding Group: ");
        String group = s.nextLine();
        System.out.print("Sequence: ");
        int sequence = s.nextInt();
        s.nextLine();
        return new Passenger(name,group.charAt(0),sequence);
    }

    /**
     * A main method used to handle user input.
     * Valid options are 1: add, 2:remove and 3:quit.
     * It will use a priority queue to store passenger objects.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        PrintOption();
        Scanner s = new Scanner(System.in);
        LinkedQueue <Passenger> Q = new LinkedQueue<Passenger>();
        int option;
        while (s.hasNext()){
            if(s.hasNextInt()){
                option = s.nextInt();
                if (option == 3) break;
                switch (option) {
                    case 1:
                        Q.enqueue(Add());
                        break;
                    case 2:
                        /* if the queue is empty, skip dequeue */
                        if(Q.isEmpty()) System.out.println("Queue is empty.");
                        else System.out.println("Removing : "+Q.dequeue());
                        break;
                    default:
                        System.out.println("Invalid Option");
                }
            }
            else{
                System.err.println("Please pick an option.");
                s.nextLine();
            }
            PrintOption();
        }
    }
}

