package db.query;

import db.DbException;
import db.GlobalManager;
import db.field.Field;
import db.field.IntField;
import db.field.Type;
import db.field.TypeMismatch;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

/**
 * The delete operator. Delete reads tuples from its child operator and removes
 * them from the table they belong to.
 */
public class Delete extends Operator{
    private static final long serialVersionUID = 1L;
    private OpIterator child;
    private TupleDesc tupleDesc;
    private boolean fetched;

    /**
     * @param child
     *            The child operator from which to read tuples for deletion
     */
    public Delete(OpIterator child) {
        this.fetched = false;
        this.child = child;
        TDItem[] tdItems = {
                new TDItem(Type.INT_TYPE, "number of deleted records", false)
        };
        tupleDesc = new TupleDesc(tdItems, null);
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

    /**
     * Deletes tuples as they are read from the child operator. Deletes are
     * processed via the buffer pool
     *
     * @return A 1-field tuple containing the number of deleted records.
     */
    @Override
    protected Tuple fetchNext() throws DbException, TypeMismatch {
        Tuple tuple = new Tuple(tupleDesc);
        int count = 0;
        if (fetched) {
           return null;
        } else {
            fetched = true;
            while (child.hasNext()) {
                Tuple c = child.next();
                GlobalManager.getBufferPool().deleteTuple(c);
                count++;
            }
            Field field = new IntField(count);
            tuple.setField(0, field);
        }
        return tuple;
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
