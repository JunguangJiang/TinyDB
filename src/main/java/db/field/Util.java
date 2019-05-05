package db.field;

import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Util {
    /**
     * When the Field is uncertain, get the most likely Field according to the value
     * @param value might be String, Integer, Float, etc.
     * @return
     */
    public static Field getField(Object value) {
        if (value instanceof Integer) {
            return new IntField((int)value);
        } else if (value instanceof Long) {
            return new LongField((long)value);
        } else if (value instanceof Float) {
            return new FloatField((float)value);
        } else if (value instanceof Double) {
            return new DoubleField((double)value);
        } else if (value instanceof String) {
            return new StringField((String)value, ((String)value).length());
        } else {
            throw new NotImplementedException();
        }
    }

    /**
     *
     * @param value value of the Field, might be Integer, Float or String
     * @param type Type of the Field, might be INT_TYPE, LONG_TYPE, FLOAT_TYPE, DOUBLE_TYPE, STRING_TYPE
     * @param maxLen max length of the StringField
     * @return the Field
     * @throws TypeMismatch when the Type was mismatched
     */
    public static Field getField(Object value, Type type, int maxLen) throws TypeMismatch{
        if (value instanceof Long) {
            switch (type) {
                case INT_TYPE:
                    return new IntField(((Long) value).intValue());
                case LONG_TYPE:
                    return new LongField((long)value);
                default:
                    throw new TypeMismatch(Type.LONG_TYPE, type);
            }
        } else if (value instanceof Integer) {
            switch (type) {
                case INT_TYPE:
                    return new IntField((int)value);
                case LONG_TYPE:
                    return new LongField(((Integer) value).longValue());
                default:
                    throw new TypeMismatch(Type.INT_TYPE, type);
            }
        } else if (value instanceof Double) {
            switch (type) {
                case FLOAT_TYPE:
                    return new FloatField(((Double) value).floatValue());
                case DOUBLE_TYPE:
                    return new DoubleField((double)value);
                default:
                    throw new TypeMismatch(Type.DOUBLE_TYPE, type);
            }
        } else if (value instanceof Float) {
            switch (type) {
                case FLOAT_TYPE:
                    return new FloatField((float)value);
                case DOUBLE_TYPE:
                    return new DoubleField(((Float) value).doubleValue());
                default:
                    throw new TypeMismatch(Type.FLOAT_TYPE, type);
            }
        } else if (value instanceof String) {
            if (type == Type.STRING_TYPE) {
                return new StringField((String)value, maxLen);
            } else {
                throw new TypeMismatch(Type.STRING_TYPE, type);
            }
        } else {
            throw new NotImplementedException();
        }
    }

    /**
     * Create a new tuple which represents operation affecting the table
     * @param count the rows affected
     * @param name the name of the operation
     * @return
     */
    public static Tuple getCountTuple(int count, String name) {
        TDItem[] tdItems = {
                new TDItem(Type.INT_TYPE, name, false)
        };
        TupleDesc tupleDesc = new TupleDesc(tdItems, null);
        Tuple tuple = new Tuple(tupleDesc);
        Field field = new IntField(count);
        tuple.setField(0, field);
        return tuple;
    }
}
