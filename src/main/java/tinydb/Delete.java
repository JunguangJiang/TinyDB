package tinydb;

import java.io.IOException;

/**
 * The delete operator. Delete reads tuples from its child operator and removes
 * them from the table they belong to.
 */
public class Delete extends Operator {

    private static final long serialVersionUID = 1L;

    private TransactionId transactionId;
    private OpIterator child;
    private boolean fetched = false;
    private TupleDesc tupleDesc;

    /**
     * Constructor specifying the transaction that this delete belongs to as
     * well as the child to read from.
     * 
     * @param t
     *            The transaction this delete runs in
     * @param child
     *            The child operator from which to read tuples for deletion
     */
    public Delete(TransactionId t, OpIterator child) {
        this.transactionId = t;
        this.child = child;
        Type[] types = new Type[] {Type.INT_TYPE};
        String[] names = new String[] {"number of deleted records"};
        tupleDesc = new TupleDesc(types, names);
    }

    public TupleDesc getTupleDesc() {
        return tupleDesc;
    }

    public void open() throws DbException, TransactionAbortedException {
        child.open();
        super.open();
    }

    public void close() {
        child.close();
        super.close();
    }

    public void rewind() throws DbException, TransactionAbortedException {
        child.rewind();
    }

    /**
     * Deletes tuples as they are read from the child operator. Deletes are
     * processed via the buffer pool (which can be accessed via the
     * Database.getBufferPool() method.
     * 
     * @return A 1-field tuple containing the number of deleted records.
     * @see Database#getBufferPool
     * @see BufferPool#deleteTuple
     */
    protected Tuple fetchNext() throws TransactionAbortedException, DbException {
        Tuple tuple = new Tuple(tupleDesc);
        try {
            int count = 0;
            if (fetched) {
                return null;
            } else {
                fetched = true;
                while (child.hasNext()) {
                    Tuple c = child.next();
                    Database.getBufferPool().deleteTuple(transactionId, c);
                    count++;
                }
                Field field = new IntField(count);
                tuple.setField(0, field);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return tuple;
    }

    @Override
    public OpIterator[] getChildren() {
        return new OpIterator[] {child};
    }

    @Override
    public void setChildren(OpIterator[] children) {
        child = children[0];
    }

}
