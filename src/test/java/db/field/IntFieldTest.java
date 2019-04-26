package db.field;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntFieldTest {

    private IntField[] intField;

    @Before
    public void setUp() {
        intField = new IntField[4];
        intField[0] = new IntField(0);
        intField[1] = new IntField(0);
        intField[2] = new IntField(10);
        intField[3] = new IntField(-10);
    }

    @Test
    public void equals() {
        assertEquals(intField[0], intField[1]);
        assertNotEquals(intField[0], intField[2]);
    }

    @Test
    public void greater_than() {
        assertTrue(intField[0].greater_than(intField[3]));
        assertTrue(intField[2].greater_than(intField[0]));
        assertFalse(intField[0].greater_than(intField[1]));
        assertFalse(intField[0].greater_than(intField[2]));
    }

    @Test
    public void less_than() {
        assertTrue(intField[0].less_than(intField[2]));
        assertTrue(intField[3].less_than(intField[0]));
        assertFalse(intField[0].less_than(intField[1]));
        assertFalse(intField[0].less_than(intField[3]));
    }
}