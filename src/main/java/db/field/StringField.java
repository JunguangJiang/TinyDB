package db.field;

import db.query.ComparisonPredicate;

import java.io.*;

/**
 * Instance of Field that stores a single String of a certain length.
 */
public class StringField implements Field {

    private static final long serialVersionUID = 1L;

    private final String value;
    private final int maxLen;
    private boolean isNull;

    public String getValue() {
        return value;
    }

    /**
     * Constructor.
     *
     * @param s
     *            The value of this field.
     * @param maxLen
     *            The maximum size of this string
     */
    public StringField(String s, int maxLen, boolean isNull) {
        this.maxLen = maxLen;
        this.isNull = isNull;

        if (s.length() > maxLen)
            value = s.substring(0, maxLen);
        else
            value = s;
    }

    public String toString() {
        if (isNull) {
            return "null";
        } else {
            return value;
        }
    }

    @Override
    public boolean isNull() {
        return isNull;
    }

    public int hashCode() {
        return value.hashCode();
    }

    public boolean equals(Object field) {
        return ((StringField) field).value.equals(value);
    }

    /**
     * Write this string to dos. Always writes maxLen + 4 bytes to the passed
     * in dos. First four bytes are string length, next bytes are string, with
     * remainder padded with 0 to maxLen.
     *
     * @param dos
     *            Where the string is written
     */
    public void serialize(DataOutputStream dos) throws IOException {
        String s = value;
        int overflow = maxLen - s.length();
        if (overflow < 0) {
            s = s.substring(0, maxLen);
        }
        dos.writeInt(s.length());
        dos.writeBytes(s);
        while (overflow-- > 0)
            dos.write((byte) 0);
        dos.writeBoolean(isNull);
    }

    /**
     * Compare the specified field to the value of this Field. Return semantics
     * are as specified by Field.compare
     *
     * @see Field#compare
     */
    public boolean compare(ComparisonPredicate.Op op, Field val) {
        if (this.isNull || val.isNull()) {
            return op == ComparisonPredicate.Op.NOT_EQUALS;
        }

        StringField iVal = (StringField) val;
        int cmpVal = value.compareTo(iVal.value);

        switch (op) {
            case EQUALS:
                return cmpVal == 0;

            case NOT_EQUALS:
                return cmpVal != 0;

            case GREATER_THAN:
                return cmpVal > 0;

            case GREATER_THAN_OR_EQ:
                return cmpVal >= 0;

            case LESS_THAN:
                return cmpVal < 0;

            case LESS_THAN_OR_EQ:
                return cmpVal <= 0;
        }

        return false;
    }

    /**
     * @return the Type for this Field
     */
    public Type getType() {

        return Type.STRING_TYPE;
    }
}
