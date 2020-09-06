package test;

import Expressions.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the DivExpression class.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class TestDivExpression {
    @Test
    public void testDivExpressionInt() throws Exception {
        Expression root = new DivExpression(new IntExpression(20), new IntExpression(10));
        assertEquals(2, root.evaluate());
        assertEquals("(20 / 10)", root.emit());
    }

    @Test
    public void testDivExpressionComplex() throws Exception {
        Expression root = new DivExpression(
                new DivExpression(new IntExpression(100), new IntExpression(4)),
                new DivExpression(new IntExpression(50), new IntExpression(10)));
        assertEquals(5, root.evaluate());
        assertEquals("((100 / 4) / (50 / 10))", root.emit());
    }

    @Test
    public void testDivExpressionZero(){
        Expression root = new DivExpression(new IntExpression(100), new IntExpression(0));
        try {
            assertEquals(1, root.evaluate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("(100 / 0)", root.emit());
    }
}
