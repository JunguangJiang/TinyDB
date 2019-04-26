package db.tuple;

import db.field.Field;
import db.field.Type;

import java.io.DataInputStream;
import java.io.Serializable;
import java.text.ParseException;

/**
 * A help class to facilitate organizing the information of each field in TupleDesc
 * */
public class TDItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /** The type of the field **/
    public final Type fieldType;

    /** The name of the field **/
    public final String fieldName;

    /** Can not be Null **/
    public boolean notNull;

    /** Is primary key **/
    public boolean isPrimaryKey;

    /** When the Type is String, maxLen is the max length of the String **/
    public final int maxLen;

    /**
     * @param type The type of the field
     * @param name The name of the field
     * @param notNull Can not be Null
     * @param maxLen When the Type is String, maxLen is the max length of the String
     */
    public TDItem(Type type, String name, boolean notNull, int maxLen) {
        this.fieldName = name;
        this.fieldType = type;
        this.notNull = notNull;
        if (type == Type.STRING_TYPE) {
            this.maxLen = maxLen;
        } else {
            this.maxLen = 0;
        }
        isPrimaryKey = false;
    }

    public TDItem(Type type, String name, boolean notNull) {
        this(type, name, notNull, 0);
    }

    /**
     * @return the number of bytes required to store a field of this type.
     */
    public int getBytes() {
        return fieldType.getBytes() + this.maxLen;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(fieldName + "(" + fieldType);
        if (fieldType == Type.STRING_TYPE) {
            stringBuilder.append("(");
            stringBuilder.append(this.maxLen);
            stringBuilder.append(")");
        }
        if (notNull) {
            stringBuilder.append(" not null");
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     * Two TDItems are equal when their
     *      field type
     *      notNull
     *      maxLen (only for StringType)
     * are same.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        TDItem tdItem = (TDItem) obj;
        boolean equal = (fieldType == tdItem.fieldType)
                && (notNull == tdItem.notNull)
                && maxLen == tdItem.maxLen;
        return equal;
    }

    public Field parse(DataInputStream dis) throws ParseException {
        return this.fieldType.parse(dis, maxLen);
    }
}
