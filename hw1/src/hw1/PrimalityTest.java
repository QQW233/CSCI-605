package hw1;

/**
 * PrimalityTest is used to determine whether a number is prime
 *
 * @author Qiwen Quan,qq575@g.rit.edu
 */
public class PrimalityTest {
    /**
     * isPrime takes a number and return if it's prime
     * @param number the number to be tested
     * @return true if number is prime, vice versa
     */
    public static boolean isPrime(int number) {
        // any number bellow 1 is not prime
        if (number <= 1) return false;
        // loop for any number between 2 and one half of number
        for (int i = 2; i <= number / 2; i++) {
            //if number is a multiply of i
            if (number % i == 0){
//                System.out.println("number is : " + number +";i is : "+ i +"\n");
                return false;
            }
        }
        return true;
    }
}
