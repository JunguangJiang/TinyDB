package db.field;

import db.Field;
import db.Type;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Instance of Field that stores a single Float.
 */
public class FloatField extends NumberField{

    public float getValue() {
        return value.floatValue();
    }

    /**
     * Constructor.
     *
     * @param i The value of this field.
     */
    public FloatField(float i) {
        super(i, Type.FLOAT_TYPE);
    }


    public void serialize(DataOutputStream dos) throws IOException {
        dos.writeFloat(value.floatValue());
    }

    @Override
    public boolean equals(Object obj) {
        return value.floatValue() == ((FloatField)obj).value.floatValue();
    }

    @Override
    public boolean greater_than(Field val) {
        return value.floatValue() > ((FloatField)val).value.floatValue();
    }

    @Override
    public boolean less_than(Field val) {
        return value.floatValue() < ((FloatField)val).value.floatValue();
    }
}
