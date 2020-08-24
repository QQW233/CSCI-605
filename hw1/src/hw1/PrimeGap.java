package hw1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static hw1.SieveOfEratosthenes.makeSieve;

/**
 * Prime gap is used to generate a string list of prime gaps with the given gap length
 * Prime gap contains a main function to be used alone
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class PrimeGap {
    /**
     * primeGap finds all prime gaps with certain length from the ginve sieve
     * @param n the gap length to search for
     * @param sieve a int array to find the prime gaps from
     * @return gaps all prime gaps found in String list
     * hw1.pre sieve should be a int array consists only 0
     * and has more than n elements.
     */
    public static List<String> primeGap(int n, int[] sieve){
        //initialize a new string list
        List<String> gaps = new ArrayList<>();
        //return empty list if the gap length is not applicable
        if (n < 1) return gaps;
        //iterate all the possible start of prime gap in sieve
        for (int i = 2; i < sieve.length - n; i++) {
            //if a gap length of n is found
            if (sieve[i] == 0 && sieve[i + n] == 0) {
                //create a string with the prime gap found
                String temp = "(" + i + ", " + (i+n) + ")";
                //add the prime gap to list
                gaps.add(temp);
            }
        }
        return gaps;
    }

    /**
     * main method that asks create a sieve with user-defined size
     * then print all prime gaps with user given gap length in the sieve
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        //initialize a scanner
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        //ask for the upperBound for sieve
        System.out.println("Please enter an upperBound: ");
        int upperBound = scanner.nextInt();
        //create a sieve with the given upperBound
        int[] Sieve = makeSieve (upperBound);
        //ask for a gap length
        System.out.println("Please enter a positive number (0 to quit): ");
        int number = scanner.nextInt();
        //when the gap entered is larger than 1 and smaller than sieve size
        while (number >= 1 && number <= upperBound) {
            //find gaps list by calling primGap(int,int[])
            List<String> gaps = primeGap(number, Sieve);
            //print the content of gaps using iterator
            Iterator<String> gapIterator = gaps.iterator();
            //skip if gap is empty
            if (!gaps.isEmpty()) {
                while (gapIterator.hasNext()) {
                    System.out.println(gapIterator.next());
                }
            }
            //when gap is empty, print Not found
            else System.out.println("Not found");
            // ask for the next gap size
            System.out.println("Please enter a positive number (0 to quit): ");
            number = scanner.nextInt();
        }
        //terminate process
        System.out.println("Goodbye!");
        scanner.close();
    }
}


