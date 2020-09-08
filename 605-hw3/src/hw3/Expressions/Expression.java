package hw3.Expressions;

/**
 * This interface defines two methods for expressions: evaluate() and emit()
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public interface Expression {
    /**
     * evaluate the value of the expression
     * @return an int indicating the result
     */
    int evaluate() throws Exception;

    /**
     * emit the expression as a string
     * @return a string representing the expression
     */
    String emit();
}
