package Expressions;

public class AddExpression implements Expression{
    private final Expression left;
    private final Expression right;

    /**
     * A constructor for AddExpression
     * @param left an expression on left hand side of addition
     * @param right and expression on right hand side of addition
     */
    public AddExpression(Expression left, Expression right) {
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
        return left.evaluate()+right.evaluate();
    }

    /**
     * emit the expression as a string
     *
     * @return a string representing the expression
     */
    @Override
    public String emit() {
        return new StringBuilder().append("(").append(left.emit()).append(" + ").append(right.emit()).append(")").toString();
    }
}
