package db.query;


import db.DbException;
import db.Tuple;
import db.TupleDesc;

public class Select extends Operator{
    private static final long serialVersionUID = 1L;

    @Override
    public TupleDesc getTupleDesc() {
        return null;
    }

    @Override
    public void open() throws DbException {
        super.open();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void rewind() throws DbException {

    }

    @Override
    protected Tuple fetchNext() throws DbException {
        return null;
    }

    @Override
    public OpIterator[] getChildren() {
        return new OpIterator[0];
    }

    @Override
    public void setChildren(OpIterator[] children) {

    }

}
