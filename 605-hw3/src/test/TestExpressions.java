package test;

import Expressions.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the Expressions package.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
public class TestExpressions {
    @Test
    public void TestExpressions() throws Exception{
        Expression root = new MulExpression(new ModExpression
                (new AddExpression(new IntExpression(497),new IntExpression(332))
                        ,new DivExpression(new IntExpression(768),new IntExpression(256))),
                new SubExpression(new DivExpression(new IntExpression(7724),new IntExpression(322))
                        ,new IntExpression(1925))
                );
        assertEquals(-1902, root.evaluate());
        assertEquals("(((497 + 332) % (768 / 256)) * ((7724 / 322) - 1925))",root.emit());
    }
}
