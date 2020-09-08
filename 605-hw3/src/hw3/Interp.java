package hw3;

import hw3.Expressions.*;
import java.util.Scanner;

/**
 * hw3.Interp is an interpreter for evaluating simple arithmetic expressions.
 * It asks for a mathematical expression(a string, in prefix form) from the standard input
 * and parse the expression into a parse binary tree, then evaluate the expression and
 * display the infix form of the expression(referred as emitting).
 * Currently supports: +, -, *, /, %.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Interp {

    /** Pointer that points to the root of the parse binary tree. */
    private Expression root;
    /** List of tokens read from the user input. */
    private String[] tokens;
    /** records the position of the current parsed token. (eg: when parsing the 1st token, position = 1) */
    private int position;
    /** Scanner to read the user input. */
    private final Scanner in;

    /**
     * Constructor for Interp class that initialize the fields.
     */
    public Interp(){
        this.in = new Scanner(System.in);
        position = 0;
    }

    /**
     * treeHelper method builds the parse binary tree according to the tokens in field: tokens
     * @return pointer to the root of the built parse binary tree.
     */
    public Expression treeHelper(){
        this.position++;
        return switch (tokens[position - 1]) {
            case "+" : yield new AddExpression(treeHelper(), treeHelper());
            case ("-") : yield new SubExpression(treeHelper(), treeHelper());
            case "/" : yield new DivExpression(treeHelper(), treeHelper());
            case "*" : yield new MulExpression(treeHelper(), treeHelper());
            case "%" : yield new ModExpression(treeHelper(), treeHelper());
            default : yield  new IntExpression(Integer.parseInt(tokens[position - 1]));
        };
    }

    /**
     * Read the input from user, then build the parse binary tree according to the user input,
     * evaluating the tree, and display the infix form of the expression (referred as emitting).
     * It will continue to perform the above actions until user input "quit" (case insensitive)
     */
    public void run(){
        String input = this.in.nextLine();
        while(!input.equalsIgnoreCase("quit")){
            try {
                tokens = (input.split(" "));
                if (tokens.length == 0)
                    System.out.print("Empty input!\n");
                else {
                    root = treeHelper();
                    System.out.println("Emit: " + root.emit());
                    System.out.println("Evaluate: " + root.evaluate());
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
            this.position = 0;
            input = this.in.nextLine();
        }
        System.out.print("Goodbye!\n");
    }

    /**
     * main method to run the program. It constructs an Interp object and calls the run() method to run the program.
     * @param args command line argument -- unused.
     */
    public static void main(String[] args){
        System.out.print("Welcome to your Arithmetic Interpreter v1.0 :)\n");
        Interp main = new Interp();
        main.run();
    }
}
