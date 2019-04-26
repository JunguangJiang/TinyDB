package db.field;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Util {
    /**
     * When the Field is uncertain, get the most likely Field according to the value
     * @param value might be String, Integer, Float
     * @return
     */
    public static Field getField(Object value) {
        // TODO
        return null;
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
                    throw new TypeMismatch(Type.INT_TYPE, type);
            }
        } else if (value instanceof Double) {
            switch (type) {
                case FLOAT_TYPE:
                    return new FloatField(((Double) value).floatValue());
                case DOUBLE_TYPE:
                    return new DoubleField((double)value);
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
}
