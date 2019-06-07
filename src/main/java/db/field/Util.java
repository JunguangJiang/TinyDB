package db.field;

import db.error.SQLError;

import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Util {
    /**
     * whether value represents a String
     * @param value
     * @return
     */
    private static boolean isString(String value) {
        return (value.startsWith("'") && value.endsWith("'")) ||
                (value.startsWith("\"") && value.endsWith("\""));
    }

    /**
     * whether value represents NULL
     * @param value
     * @return
     */
    public static boolean isNull(String value) {
        return value == null || value.toUpperCase().equals("NULL");
    }

    /**
     * When the Field is uncertain, get the most likely Field according to the value
     * @param value
     */
    public static Field getField(String value) {
        if (isString(value)){
            return new StringField(value.substring(1, value.length()-1), value.length()-2, false);
        }
        try {
            return new DoubleField(Double.parseDouble(value), false);
        } catch (NumberFormatException e){
            try {
                return new LongField(Long.valueOf(value), false);
            } catch (NumberFormatException e2){
                throw new NotImplementedException();
            }
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
     * @throws SQLError when the Type was mismatched
     */
    public static Field getField(String value, TDItem tdItem) throws SQLError {
        return getField(value, tdItem.fieldType, tdItem.maxLen, tdItem.fieldName);
    }

    /**
     *
     * @param value value of the Field
     * @param type Type of the Field, might be INT_TYPE, LONG_TYPE, FLOAT_TYPE, DOUBLE_TYPE, STRING_TYPE
     * @param maxLen max length of the StringField
     * @param name the name of the Field
     * @return the Field
     * @throws SQLError when the Type was mismatched
     */
    public static Field getField(String value, Type type, int maxLen, String name) throws SQLError{
        if (isNull(value)) {
            return getNullField(type, maxLen);
        }
        try {
            switch (type) {
                case INT_TYPE:
                    return new IntField(Integer.valueOf(value), false);
                case LONG_TYPE:
                    return new LongField(Long.valueOf(value),false);
                case FLOAT_TYPE:
                    return new FloatField(Float.valueOf(value), false);
                case DOUBLE_TYPE:
                    return new DoubleField(Double.valueOf(value), false);
                case STRING_TYPE:
                    if (isString(value)) {
                        value = value.substring(1, value.length()-1);
                        if (value.length() > maxLen) {
                            throw new SQLError("Out of range value for column " + name);
                        }
                        return new StringField(value, maxLen);
                    }
                    throw new SQLError("Column " + name + " expected " + type + " yet get " + value);
                default:
                    throw new NotImplementedException();
            }
        } catch (NumberFormatException e) {
            throw new SQLError("Column " + name + " expected " + type + " yet get " + value);
        }

    }

    /**
     * Create a new tuple which represents operation affecting the table
     * @param count the rows affected
     * @param name the name of the operation
     * @return
     */
    public static Tuple getCountTuple(long count, String name) {
        TDItem[] tdItems = {
                new TDItem(Type.INT_TYPE, name, false)
        };
        TupleDesc tupleDesc = new TupleDesc(tdItems, null);
        Tuple tuple = new Tuple(tupleDesc);
        Field field = new LongField(count, false);
        tuple.setField(0, field);
        return tuple;
    }

    /**
     * check if there exists null field in the expression
     * @param lhs
     * @param op
     * @param rhs
     * @return null if op is not 'IS', 'IS_NOT' and there are no "NULL" field.
     *          else
     *              true if the expression is true
     *              false if the expression is false
     */
    static Boolean checkNullCompare(Field lhs, Op op, Field rhs) {
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
