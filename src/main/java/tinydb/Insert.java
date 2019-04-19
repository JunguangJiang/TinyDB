package tinydb;

import java.io.IOException;

/**
 * Inserts tuples read from the child operator into the tableId specified in the
 * constructor
 */
public class Insert extends Operator {

    private static final long serialVersionUID = 1L;

    private TransactionId transactionId;
    private OpIterator child;
    private int tableId;
    private boolean fetched = false;
    private TupleDesc tupleDesc;

    /**
     * Constructor.
     *
     * @param t
     *            The transaction running the insert.
     * @param child
     *            The child operator from which to read tuples to be inserted.
     * @param tableId
     *            The table in which to insert tuples.
     * @throws DbException
     *             if TupleDesc of child differs from table into which we are to
     *             insert.
     */
    public Insert(TransactionId t, OpIterator child, int tableId)
            throws DbException {
        this.transactionId = t;
        this.child = child;
        this.tableId = tableId;
        Type[] types = new Type[]{Type.INT_TYPE};
        String[] names=  new String[] {"number of inserted records"};
        this.tupleDesc = new TupleDesc(types, names);
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
     * Inserts tuples read from child into the tableId specified by the
     * constructor. It returns a one field tuple containing the number of
     * inserted records. Inserts should be passed through BufferPool. An
     * instances of BufferPool is available via Database.getBufferPool(). Note
     * that insert DOES NOT need check to see if a particular tuple is a
     * duplicate before inserting it.
     *
     * @return A 1-field tuple containing the number of inserted records, or
     *         null if called more than once.
     * @see Database#getBufferPool
     * @see BufferPool#insertTuple
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
                    try {
                        Database.getBufferPool().insertTuple(transactionId, tableId, c);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
            Field field = new IntField(count);
            tuple.setField(0, field);
        } catch (DbException e){
            e.printStackTrace();
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
