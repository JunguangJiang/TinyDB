package db.tuple;

import db.field.*;
import db.file.RecordId;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

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
            switch (td.getTDItem(i).fieldType){
                case INT_TYPE:
                    fields[i] = new IntField(0, true);
                    break;
                case LONG_TYPE:
                    fields[i] = new LongField(0, true);
                    break;
                case FLOAT_TYPE:
                    fields[i] = new FloatField(0.0f, true);
                    break;
                case DOUBLE_TYPE:
                    fields[i] = new DoubleField(0.0, true);
                    break;
                case STRING_TYPE:
                    fields[i] = new StringField("", td.getTDItem(i).maxLen, true);
                    break;
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

    public void serialize(DataOutputStream dos) throws IOException{
        for (Field field: fields) {
            field.serialize(dos);
        }
    }
}
