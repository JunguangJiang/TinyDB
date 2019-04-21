package db;

import db.field.IntField;
import db.field.StringField;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Tuple maintains information about the contents of a tuple. Tuples have a
 * specified schema specified by a TupleDesc object and contain Field objects
 * with the data for each field.
 */
public class Tuple implements Serializable {

    private static final long serialVersionUID = 1L;

    private TupleDesc tupleDesc;
    private Field[] fields;
    private RecordId recordId;


    /**
     * Create a new tuple with the specified schema (type).
     *
     * @param td
     *            the schema of this tuple. It must be a valid TupleDesc
     *            instance with at least one field.
     */
    public Tuple(TupleDesc td) {
        tupleDesc = td;
        int length = td.numFields();
        fields = new Field[length];
        for(int i=0; i<length; i++){

            switch (td.getFieldType(i)){
                case INT_TYPE:
                    fields[i] = new IntField(0);
                    break;
                case STRING_TYPE:
                    fields[i] = new StringField("", Type.STRING_LEN);
                    break;
                // TODO other type
                default:
                    throw new NotImplementedException();
            }
        }
    }

    /**
     * @return The TupleDesc representing the schema of this tuple.
     */
    public TupleDesc getTupleDesc() {
        return tupleDesc;
    }

    /**
     * @return The RecordId representing the location of this tuple on disk. May
     *         be null.
     */
    public RecordId getRecordId() {
        return recordId;
    }

    /**
     * Set the RecordId information for this tuple.
     *
     * @param rid
     *            the new RecordId for this tuple.
     */
    public void setRecordId(RecordId rid) {
        recordId = rid;
    }

    /**
     * Change the value of the ith field of this tuple.
     *
     * @param i
     *            index of the field to change. It must be a valid index.
     * @param f
     *            new value for the field.
     */
    public void setField(int i, Field f) {
        fields[i] = f;
    }

    /**
     * @return the value of the ith field, or null if it has not been set.
     *
     * @param i
     *            field index to return. Must be a valid index.
     */
    public Field getField(int i) {
        return fields[i];
    }

    /**
     * Returns the contents of this Tuple as a string. Note that to pass the
     * system tests, the format needs to be as follows:
     *
     * column1\tcolumn2\tcolumn3\t...\tcolumnN
     *
     * where \t is any whitespace (except a newline)
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for(Field field : fields){
            stringBuffer.append(field.toString());
            stringBuffer.append(" ");
        }
        return stringBuffer.toString().trim();
    }

    /**
     * @return
     *        An iterator which iterates over all the fields of this tuple
     * */
    public Iterator<Field> fields()
    {
        ArrayList<Field> fieldArrayList = new ArrayList<>(Arrays.asList(fields));
        return fieldArrayList.iterator();
    }

    /**
     * reset the TupleDesc of this tuple (only affecting the TupleDesc)
     * */
    public void resetTupleDesc(TupleDesc td)
    {
        tupleDesc = td;
    }

    /**
     * merge two tuples into one tuple
     * @param tuple1
     * @param tuple2
     * @return
     */
    public static Tuple merge(Tuple tuple1, Tuple tuple2) {
        TupleDesc tupleDesc1 = tuple1.getTupleDesc(), tupleDesc2 = tuple2.getTupleDesc();
        TupleDesc tupleDesc = TupleDesc.merge(tupleDesc1, tupleDesc2);
        Tuple tuple = new Tuple(tupleDesc);
        int i;
        for (i = 0; i < tupleDesc1.numFields(); i++) {
            tuple.setField(i, tuple1.getField(i));
        }

        for(int j = 0; j < tupleDesc2.numFields(); j++, i++) {
            tuple.setField(i, tuple2.getField(j));
        }
        return tuple;
    }
}
