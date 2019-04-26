package db;

import db.Type;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;

import java.io.DataInputStream;
import java.io.Serializable;
import java.sql.Array;
import java.text.ParseException;
import java.util.*;

/**
 * TupleDesc describes the schema of a tuple.
 */
public class TupleDesc implements Serializable {

    /**
     * A help class to facilitate organizing the information of each field
     * */
    public static class TDItem implements Serializable {

        private static final long serialVersionUID = 1L;

        /** The type of the field **/
        public final db.Type fieldType;

        /** The name of the field **/
        public final String fieldName;

        /** Can not be Null **/
        public final boolean notNull;

        /** When the Type is String, maxLen is the max length of the String **/
        public final int maxLen;

        /**
         * @param type The type of the field
         * @param name The name of the field
         * @param notNull Can not be Null
         * @param maxLen When the Type is String, maxLen is the max length of the String
         */
        public TDItem(db.Type type, String name, boolean notNull, int maxLen) {
            this.fieldName = name;
            this.fieldType = type;
            this.notNull = notNull;
            if (type == Type.STRING_TYPE) {
                this.maxLen = maxLen;
            } else {
                this.maxLen = 0;
            }
        }

        public TDItem(db.Type type, String name, boolean notNull) {
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

    private static final long serialVersionUID = 1L;
    private TDItem[] tdItems;
    private String[] primaryKeys;
    private int[] primaryKeysIndex;

    /**
     *
     * @param tdItems an array of TDItem
     * @param primaryKeys an array of primary key String
     */
    public TupleDesc(TDItem[] tdItems, String[] primaryKeys) {
        this.tdItems = tdItems.clone();
        if (primaryKeys != null) {
            this.primaryKeys = primaryKeys.clone();

            // get the positions of each primary key in the tuple descriptor
            ArrayList<Integer> primaryKeysIndexList = new ArrayList<>();
            List<String> primaryKeyList = Arrays.asList(primaryKeys);
            for(int i=0; i<tdItems.length; i++) {
                if (primaryKeyList.contains(tdItems[i].fieldName)){
                    primaryKeysIndexList.add(i);
                }
            }
            this.primaryKeysIndex = primaryKeysIndexList.stream().mapToInt(i->i).toArray();
        } else {
            this.primaryKeys = new String[0];
            this.primaryKeysIndex = new int[0];
        }
    }

    /**
     * @return
     *        An iterator which iterates over all the field TDItems
     *        that are included in this TupleDesc
     * */
    public Iterator<db.TupleDesc.TDItem> iterator() {
        return Arrays.asList(tdItems).iterator();
    }


    /**
     * @return the number of fields in this TupleDesc
     */
    public int numFields() {
        return tdItems.length;
    }

    /**
     * Gets the (possibly null) ith TDItem of this TupleDesc.
     *
     * @param i
     *            index of the field to return. It must be a valid index.
     * @return the ith field
     * @throws NoSuchElementException
     *             if i is not a valid field reference.
     */
    public TDItem getField(int i) throws NoSuchElementException {
        if (i < 0 || i >= tdItems.length) {
            throw new NoSuchElementException("index " + i);
        }
        return tdItems[i];
    }

    /**
     * Find the index of the field with a given name.
     *
     * @param name
     *            name of the field.
     * @return the index of the field that is first to have the given name.
     * @throws NoSuchElementException
     *             if no field with a matching name is found.
     */
    public int fieldNameToIndex(String name) throws NoSuchElementException {
        for(int i=0; i<tdItems.length; i++){
            String fieldName = tdItems[i].fieldName;
            if (fieldName != null && fieldName.equals(name)){
                return i;
            }
        }
        throw new NoSuchElementException(String.format("name %s", name));
    }

    /**
     * @return The size (in bytes) of tuples corresponding to this TupleDesc.
     *         Note that tuples from a given TupleDesc are of a fixed size.
     */
    public int getSize() {
        int size = 0;
        for (db.TupleDesc.TDItem tdItem : tdItems){
            size += tdItem.getBytes();
        }
        return size;
    }

    /**
     * Merge two TupleDescs into one, with td1.numFields + td2.numFields fields,
     * with the first td1.numFields coming from td1 and the remaining from td2.
     *
     * @param td1
     *            The TupleDesc with the first fields of the new TupleDesc
     * @param td2
     *            The TupleDesc with the last fields of the TupleDesc
     * @return the new TupleDesc
     */
    public static TupleDesc merge(TupleDesc td1, TupleDesc td2) {
        int numFields = td1.numFields() + td2.numFields();
        TDItem[] tdItems = new TDItem[numFields];
        for (int i=0; i<td1.numFields(); i++) {
            tdItems[i] = td1.getField(i);
        }
        for (int i=0, j=td1.numFields(); i<td2.numFields(); i++, j++) {
            tdItems[j] = td2.getField(i);
        }
        return new TupleDesc(tdItems, null);
    }

    /**
     * Compares the specified object with this TupleDesc for equality. Two
     * TupleDescs are considered equal if
     *      they have the same number of items
     *      and if the i-th type in this TupleDesc is equal to the i-th type for every i
     *      and if the i-th notNull/primary key in this TupleDesc is equal to the i-th notNull/primary key for every i
     *
     * @param o
     *            the Object to be compared for equality with this TupleDesc.
     * @return true if the object is equal to this TupleDesc.
     */

    public boolean equals(Object o) {
        TupleDesc tupleDesc = (TupleDesc)o;
        try {
            if(this.numFields() != tupleDesc.numFields()){
                return false;
            }
            for(int i=0; i<this.numFields(); i++){
                if(!getField(i).equals(tupleDesc.getField(i))){
                    return false;
                }
            }
            return Arrays.equals(primaryKeysIndex, tupleDesc.primaryKeysIndex);
        }catch (NullPointerException | ClassCastException e){
            return false;
        }
    }

    /**
     * @return the positions of each primary key in the tuple descriptor
     */
    public int[] getPrimaryKeysIndex() {
        return primaryKeysIndex;
    }

    public int hashCode() {
        // If you want to use TupleDesc as keys for HashMap, implement this so
        // that equal objects have equals hashCode() results
        throw new UnsupportedOperationException("unimplemented");
    }

    /**
     * Returns a String describing this descriptor. It should be of the form
     * "fieldType[0](fieldName[0]), ..., fieldType[M](fieldName[M])", although
     * the exact format does not matter.
     *
     * @return String describing this descriptor.
     */
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0; i<numFields(); i++){
            stringBuilder.append(tdItems[i].toString());
            if(i < numFields()-1){
                stringBuilder.append(',');
            }
        }
        return stringBuilder.toString();
    }
}

