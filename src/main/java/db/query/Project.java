package db.query;

import db.DbException;
import db.field.TypeMismatch;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

/**
 * Project is an operator that implements a relational projection.
 */
public class Project extends Operator{

    private static final long serialVersionUID = 1L;
    private OpIterator child;
    private TupleDesc tupleDesc;
    private Attribute[] attributes;

    public Project(Attribute[] attributes, OpIterator child) {
        this.child = child;
        this.attributes = attributes;
    }

    @Override
    public TupleDesc getTupleDesc() {
        return tupleDesc;
    }

    @Override
    public void open() throws DbException, TypeMismatch {
        super.open();
        child.open();
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
     * Operator.fetchNext implementation. Iterates over tuples from the child
     * operator, projecting out the fields from the tuple
     *
     * @return The next tuple, or null if there are no more tuples
     */
    @Override
    protected Tuple fetchNext() throws DbException {
        return null;
    }

    @Override
    public OpIterator[] getChildren() {
        return new OpIterator[] { this.child };
    }

    @Override
    public void setChildren(OpIterator[] children) {
        if (this.child!=children[0])
        {
            this.child = children[0];
        }
    }
}
