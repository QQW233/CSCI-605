package test;

import Expressions.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the SubExpression class.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class TestSubExpression {
    @Test
    public void testSubExpressionInt() throws Exception {
        Expression root = new SubExpression(new IntExpression(20), new IntExpression(10));
        assertEquals(10, root.evaluate());
        assertEquals("(20 - 10)", root.emit());
    }

    @Test
    public void testSubExpressionComplex() throws Exception {
        Expression root = new SubExpression(
                new SubExpression(new IntExpression(40), new IntExpression(10)),
                new SubExpression(new IntExpression(30), new IntExpression(20)));
        assertEquals(20, root.evaluate());
        assertEquals("((40 - 10) - (30 - 20))", root.emit());
    }
}
