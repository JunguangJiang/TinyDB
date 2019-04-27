package db.field;

import db.query.ComparisonPredicate;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringFieldTest {

    @Test
    public void compare1() {
        StringField field1 = new StringField("Hello", 4);
        StringField field2 = new StringField("Hell1", 4);
        assertEquals(field1, field2);
        assertTrue(field1.compare(ComparisonPredicate.Op.EQUALS, field2));
        assertFalse(field1.compare(ComparisonPredicate.Op.NOT_EQUALS, field2));
        assertFalse(field1.compare(ComparisonPredicate.Op.LESS_THAN, field2));
        assertFalse(field1.compare(ComparisonPredicate.Op.GREATER_THAN, field2));
        assertTrue(field1.compare(ComparisonPredicate.Op.LESS_THAN_OR_EQ, field2));
        assertTrue(field1.compare(ComparisonPredicate.Op.GREATER_THAN_OR_EQ, field2));
    }

    @Test
    public void compare2() {
        StringField field1 = new StringField("Hella", 8);
        StringField field2 = new StringField("Hellb", 9);
        assertNotEquals(field1, field2);
        assertFalse(field1.compare(ComparisonPredicate.Op.EQUALS, field2));
        assertTrue(field1.compare(ComparisonPredicate.Op.NOT_EQUALS, field2));
        assertTrue(field1.compare(ComparisonPredicate.Op.LESS_THAN, field2));
        assertFalse(field1.compare(ComparisonPredicate.Op.GREATER_THAN, field2));
        assertTrue(field1.compare(ComparisonPredicate.Op.LESS_THAN_OR_EQ, field2));
        assertFalse(field1.compare(ComparisonPredicate.Op.GREATER_THAN_OR_EQ, field2));
    }

    @Test
    public void compare3() {
        StringField field1 = new StringField("Helloooo", 10);
        StringField field2 = new StringField("Hell", 10);
        assertNotEquals(field1, field2);
        assertFalse(field1.compare(ComparisonPredicate.Op.EQUALS, field2));
        assertTrue(field1.compare(ComparisonPredicate.Op.NOT_EQUALS, field2));
        assertFalse(field1.compare(ComparisonPredicate.Op.LESS_THAN, field2));
        assertTrue(field1.compare(ComparisonPredicate.Op.GREATER_THAN, field2));
        assertFalse(field1.compare(ComparisonPredicate.Op.LESS_THAN_OR_EQ, field2));
        assertTrue(field1.compare(ComparisonPredicate.Op.GREATER_THAN_OR_EQ, field2));
    }
}