package hw1;

/**
 * SieveofEratosthenes is used to create a truth table for prime numbers with a given range
 * SieveofEratosthenes has a main functin to work alone
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class SieveOfEratosthenes {
    /**
     * makeSieve create an int array indicating the primality of numbers from 0 to upperBound
     * using the method called "the Sieve of Eratothenes"
     * @param upperBound the number of elements in array minus 1
     * @return Sieve a 0 indicates the number of given location is prime, vice versa
     * hw1.pre upperBound should be larger or equal to 2
     * hw1.post an empty return indicates the iput upperBound is not viable
     */
    public static int[] makeSieve(int upperBound){
        //create an array with elements range from 0 to upperbound
        int[] Sieve = new int[upperBound + 1];
        //setting the primality of 0 and 1
        Sieve[0] = 1;
        Sieve[1] = 1;
        //explore the lowest unchecked number in the array
        for (int explored = 2;explored < upperBound;explored++){
            if (Sieve[explored] == 0) {
                //mark all the multiplies of explored to be not prime
                for (int n_explored = 2 * explored;
                     n_explored <= upperBound; n_explored += explored){
                    Sieve[n_explored] = 1;
                }
            }
        }
        return Sieve;
    }

    /**
     * main method that creates a sieve with user-defined size
     * and return if the user entered number is prime or not in the sieve
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        //initialze scanner
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        //ask the user to input an upperBound of sieve
        System.out.println("Please enter an upperBound: ");
        int upperBound = scanner.nextInt();
        //create a sieve with user-input range
        int[] Sieve = makeSieve (upperBound);
        //ask for a number in the sieve to test primality
        System.out.println("Please enter a positive number (0 to quit): ");
        int number = scanner.nextInt();
        //exeucte if the number is within the given range
        while (number >= 1 && number <= upperBound){
            //print the if the input number is prime
            if(Sieve[number] == 0) System.out.println(number + " is Prime");
            else System.out.println(number + " is not Prime");
            //ask for a new input
            System.out.println("Please enter a number: ");
            number = scanner.nextInt();
        }
        //terminate process
        System.out.println("Goodbye!");
        scanner.close();
    }
}
