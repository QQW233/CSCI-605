package hw3.Expressions;

/**
 * Specific class for parsing, emitting and evaluating token "*".
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class MulExpression implements Expression{
    private final Expression left;
    private final Expression right;

    /**
     * A constructor for MulExpression
     * @param left an expression on left hand side of Multiplication
     * @param right and expression on right hand side of Multiplication
     */
    public MulExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * evaluate the value of the expression
     *
     * @return an int indicating the result
     */
    @Override
    public int evaluate() throws Exception {
        return left.evaluate()*right.evaluate();
    }

    /**
     * emit the expression as a string
     *
     * @return a string representing the expression
     */
    @Override
    public String emit() {
        return new StringBuilder().append("(").append(left.emit()).append(" * ").append(right.emit()).append(")").toString();
    }
}
