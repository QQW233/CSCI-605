package hw3.test;

import hw3.Expressions.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * A hw3.test unit for the IntExpression class.
 *
 * @author RIT CS
 */
public class TestIntExpression {
    @Test
    public void testIntExpression() throws Exception {
        Expression root = new IntExpression(10);
        assertEquals(10, root.evaluate());
        assertEquals("10", root.emit());
    }
}
