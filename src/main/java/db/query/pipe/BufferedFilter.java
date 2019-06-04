package db.query.pipe;

import db.DbException;
import db.Setting;
import db.error.SQLError;
import db.file.TupleBuffer;
import db.query.predicate.Predicate;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.File;

/**
 * BufferedFilter is an operator that store the filter result in TupleBuffer
 */
public class BufferedFilter extends Operator{
    private static final long serialVersionUID = 1L;
    private Predicate predicate;
    private OpIterator child;
    private TupleBuffer tupleBuffer;

    /**
     * Constructor accepts a predicate to apply and a child operator to read
     * tuples to filter from.
     *
     * @param predicate
     *            The predicate to filter tuples with
     * @param child
     *            The child operator
     */
    public BufferedFilter(Predicate predicate, OpIterator child) throws DbException{
        this.predicate = predicate;
        this.child = child;
        this.tupleBuffer = new TupleBuffer(Setting.MAX_MEMORY_BYTES_FOR_FILTER_BUFFER,
                new File(getTupleDesc().getTableName()+":filter.db"), getTupleDesc());
    }

    @Override
    public TupleDesc getTupleDesc() {
        return child.getTupleDesc();
    }

    @Override
    public void open() throws DbException, SQLError {
        child.open();
        super.open();
        while (child.hasNext()) {
            Tuple tuple = child.next();
            if (this.predicate.filter(tuple)) {
                tupleBuffer.add(tuple);
            }
        }
        tupleBuffer.finisheAdding();
        child.close();
    }

    @Override
    public void close() {
        super.close();
        tupleBuffer.close();
    }

    @Override
    public void rewind() throws DbException {
        tupleBuffer.rewind();
    }

    /**
     * Iterates over tuples from the
     * child operator, applying the predicate to them and returning those that
     * pass the predicate (i.e. for which the Predicate.filter() returns true.)
     *
     * @return The next tuple that passes the filter, or null if there are no
     *         more tuples
     */
    @Override
    protected Tuple fetchNext() throws DbException, SQLError{
        return tupleBuffer.next();
    }

    @Override
    public long count() {
        return tupleBuffer.getTupleNum();
    }
}
