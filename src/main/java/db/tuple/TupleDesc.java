package db.tuple;

import db.query.FullColumnName;

import java.io.Serializable;
import java.util.*;

/**
 * TupleDesc describes the schema of a tuple.
 */
public class TupleDesc implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private TDItem[] tdItems;
    private String[] primaryKeys;
    private int[] primaryKeysIndex;

    public TupleDesc() {
        tdItems = new TDItem[0];
        primaryKeys = new String[0];
        primaryKeysIndex = new int[0];
    }

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
                    tdItems[i].isPrimaryKey = true;
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
    public Iterator<TDItem> iterator() {
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
    public TDItem getTDItem(int i) throws NoSuchElementException {
        if (i < 0 || i >= tdItems.length) {
            throw new NoSuchElementException("index " + i);
        }
        return tdItems[i];
    }

    public String[] getAttrNames() {
        String[] attrNames = new String[numFields()];
        for (int i=0; i<numFields(); i++) {
            attrNames[i] = getTDItem(i).fieldName;
        }
        return attrNames;
    }

    /**
     * @return primaryKeys
     */
    public String[] getPrimaryKeys(){
        return this.primaryKeys;
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
            if (tdItems[i].hasName(name)) {
                return i;
            }
        }
        throw new NoSuchElementException(String.format("FullColumnName %s doesn't exist.", name));
    }

    /**
     *  Find the index of the field with a given tableName and attrName.
     * @param tableName name of the Table
     * @param attrName name of the FullColumnName
     * @return the index of the field that is first to have the given tableName and attrName.
     * @throws NoSuchElementException
     *          if no field with a matching name is found
     */
    public int fieldNameToIndex(String tableName, String attrName) throws NoSuchElementException {
        for (int i=0; i<tdItems.length; i++) {
            if (tdItems[i].hasName(tableName, attrName)) {
                return i;
            }
        }
        throw new NoSuchElementException(String.format("Table %s attribute %s doesn't exist.", tableName, attrName));
    }

    /**
     * Find the index of the Field with a given FullColumnName
     * @param fullColumnName
     * @return
     */
    public int fullColunmnNameToIndex(FullColumnName fullColumnName) {
        for (int i=0; i<tdItems.length; i++) {
            System.out.println("tdItem:"+tdItems[i].tableName+" "+tdItems[i].fieldName);
            System.out.println("fullColumnName:"+fullColumnName.tableName+" "+fullColumnName.attrName);
            System.out.println(tdItems[i].hasName(fullColumnName.tableName, fullColumnName.attrName));
            if (tdItems[i].hasName(fullColumnName.tableName, fullColumnName.attrName)) {
                return i;
            }
        }
        throw new NoSuchElementException(String.format("Table %s attribute %s doesn't exist.", fullColumnName.tableName, fullColumnName.attrName));
    }

    /**
     * set the tableName of all the tdItems, which can distinguish TDItem after the join operation.
     * @param tableName
     */
    public void setTableName(String tableName) {
        for (int i=0; i<tdItems.length; i++) {
            tdItems[i].tableName = tableName;
        }
    }

    /**
     * @return The size (in bytes) of tuples corresponding to this TupleDesc.
     *         Note that tuples from a given TupleDesc are of a fixed size.
     */
    public int getSize() {
        int size = 0;
        for (TDItem tdItem : tdItems){
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
            tdItems[i] = td1.getTDItem(i);
        }
        for (int i=0, j=td1.numFields(); i<td2.numFields(); i++, j++) {
            tdItems[j] = td2.getTDItem(i);
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
                if(!getTDItem(i).equals(tupleDesc.getTDItem(i))){
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

    /**
     * @param attrName
     * @return whether attrName is the primary key of the Table
     */
    public boolean isPrimaryKey(String attrName) {
        return Arrays.asList(this.getPrimaryKeys()).contains(attrName);
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
        stringBuilder.append(" PRIMARY KEY(");
        for (int i=0; i<primaryKeys.length; i++) {
            stringBuilder.append(primaryKeys[i]);
            if (i < primaryKeys.length - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}

