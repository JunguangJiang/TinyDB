package db.field;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberFieldTest {

    @Test
    public void compare() {
        LongField longField1 = new LongField(100);
        LongField longField2 = new LongField(1000);
        assertNotEquals(longField1, longField2);
        assertTrue(longField1.compare(Op.LESS_THAN_OR_EQ, longField2));
        assertTrue(longField1.compare(Op.LESS_THAN, longField2));
        assertFalse(longField1.compare(Op.GREATER_THAN_OR_EQ, longField2));
        assertFalse(longField1.compare(Op.GREATER_THAN, longField2));
        assertTrue(longField1.compare(Op.NOT_EQUALS, longField2));
        assertFalse(longField1.compare(Op.EQUALS, longField2));
    }
}