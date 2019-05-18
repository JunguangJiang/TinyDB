package db.field;
import java.io.File;

/**
 * Class of Field that stores a single Number.
 * Number might be Int, Long, Float and Double
 */
public abstract class NumberField implements Field {
    private static final long serialVersionUID = 1L;

    protected final Number value;
    protected final Type type;
    protected boolean isNull;

    /**
     * Constructor.
     *
     * @param value The value of this field.
     * @param type The type of this field
     */
    public NumberField(Number value, Type type, boolean isNull) {
        this.value = value;
        this.type = type;
        this.isNull = isNull;
    }

    @Override
    public String toString() {
        if (isNull) {
            return "null";
        } else {
            return value.toString();
        }
    }

    @Override
    public boolean isNull() {
        return isNull;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public abstract boolean greater_than(Field val);
    public abstract boolean less_than(Field val);

    /**
     * Compare the specified field to the value of this Field.
     * Return semantics are as specified by Field.compare
     *
     * @see Field#compare
     */
    @Override
    public boolean compare(Op op, Field val) {
        if (this.isNull || val.isNull()) {
            return op == Op.NOT_EQUALS;
        }
        switch (op) {
            case EQUALS:
                return value.equals(((NumberField)val).value);
            case NOT_EQUALS:
                return !value.equals(((NumberField)val).value);
            case GREATER_THAN:
                return greater_than(val);
            case GREATER_THAN_OR_EQ:
                return !less_than(val);
            case LESS_THAN:
                return less_than(val);
            case LESS_THAN_OR_EQ:
                return !greater_than(val);
        }
        return false;
    }

    /**
     * @return the Type of this field.
     */
    public Type getType() {
        return type;
    }
}
