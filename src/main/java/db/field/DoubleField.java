package db.field;

import db.Field;
import db.Type;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Instance of Field that stores a single Double.
 */
public class DoubleField extends NumberField{

    public Double getValue() {
        return value.doubleValue();
    }

    /**
     * Constructor.
     *
     * @param i The value of this field.
     */
    public DoubleField(Double i) {
        super(i, Type.DOUBLE_TYPE);
    }


    public void serialize(DataOutputStream dos) throws IOException {
        dos.writeDouble(value.doubleValue());
    }

    @Override
    public boolean equals(Object obj) {
        return value.doubleValue() == ((DoubleField)obj).value.doubleValue();
    }

    @Override
    public boolean greater_than(Field val) {
        return value.doubleValue() > ((DoubleField)val).value.doubleValue();
    }

    @Override
    public boolean less_than(Field val) {
        return value.doubleValue() < ((DoubleField)val).value.doubleValue();
    }
}
