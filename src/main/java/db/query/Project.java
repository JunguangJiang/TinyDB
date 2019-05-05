package db.query;

import db.DbException;
import db.field.TypeMismatch;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import javax.swing.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Project is an operator that implements a relational projection.
 */
public class Project extends Operator{

    private static final long serialVersionUID = 1L;
    private OpIterator child;
    private TupleDesc tupleDesc;
    private int[] attributes;

    /**
     * @param attributes an array of attributes
     * @param child a child operator to read tuples to apply projection to
     */
    public Project(Attribute[] attributes, OpIterator child) {
        this.child = child;

        TupleDesc childTD = child.getTupleDesc();
        this.attributes = new int[attributes.length];
        for (int i=0; i<attributes.length; i++) {
            this.attributes[i] = childTD.fieldNameToIndex(
                    attributes[i].tableName, attributes[i].attrName);
        }

        TDItem[] tdItems = new TDItem[attributes.length];
        for (int i=0; i<tdItems.length; i++){
            tdItems[i] =  childTD.getField(this.attributes[i]); // TODO Is shallow copy right?
        }
        this.tupleDesc = new TupleDesc(tdItems, null);
    }

    @Override
    public TupleDesc getTupleDesc() {
        return tupleDesc;
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
     * Operator.fetchNext implementation. Iterates over tuples from the child
     * operator, projecting out the fields from the tuple
     *
     * @return The next tuple, or null if there are no more tuples
     */
    @Override
    protected Tuple fetchNext() throws DbException, TypeMismatch {
        while(child.hasNext()) {
            Tuple t = child.next();
            Tuple newTuple = new Tuple(tupleDesc);
            newTuple.setRecordId(t.getRecordId());
            for (int i=0; i<tupleDesc.numFields(); i++){
                newTuple.setField(i, t.getField(attributes[i]));
            }
            return newTuple;
        }
        return null;
    }

    @Override
    public OpIterator[] getChildren() {
        return new OpIterator[] { this.child };
    }

    @Override
    public void setChildren(OpIterator[] children) {
        this.child = children[0];
    }
}
