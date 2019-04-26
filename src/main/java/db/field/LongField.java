package db.field;

import db.Field;
import db.Type;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Instance of Field that stores a single Long.
 */
public class LongField extends NumberField{

    public long getValue() {
        return value.longValue();
    }

    /**
     * Constructor.
     *
     * @param i The value of this field.
     */
    public LongField(long i) {
        super(i, Type.LONG_TYPE);
    }


    public void serialize(DataOutputStream dos) throws IOException {
        dos.writeLong(value.longValue());
    }

    @Override
    public boolean equals(Object obj) {
        return value.longValue() == ((LongField)obj).value.longValue();
    }

    @Override
    public boolean greater_than(Field val) {
        return value.longValue() > ((LongField)val).value.longValue();
    }

    @Override
    public boolean less_than(Field val) {
        return value.longValue() < ((LongField)val).value.longValue();
    }
}
