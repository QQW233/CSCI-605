package hw3.Expressions;

/**
 * Specific class for parsing, emitting and evaluating int.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class IntExpression implements Expression{
    private final int value;

    /**
     * a constructor for IntExpression
     * @param value value of the int
     */
    public IntExpression (int value){
        this.value = value;
    }
    /**
     * evaluate the value of the expression
     * @return an int indicating the result
     */
    @Override
    public int evaluate() throws  Exception {
        return value;
    }
    /**
     * emit the expression as a string
     * @return a string representing the expression
     */
    @Override
    public String emit() {
        return Integer.toString(value);
    }
}
