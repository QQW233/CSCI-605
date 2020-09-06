package Expressions;

public class ModExpression implements Expression{
    private final Expression left;
    private final Expression right;

    /**
     * A constructor for ModExpression
     * @param left an expression on left hand side of Mod
     * @param right and expression on right hand side of Mod
     */
    public ModExpression(Expression left, Expression right) {
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
        int r = right.evaluate();
        if (r <= 1) throw new Exception("Right hand side of Modulus should be larger than 1");
        return left.evaluate()%right.evaluate();
    }

    /**
     * emit the expression as a string
     *
     * @return a string representing the expression
     */
    @Override
    public String emit() {
        return new StringBuilder().append("(").append(left.emit()).append(" % ").append(right.emit()).append(")").toString();
    }
}
