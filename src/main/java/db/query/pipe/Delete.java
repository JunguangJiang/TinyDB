package db.query.pipe;

import db.DbException;
import db.GlobalManager;
import db.Setting;
import db.error.SQLError;
import db.field.*;
import db.file.TupleBuffer;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.File;

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
    }

    @Override
    public TupleDesc getTupleDesc() {
        return child.getTupleDesc();
    }

    @Override
    public void open() throws DbException, SQLError {
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
    protected Tuple fetchNext() throws DbException, SQLError {
        int count = 0;
        if (fetched) {
           return null;
        } else {
            fetched = true;
            TupleBuffer tuple_buf = new TupleBuffer(Setting.MAX_MEMORY_BYTES_FOR_FILTER_BUFFER,
                    new File(getTupleDesc().getTableName()+":delete.data"), getTupleDesc());
            while (child.hasNext()) {
                //todo Í³Ò»Ð´·¨
                if(Setting.isBTree){
                    tuple_buf.add(child.next());
                }
                else{
                    Tuple c = child.next();
                    GlobalManager.getBufferPool().deleteTuple(c);
                }
                count++;
            }
            tuple_buf.finisheAdding();
            Tuple c;
            while((c = tuple_buf.next()) != null){
                GlobalManager.getBufferPool().deleteTuple(c);
            }
            tuple_buf.close();
            return Util.getCountTuple(count, "delete count");
        }
    }
}
