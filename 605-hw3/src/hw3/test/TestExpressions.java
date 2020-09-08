package hw3.test;

import hw3.Expressions.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * A hw3.test unit for the hw3.Expressions package.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
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
