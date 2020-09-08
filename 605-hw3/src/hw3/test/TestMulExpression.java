package hw3.test;

import hw3.Expressions.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * A hw3.test unit for the SubExpression class.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class TestMulExpression {
    @Test
    public void testMulExpressionInt() throws Exception {
        Expression root = new MulExpression(new IntExpression(2), new IntExpression(10));
        assertEquals(20, root.evaluate());
        assertEquals("(2 * 10)", root.emit());
    }

    @Test
    public void testMulExpressionComplex() throws Exception {
        Expression root = new MulExpression(
                new MulExpression(new IntExpression(1), new IntExpression(2)),
                new MulExpression(new IntExpression(3), new IntExpression(4)));
        assertEquals(24, root.evaluate());
        assertEquals("((1 * 2) * (3 * 4))", root.emit());
    }
}
