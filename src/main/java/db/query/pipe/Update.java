package db.query.pipe;

import db.DbException;
import db.GlobalManager;
import db.field.Field;
import db.field.TypeMismatch;
import db.field.Util;
import db.file.PrimaryKeyViolation;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.IOException;

/**
 * Update is an operator that implements updating.
 */
public class Update extends Operator{

    /**
     * For sql "set attribute=value",
     * we have UpdateElement(attribute, value)
     */
    public static class UpdateElement {
        public String attribute;
        public Object value;
        public UpdateElement(String attribute, Object value) {
            this.attribute = attribute;
            this.value = value;
        }
    }

    private static final long serialVersionUID = 1L;
    private OpIterator child;
    private int indexes[]; //index of the attributes that needs updating
    private Field fields[]; //the corresponding updating fields

    public Update(OpIterator child, UpdateElement[] updateElements) throws TypeMismatch{
        this.child = child;
        this.indexes = new int[updateElements.length];
        this.fields = new Field[updateElements.length];
        TupleDesc tupleDesc = child.getTupleDesc();
        for (int i=0; i<updateElements.length; i++){
            this.indexes[i] = tupleDesc.fieldNameToIndex(updateElements[i].attribute);
            TDItem tdItem = tupleDesc.getTDItem(indexes[i]);
            this.fields[i] = Util.getField(updateElements[i].value, tdItem.fieldType,
                    tdItem.maxLen, tdItem.fieldName);
        }
    }

    @Override
    public TupleDesc getTupleDesc() {
        return child.getTupleDesc();
    }

    @Override
    public void open() throws DbException, TypeMismatch {
        child.open();
        super.open();
    }

    @Override
    public void close() {
        super.close();
        child.close();
    }

    @Override
    public void rewind() throws DbException {
        child.rewind();
    }

    @Override
    protected Tuple fetchNext() throws DbException, TypeMismatch, PrimaryKeyViolation {
        int count = 0;
        while (child.hasNext()) {
            count++;
            Tuple tuple = child.next();
            //make a new tuple, delete the old tuple and insert the new tuple
            int tableid = tuple.getRecordId().getPageId().getTableId();
            GlobalManager.getBufferPool().deleteTuple(tuple);

            for (int i=0; i<indexes.length; i++){
                tuple.setField(indexes[i], fields[i]);
            }
            try {
                GlobalManager.getBufferPool().insertTuple(tableid, tuple);
            } catch (DbException | IOException e) {
                e.printStackTrace();
            }
        }
        return Util.getCountTuple(count, "update counts");
    }

    @Override
    public OpIterator[] getChildren() {
        return new OpIterator[]{child};
    }

    @Override
    public void setChildren(OpIterator[] children) {
        child = children[0];
    }
}
