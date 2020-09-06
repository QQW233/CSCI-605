package Expressions;

public class DivExpression implements Expression{
    private final Expression left;
    private final Expression right;

    /**
     * A constructor for DivExpression
     * @param left an expression on left hand side of Division
     * @param right and expression on right hand side of Division
     */
    public DivExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * evaluate the value of the expression
     *
     * @return an int indicating the result
     */
    @Override
    public int evaluate() throws Exception{
        int r = right.evaluate();
        if (r == 0) throw new Exception("The right hand side of Division cannot be zero");
        return left.evaluate()/r;
    }

    /**
     * emit the expression as a string
     *
     * @return a string representing the expression
     */
    @Override
    public String emit() {
        return new StringBuilder().append("(").append(left.emit()).append(" / ").append(right.emit()).append(")").toString();
    }
}
