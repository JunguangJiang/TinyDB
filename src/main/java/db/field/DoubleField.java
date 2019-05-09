package db.field;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Instance of Field that stores a single Double.
 */
public class DoubleField extends NumberField{

    @Override
    public Object getValue() {
        return value.doubleValue();
    }

    /**
     * Constructor.
     *
     * @param i The value of this field.
     */
    public DoubleField(Double i, boolean isNull) {
        super(i, Type.DOUBLE_TYPE, isNull);
    }


    public void serialize(DataOutputStream dos) throws IOException {
        dos.writeDouble(value.doubleValue());
        dos.writeBoolean(isNull);
    }

    @Override
    public boolean equals(Object obj) {
        return (double)getValue() == (double)((Field)obj).getValue();
    }

    @Override
    public boolean greater_than(Field val) {
        return (double)getValue() > (double)val.getValue();
    }

    @Override
    public boolean less_than(Field val) {
        return (double)getValue() < (double)val.getValue();
    }
}
