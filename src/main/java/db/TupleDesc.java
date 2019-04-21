package db;

import db.Type;

import java.io.Serializable;
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

        public TDItem(db.Type t, String n, boolean notNull) {
            this.fieldName = n;
            this.fieldType = t;
            this.notNull = notNull;
        }

        public String toString() {
            return fieldName + "(" + fieldType + ")";
        }
    }

    /**
     * @return
     *        An iterator which iterates over all the field TDItems
     *        that are included in this TupleDesc
     * */
    public Iterator<db.TupleDesc.TDItem> iterator() {
        return this.tdItems.iterator();
    }

    private static final long serialVersionUID = 1L;

    private ArrayList<TDItem> tdItems;
    private ArrayList<String> primaryKeys;

    public TupleDesc() {
        this.tdItems = new ArrayList<>();
        this.primaryKeys = new ArrayList<>();
    }


    public void addTDItem(db.Type type, String name, boolean notNull) {

    }

    public void addPrimaryKey(String primaryKey) {

    }

    /**
     * @return the number of fields in this TupleDesc
     */
    public int numFields() {
        return tdItems.size();
    }

    /**
     * Gets the (possibly null) field name of the ith field of this TupleDesc.
     *
     * @param i
     *            index of the field name to return. It must be a valid index.
     * @return the name of the ith field
     * @throws NoSuchElementException
     *             if i is not a valid field reference.
     */
    public String getFieldName(int i) throws NoSuchElementException {
//        if(i < 0 || i >= tdItems.length)
//            throw new NoSuchElementException(String.format("index %d", i));
//        return tdItems[i].fieldName;
        return null;
    }

    /**
     * Gets the type of the ith field of this TupleDesc.
     *
     * @param i
     *            The index of the field to get the type of. It must be a valid
     *            index.
     * @return the type of the ith field
     * @throws NoSuchElementException
     *             if i is not a valid field reference.
     */
    public db.Type getFieldType(int i) throws NoSuchElementException {
//        if(i < 0 || i >= tdItems.length)
//            throw new NoSuchElementException(String.format("index %d", i));
//        return tdItems[i].fieldType;
        return null;
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
//        for(int i=0; i<tdItems.length; i++){
//            String fieldName = tdItems[i].fieldName;
//            if (fieldName != null && fieldName.equals(name)){
//                return i;
//            }
//        }
//        throw new NoSuchElementException(String.format("name %s", name));
        return 0;
    }

    /**
     * @return The size (in bytes) of tuples corresponding to this TupleDesc.
     *         Note that tuples from a given TupleDesc are of a fixed size.
     */
    public int getSize() {
//        int size = 0;
//        for (db.TupleDesc.TDItem tdItem : tdItems){
//            size += tdItem.fieldType.getLen();
//        }
//        return size;
        return 0;
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
    public static db.TupleDesc merge(db.TupleDesc td1, db.TupleDesc td2) {
//        int numFields = td1.numFields() + td2.numFields();
//        db.Type[] typeArray = new Type[numFields];
//        String[] nameArray = new String[numFields];
//        for(int i=0; i<td1.numFields(); i++){
//            typeArray[i] = td1.getFieldType(i);
//            nameArray[i] = td1.getFieldName(i);
//        }
//        for(int i=0; i<td2.numFields(); i++){
//            typeArray[i+td1.numFields()] = td2.getFieldType(i);
//            nameArray[i+td1.numFields()] = td2.getFieldName(i);
//        }
//
//        return new db.TupleDesc(typeArray, nameArray);
        return null;
    }

    /**
     * Compares the specified object with this TupleDesc for equality. Two
     * TupleDescs are considered equal if they have the same number of items
     * and if the i-th type in this TupleDesc is equal to the i-th type in o
     * for every i.
     *
     * @param o
     *            the Object to be compared for equality with this TupleDesc.
     * @return true if the object is equal to this TupleDesc.
     */

    public boolean equals(Object o) {
//        try {
//            if(this.numFields() != ((db.TupleDesc) o).numFields()){
//                return false;
//            }
//            for(int i=0; i<this.numFields(); i++){
//                if(this.getFieldType(i) != ((db.TupleDesc) o).getFieldType(i)){
//                    return false;
//                }
//            }
//            return true;
//        }catch (NullPointerException | ClassCastException e){
//            return false;
//        }
        return false;
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
//        StringBuffer stringBuffer=new StringBuffer();
//        for(int i=0; i<numFields(); i++){
//            stringBuffer.append(tdItems[i].toString());
//            if(i < numFields()-1){
//                stringBuffer.append(',');
//            }
//        }
//        return stringBuffer.toString();
        return null;
    }
}

