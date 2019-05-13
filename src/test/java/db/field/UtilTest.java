package db.field;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void getField() {
    }

    @Test
    public void getField1() {
        try {
            IntField field = (IntField) Util.getField(3, Type.INT_TYPE, 0);
            assertEquals(field.getType(), Type.INT_TYPE);
            assertEquals(field.getValue(), 3);
        } catch (TypeMismatch e) {
            fail();
        }

        try {
            LongField field = (LongField) Util.getField(3, Type.LONG_TYPE, 0);
            assertEquals(field.getType(), Type.LONG_TYPE);
            assertEquals(field.getValue(), 3l);
        } catch (TypeMismatch e) {
            fail();
        }

        try {
            FloatField field = (FloatField) Util.getField(3.3, Type.FLOAT_TYPE, 0);
            assertEquals(field.getType(), Type.FLOAT_TYPE);
            assertEquals("", Double.parseDouble(field.getValue().toString()), 3.3, 0.00001);
        } catch (TypeMismatch e) {
            fail();
        }

        try {
            DoubleField field = (DoubleField) Util.getField(3.3333, Type.DOUBLE_TYPE, 0);
            assertEquals(field.getType(), Type.DOUBLE_TYPE);
            assertEquals("", Double.parseDouble(field.getValue().toString()), 3.3333, 0.00001);
        } catch (TypeMismatch e) {
            fail();
        }

        try {
            StringField field = (StringField) Util.getField("HELLO", Type.STRING_TYPE, 10);
            assertEquals(field.getType(), Type.STRING_TYPE);
            assertEquals(field.getValue(), "HELLO");
        } catch (TypeMismatch e) {
            fail();
        }

        boolean failed = false;
        try {
            StringField field = (StringField) Util.getField("HELLO", Type.INT_TYPE, 10);
        } catch (TypeMismatch e) {
            failed = true;
        }
        assertTrue(failed);

        failed = false;
        try {
            FloatField field = (FloatField) Util.getField(3, Type.FLOAT_TYPE, 10);
        } catch (TypeMismatch e) {
            failed = true;
        }
        assertTrue(failed);

        failed = false;
        try {
            Field field = Util.getField(3.3, Type.INT_TYPE, 10);
        } catch (TypeMismatch e) {
            failed = true;
        }
        assertTrue(failed);

    }
}