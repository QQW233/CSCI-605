package test;

import Expressions.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the ModExpression class.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class TestModExpression {
    @Test
    public void testModExpressionInt() throws Exception {
        Expression root = new ModExpression(new IntExpression(10), new IntExpression(4));
        assertEquals(2, root.evaluate());
        assertEquals("(10 % 4)", root.emit());
    }

    @Test
    public void testModExpressionComplex() throws Exception {
        Expression root = new ModExpression(
                new ModExpression(new IntExpression(97), new IntExpression(30)),
                new ModExpression(new IntExpression(100), new IntExpression(8)));
        assertEquals(3, root.evaluate());
        assertEquals("((97 % 30) % (100 % 8))", root.emit());
    }

    @Test
    public void testModExpressionLessThanOne(){
        Expression root = new ModExpression(new IntExpression(10), new IntExpression(0));
        try {
            assertEquals(0, root.evaluate());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("(10 % 0)", root.emit());
    }
}
