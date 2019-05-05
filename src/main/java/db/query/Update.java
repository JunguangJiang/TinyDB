package db.query;

import db.DbException;
import db.GlobalManager;
import db.field.TypeMismatch;
import db.field.Util;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

/**
 * Project is an operator that implements updating.
 */
public class Update extends Operator{
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
    private UpdateElement[] updateElements;
    private int indexes[];
    private Object values[];

    public Update(OpIterator child, UpdateElement[] updateElements) {
        this.child = child;
        this.indexes = new int[updateElements.length];
        this.values = new Object[updateElements.length];
        TupleDesc tupleDesc = child.getTupleDesc();
        for (int i=0; i<updateElements.length; i++){
            this.indexes[i] = tupleDesc.fieldNameToIndex(updateElements[i].attribute);
            this.values[i] = updateElements[i].value;
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
    protected Tuple fetchNext() throws DbException, TypeMismatch {
        int count = 0;
        while (child.hasNext()) {
            count++;
            Tuple tuple = child.next();
            // TODO make a new tuple, delete the old tuple and insert the new tuple
            int tableid = tuple.getRecordId().getPageId().getTableId();
            GlobalManager.getBufferPool().deleteTuple(tuple);

            TupleDesc tupleDesc = tuple.getTupleDesc();
            for (int i=0; i<indexes.length; i++){
                TDItem tdItem = tupleDesc.getField(i);
                tuple.setField(indexes[i], Util.getField(values[i], tdItem.fieldType, tdItem.maxLen));
            }
            try {
                GlobalManager.getBufferPool().insertTuple(tableid, tuple);
            } catch (Exception e) {
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
