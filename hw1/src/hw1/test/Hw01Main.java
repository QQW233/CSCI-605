package hw1.test;

import hw1.Palindrome;
import hw1.PrimalityTest;
import hw1.PrimeGap;
import hw1.SieveOfEratosthenes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class encapsulates a test program provided to students so that they
 * can test their implementations of the various parts of the homework. This class
 * will not test the standalone execution of each class (e.g. the main
 * method), but will instead call the required helper functions directly in
 * each of the implementation classes.
 *
 * @author RIT CS
 */
public class Hw01Main {

    /**
     * A truth table for the prime numbers between 0 and 1000.
     */
    public static final int[] PRIME_TRUTH_TABLE = {
            1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0,
            1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0,
            1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0,
            1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1,
            1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1,
            1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0,
            1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0,
            1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1,
            1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1,
            1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0,
            1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1,
            1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0,
            1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1,
            1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1,
            1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0,
            1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1,
            1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,
            1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1
    };

    /**
     * A truth table for the prime gaps between 1 and 12 for numbers between 0 and 1000.
     */
    public static final String[] PRIME_GAP_TRUTH_TABLE = {
            "1 - (2, 3)",
            "2 - (3, 5), (5, 7), (11, 13), (17, 19), (29, 31), (41, 43), (59, 61), (71, 73), (101, 103), (107, 109), (137, 139), (149, 151), (179, 181), (191, 193), (197, 199), (227, 229), (239, 241), (269, 271), (281, 283), (311, 313), (347, 349), (419, 421), (431, 433), (461, 463), (521, 523), (569, 571), (599, 601), (617, 619), (641, 643), (659, 661), (809, 811), (821, 823), (827, 829), (857, 859), (881, 883)",
            "3 - (2, 5)",
            "4 - (3, 7), (7, 11), (13, 17), (19, 23), (37, 41), (43, 47), (67, 71), (79, 83), (97, 101), (103, 107), (109, 113), (127, 131), (163, 167), (193, 197), (223, 227), (229, 233), (277, 281), (307, 311), (313, 317), (349, 353), (379, 383), (397, 401), (439, 443), (457, 461), (463, 467), (487, 491), (499, 503), (613, 617), (643, 647), (673, 677), (739, 743), (757, 761), (769, 773), (823, 827), (853, 857), (859, 863), (877, 881), (883, 887), (907, 911), (937, 941), (967, 971)",
            "5 - (2, 7)",
            "6 - (5, 11), (7, 13), (11, 17), (13, 19), (17, 23), (23, 29), (31, 37), (37, 43), (41, 47), (47, 53), (53, 59), (61, 67), (67, 73), (73, 79), (83, 89), (97, 103), (101, 107), (103, 109), (107, 113), (131, 137), (151, 157), (157, 163), (167, 173), (173, 179), (191, 197), (193, 199), (223, 229), (227, 233), (233, 239), (251, 257), (257, 263), (263, 269), (271, 277), (277, 283), (307, 313), (311, 317), (331, 337), (347, 353), (353, 359), (367, 373), (373, 379), (383, 389), (433, 439), (443, 449), (457, 463), (461, 467), (503, 509), (541, 547), (557, 563), (563, 569), (571, 577), (587, 593), (593, 599), (601, 607), (607, 613), (613, 619), (641, 647), (647, 653), (653, 659), (677, 683), (727, 733), (733, 739), (751, 757), (821, 827), (823, 829), (853, 859), (857, 863), (877, 883), (881, 887), (941, 947), (947, 953), (971, 977), (977, 983), (991, 997)",
            "7 - ",
            "8 - (3, 11), (5, 13), (11, 19), (23, 31), (29, 37), (53, 61), (59, 67), (71, 79), (89, 97), (101, 109), (131, 139), (149, 157), (173, 181), (191, 199), (233, 241), (263, 271), (269, 277), (359, 367), (389, 397), (401, 409), (431, 439), (449, 457), (479, 487), (491, 499), (563, 571), (569, 577), (593, 601), (599, 607), (653, 661), (683, 691), (701, 709), (719, 727), (743, 751), (761, 769), (821, 829), (911, 919), (929, 937), (983, 991)",
            "9 - (2, 11)",
            "10 - (3, 13), (7, 17), (13, 23), (19, 29), (31, 41), (37, 47), (43, 53), (61, 71), (73, 83), (79, 89), (97, 107), (103, 113), (127, 137), (139, 149), (157, 167), (163, 173), (181, 191), (223, 233), (229, 239), (241, 251), (271, 281), (283, 293), (307, 317), (337, 347), (349, 359), (373, 383), (379, 389), (409, 419), (421, 431), (433, 443), (439, 449), (457, 467), (499, 509), (547, 557), (577, 587), (607, 617), (631, 641), (643, 653), (673, 683), (691, 701), (709, 719), (733, 743), (751, 761), (787, 797), (811, 821), (829, 839), (853, 863), (877, 887), (919, 929), (937, 947), (967, 977)",
            "11 - (2, 13)",
            "12 - (5, 17), (7, 19), (11, 23), (17, 29), (19, 31), (29, 41), (31, 43), (41, 53), (47, 59), (59, 71), (61, 73), (67, 79), (71, 83), (89, 101), (97, 109), (101, 113), (127, 139), (137, 149), (139, 151), (151, 163), (167, 179), (179, 191), (181, 193), (199, 211), (211, 223), (227, 239), (229, 241), (239, 251), (251, 263), (257, 269), (269, 281), (271, 283), (281, 293), (337, 349), (347, 359), (367, 379), (389, 401), (397, 409), (409, 421), (419, 431), (421, 433), (431, 443), (449, 461), (467, 479), (479, 491), (487, 499), (491, 503), (509, 521), (557, 569), (587, 599), (601, 613), (607, 619), (619, 631), (631, 643), (641, 653), (647, 659), (661, 673), (727, 739), (739, 751), (757, 769), (761, 773), (797, 809), (809, 821), (811, 823), (827, 839), (907, 919), (929, 941), (941, 953), (971, 983)"
    };

    /**
     * A table of palindromes
     */
    public static final String[] PALINDROMES_TABLE = {
            "1",
            "Air 2 an a2ria",
            "A but tuba",
            "A lad named E Mandala",
            "A nut for a jar of tuna",
            "A Santa at Nasa",
            "A Santa dog lived as a devil God at NASA",
            "A Toyota s a Toyota",
            "Able was I ere I saw Elba",
            "Acrobats stab orca",
            "Aerate pet area",
            "Aibohphobia ",
            "Air an aria",
            "Al lets Della call Ed Stella",
            "Nurses Run",
            "alula",
            "Amen icy cinema",
            "Amore Roma",
            "Ana",
            "Animal loots foliated detail of stool lamina",
            "Anna",
            "Avid diva",
            "Murder for a jar of red rum",
            "Mom",
            "civic",
            "evitative",
            "Hannah",
            "kayak",
            "kinnikinnik",
            "level",
            "madam",
            "Malayalam"
    };

    /**
     * A table of non-palindromes
     */
    public static final String[] NON_PALINDROMES_TABLE = {
            "",
            "I am testing",
            "King, are you glad you are king?",
            "Madam, in Eden, I’m Adam",
            null};

    /**
     * Tests the {@link PrimalityTest#isPrime(int)} function to verify that
     * it returns the correct result for all numbers in the range 0-1000.
     */
    public static void testPrimality() {
        System.out.println("Testing PrimalityTest...");
        for (int i = 0; i < PRIME_TRUTH_TABLE.length; i++) {
            boolean expected = PRIME_TRUTH_TABLE[i] == 0;
            boolean result = PrimalityTest.isPrime(i);

            if (expected != result) {
                System.out.println("  Primality test failed: '" + i +
                        "'; expected " + expected + " but got " + result);
                return; // abort further testing
            }
        }
        System.out.println("  all primality tests passed!");
    }

    /**
     * Tests the {@link SieveOfEratosthenes#makeSieve(int)} method by making
     * a sieve and comparing it to the {@link #PRIME_TRUTH_TABLE}.
     */
    public static void testSieve() {
        System.out.println("Testing SieveOfEratosthenes...");

        int[] sieve = SieveOfEratosthenes.makeSieve(PRIME_TRUTH_TABLE.length);

        for (int i = 0; i < PRIME_TRUTH_TABLE.length; i++) {
            boolean expected = PRIME_TRUTH_TABLE[i] == 0;
            boolean result = sieve[i] == 0;

            if (expected != result) {
                System.out.println("  Sieve of Eratosthenes test failed: '" +
                        i + "'; expected " + expected + " but got " + result);
                return; // abort further testing
            }
        }
        System.out.println("  all Sieve of Eratosthenes tests passed!");
    }

    /**
     * Tests the {@link PrimeGap#primeGap(int, int[])} method by retrieving
     * the lists of prime numbers pairs up to 1000 with gaps in the range 1-12
     * and comparing them to the {@link #PRIME_GAP_TRUTH_TABLE}.
     */
    public static void testPrimeGap() {
        System.out.println("Testing PrimeGap...");
        // generating the sieve array
        int[] sieve = SieveOfEratosthenes.makeSieve(PRIME_TRUTH_TABLE.length);

        for (int i = 0; i < PRIME_GAP_TRUTH_TABLE.length; i++) {
            /* every string in the PRIME_GAP_TRUTH_TABLE
                has the following format:
                gap length - follows by the list of prime number pairs
                Example: 1 - (2, 3)
            */
            String testcase = PRIME_GAP_TRUTH_TABLE[i];
            String[] tokens = testcase.split("-");
            // extracting target gap number
            int x = Integer.valueOf(tokens[0].trim());
            // extracting expected result
            String expected = tokens[1].trim();
            // invoking primeGap method
            List<String> resultList = PrimeGap.primeGap(x, sieve);
            // converting resulting list into a String using "," as delimiter between each pair
            String strResult = resultList.stream().collect(Collectors.joining(", "));
            if (!expected.equals(strResult)) {
                System.out.println("Prime Gap test failed " +
                        "for n=" + i + "\nexpected: " + expected +
                        "\nresult:   " + strResult);
                return; // abort further testing
            }
        }
        System.out.println("  all Prime Gap tests passed!");
    }

    /**
     * Tests the {@link Palindrome#isPalindrome(String)} function to verify that
     * it returns the correct result for all entries on the {@link #PALINDROMES_TABLE}
     * and {@link #NON_PALINDROMES_TABLE}.
     */
    public static void testPalindrome() {
        System.out.println("Testing hw1.Palindrome...");
        boolean result = false;
        if (testPalindromeHelper(PALINDROMES_TABLE, true)) {
            result = testPalindromeHelper(NON_PALINDROMES_TABLE, false);
        }
        if (result) {
            System.out.println("  all hw1.Palindrome tests passed!");
        }
    }

    /**
     * Tests the {@link Palindrome#isPalindrome(String)} function to verify that
     * it returns the correct result for all the test cases
     * @param testTable Table with the different test cases
     * @param expectedResult The expected result
     * @return true if {@link Palindrome#isPalindrome(String)} returns the expected result
     * for all the test cases. Otherwise, it returns false.
     */
    private static boolean testPalindromeHelper(String[] testTable, boolean expectedResult) {
        for (int i = 0; i < testTable.length; i++) {
            String testcase = testTable[i];
            if (expectedResult != Palindrome.isPalindrome(testcase)) {
                System.out.println("hw1.Palindrome test failed " +
                        "for input: " + testcase);
                return false; // abort further testing
            }
        }
        return true;
    }

    /**
     * Tests each of the 4 activities from this homework.
     *
     * @param args Command line arguments; ignored.
     */
    public static void main(String[] args) {
        testPrimality();

        testSieve();

        testPrimeGap();

        testPalindrome();
    }
}