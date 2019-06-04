package db.field;

import db.error.TypeMismatch;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Util {
    /**
     * When the Field is uncertain, get the most likely Field according to the value
     * @param value might be String, Integer, Float, etc.
     */
    public static Field getField(Object value) {
        if (value instanceof Integer) {
            return new IntField((int)value, false);
        } else if (value instanceof Long) {
            return new LongField((long)value, false);
        } else if (value instanceof Float) {
            return new FloatField((float)value, false);
        } else if (value instanceof Double) {
            return new DoubleField((double)value, false);
        } else if (value instanceof String) {
            return new StringField((String)value, ((String)value).length(), false);
        } else {
            throw new NotImplementedException();
        }
    }

    /**
     * @param type a given Type
     * @param maxLen
     * @return a null Field
     */
    public static Field getNullField(Type type, int maxLen) {
        switch (type) {
            case INT_TYPE:
                return new IntField(0, true);
            case LONG_TYPE:
                return new LongField(0,true);
            case FLOAT_TYPE:
                return new FloatField(0.0f, true);
            case DOUBLE_TYPE:
                return new DoubleField(0.0,true);
            case STRING_TYPE:
                return new StringField("", maxLen, true);
            default:
                throw new NotImplementedException();
        }
    }

    /**
     * @param value value of the Field, might be Integer, Float or String
     * @param tdItem TDItem describing the Field
     * @return the Field
     * @throws TypeMismatch when the Type was mismatched
     */
    public static Field getField(Object value, TDItem tdItem) throws TypeMismatch {
        return getField(value, tdItem.fieldType, tdItem.maxLen, tdItem.fieldName);
    }

    /**
     *
     * @param value value of the Field, might be Integer, Float or String
     * @param type Type of the Field, might be INT_TYPE, LONG_TYPE, FLOAT_TYPE, DOUBLE_TYPE, STRING_TYPE
     * @param maxLen max length of the StringField
     * @param name the name of the Field
     * @return the Field
     * @throws TypeMismatch when the Type was mismatched
     */
    public static Field getField(Object value, Type type, int maxLen, String name) throws TypeMismatch{
        if (value == null) {
            return getNullField(type, maxLen);
        }
        if (value instanceof Long) {
            switch (type) {
                case INT_TYPE:
                    return new IntField(((Long) value).intValue(), false);
                case LONG_TYPE:
                    return new LongField((long)value, false);
                default:
                    throw new TypeMismatch(name, type, Type.LONG_TYPE);
            }
        } else if (value instanceof Integer) {
            switch (type) {
                case INT_TYPE:
                    return new IntField((int)value, false);
                case LONG_TYPE:
                    return new LongField(((Integer) value).longValue(), false);
                default:
                    throw new TypeMismatch(name, type, Type.INT_TYPE);
            }
        } else if (value instanceof Double) {
            switch (type) {
                case FLOAT_TYPE:
                    return new FloatField(((Double) value).floatValue(), false);
                case DOUBLE_TYPE:
                    return new DoubleField((double)value, false);
                default:
                    throw new TypeMismatch(name, type, Type.DOUBLE_TYPE);
            }
        } else if (value instanceof Float) {
            switch (type) {
                case FLOAT_TYPE:
                    return new FloatField((float)value, false);
                case DOUBLE_TYPE:
                    return new DoubleField(((Float) value).doubleValue(), false);
                default:
                    throw new TypeMismatch(name, type, Type.FLOAT_TYPE);
            }
        } else if (value instanceof String) {
            if (type == Type.STRING_TYPE) {
                return new StringField((String)value, maxLen, false);
            } else {
                throw new TypeMismatch(name, type, Type.STRING_TYPE);
            }
        } else {
            throw new NotImplementedException();
        }
    }

    /**
     * @see Util#getField(Object, Type, int, String) */
    public static Field getField(Object value, Type type, int maxLen) throws TypeMismatch{
        return getField(value, type, maxLen, "");
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
        Field field = new IntField(count, false);
        tuple.setField(0, field);
        return tuple;
    }

    public static Boolean checkNullCompare(Field lhs, Op op, Field rhs) {
        switch (op) {
            case IS:
                assert rhs.isNull();
                return lhs.isNull();
            case IS_NOT:
                assert rhs.isNull();
                return !lhs.isNull();
            default:
                if (lhs.isNull() || rhs.isNull()) {
                    return false;
                } else{
                    return null;
                }
        }
    }
}
