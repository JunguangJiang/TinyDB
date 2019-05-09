package db.field;

import java.io.*;

/**
 * Instance of Field that stores a single Int.
 */
public class IntField extends NumberField {

    public int getValue() {
        return value.intValue();
    }

    /**
     * Constructor.
     *
     * @param i The value of this field.
     */
    public IntField(int i, boolean isNull) {
        super(i, Type.INT_TYPE, isNull);
    }


    public void serialize(DataOutputStream dos) throws IOException {
        dos.writeInt(value.intValue());
        dos.writeBoolean(isNull);
    }

    @Override
    public boolean equals(Object obj) {
        return value.intValue() == ((IntField)obj).value.intValue();
    }

    @Override
    public boolean greater_than(Field val) {
        return value.intValue() > ((IntField)val).value.intValue();
    }

    @Override
    public boolean less_than(Field val) {
        return value.intValue() < ((IntField)val).value.intValue();
    }
}
