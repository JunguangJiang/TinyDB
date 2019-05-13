package db.field;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Instance of Field that stores a single Float.
 */
public class FloatField extends NumberField{

    @Override
    public Object getValue() {
        return value.floatValue();
    }

    /**
     * Constructor.
     *
     * @param i The value of this field.
     */
    public FloatField(float i, boolean isNull) {
        super(i, Type.FLOAT_TYPE, isNull);
    }

    public FloatField(float i) {
        this(i, false);
    }


    public void serialize(DataOutputStream dos) throws IOException {
        dos.writeFloat(value.floatValue());
        dos.writeBoolean(isNull);
    }

    @Override
    public boolean equals(Object obj) {
        return (float)getValue() == (float)((Field)obj).getValue();
    }

    @Override
    public boolean greater_than(Field val) {
        return (float)getValue() > (float)val.getValue();
    }

    @Override
    public boolean less_than(Field val) {
        return (float) getValue() < (float) val.getValue();
    }
}
