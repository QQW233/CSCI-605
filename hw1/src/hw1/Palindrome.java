package hw1;

/**
 * Palindrome contains methods used to test if an input string is a palindrome.
 * Palindrome contains a main method to be used alone.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class Palindrome {
    /**
     * PalindromeHelper determines whether an input string is palindrome.
     * @param s string to be tested
     * @return whether the input is a palindrome
     * @hw1.pre s should be all lower-cased string without whitespace
     * @hw1.post false indicates input is not a palindrome, vice versa
     */
    public static boolean PalindromeHelper(String s){
        // determine if the string reaches end.
        if (s.length() <= 1) return true;
        //recursion call for PlaindromeHelper
        return (s.charAt(0) == s.charAt(s.length() - 1))
                &&PalindromeHelper(s.substring(1,s.length()-1));
    }

    /**
     * is Palindrome calls PalindromeHelper
     * to determine whether the input string is a palindrome
     * @param s A string object to be tested
     * @return return true if the input is a valid Palindrome
     */
    public static boolean isPalindrome(String s){
        //if s is empty or null, return false
        if (s == (null) || s.isEmpty()) return false;
        //format s to be all lowe-cased string without whitespace
        s = s.toLowerCase();
        s = s.replaceAll(" ", "");
        //System.out.println(s);
        //call PalindromeHelper to test if the string is a Palindrome
        return PalindromeHelper(s);
    }

    /**
     * main method asks the user to input a text for Palindrome test
     *
     * @param args unused
     */
    public static void main(String[] args) {
        //initialize a scanner
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        //ask the user to input a text
        System.out.println("Enter text (enter 'q' to quit): ");
        String input = scanner.nextLine();
        //execute until the user enters q
        while (!input.equals("q")){
            //call isPalindtrome to test the input string and print the result
            if(isPalindrome(input)) System.out.println(input + " is a palindrome!");
            else System.out.println(input + " is not a palindrome!");
            //ask for a new input
            System.out.println("Enter text (enter 'q' to quit): ");
            input = scanner.nextLine();
        }
        //terminate process
        System.out.println("Goodbye!");
        scanner.close();
    }
}
