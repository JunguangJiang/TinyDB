package db.query.pipe;

import db.DbException;
import db.Setting;
import db.error.SQLError;
import db.field.Field;
import db.field.Op;
import db.file.TupleBuffer;
import db.query.FullColumnName;
import db.query.plan.LogicalFilterNode;
import db.query.predicate.Predicate;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import db.query.plan.LogicalFilterNode.*;

public class NaturalJoin extends Operator{
    private static final long serialVersionUID = 1L;

    private OpIterator lhs, rhs;
    private TupleDesc mergedTupleDesc;
    private Predicate predicate;
    TupleBuffer tupleBuffer;

    /**
     * Join lhs and rhs with cmp as filter predicate
     * @param lhs
     * @param rhs
     */
    public NaturalJoin(OpIterator lhs, OpIterator rhs) throws DbException, SQLError{
        this.lhs = lhs;
        this.rhs = rhs;
        this.mergedTupleDesc = TupleDesc.merge(lhs.getTupleDesc(), rhs.getTupleDesc());
        String filename = "NaturalJoin_"+this.lhs.getTupleDesc().getTableName() + "_" + rhs.getTupleDesc().getTableName() + ".data";
        File file = new File(filename);
        this.tupleBuffer = new TupleBuffer(Setting.MAX_MEMORY_BYTES_FOR_JOIN_BUFFER,
                file, mergedTupleDesc);
        this.predicate = getSameNames();

    }

    private Predicate getSameNames() throws SQLError{
        TupleDesc ltd = lhs.getTupleDesc(), rtd = rhs.getTupleDesc();
        AndNode andNode = new AndNode();
        for (int i=0; i<ltd.numFields(); i++) {
            String name = ltd.getTDItem(i).fieldName;
            try {
                int j = rtd.fieldNameToIndex(name);
                FullColumnName lname = new FullColumnName(ltd.getTableName(), name, null);
                FullColumnName rname = new FullColumnName(rtd.getTableName(),name,null);
                andNode.add(new KKCmpNode(lname, Op.EQUALS,rname));
            }catch (NoSuchElementException e){
            }
        }
        return andNode.predicate(mergedTupleDesc);
    }

    @Override
    public TupleDesc getTupleDesc() {
        return mergedTupleDesc;
    }


    @Override
    public void open() throws DbException, SQLError {
        this.lhs.open();
        this.rhs.open();

        // tuple count of child
        long lcount = lhs.count();
        long rcount = rhs.count();

        assert lcount >= 0 && rcount >= 0;

        //Nested-Loop Join
        // ensure that the inner loop has less tuples
        boolean swap = false;
        OpIterator outer=lhs, inner=rhs;
        if (lcount < rcount) {
            outer = rhs;
            inner = lhs;
            swap = true;
        }

        // do the nested-loop join
        while (outer.hasNext()) {
            Tuple tuple1 = outer.next();
            while (inner.hasNext()) {
                Tuple tuple2 = inner.next();
                Tuple mergedTuple = Tuple.merge(tuple1, tuple2, mergedTupleDesc, swap);
                if (predicate.filter(mergedTuple)) {
                    tupleBuffer.add(mergedTuple);
                }
            }
            inner.rewind();
        }

        lhs.close();
        rhs.close();
        tupleBuffer.finisheAdding();
        super.open();
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
     * Returns the next tuple generated by the join, or null if there are no
     * more tuples. Logically, this is the next tuple in r1 cross r2 that
     * satisfies the join predicate. There are many possible implementations;
     * the simplest is a nested loops join.
     * <p>
     * Note that the tuples returned from this particular implementation of Join
     * are simply the concatenation of joining tuples from the left and right
     * relation. Therefore, if an equality predicate is used there will be two
     * copies of the join attribute in the results. (Removing such duplicate
     * columns can be done with an additional projection operator if needed.)
     * <p>
     * For example, if one tuple is {1,2,3} and the other tuple is {1,5,6},
     * joined on equality of the first column, then this returns {1,2,3,1,5,6}.
     *
     * @return The next matching tuple.
     */
    @Override
    protected Tuple fetchNext() throws DbException{
        return tupleBuffer.next();
    }

    @Override
    public long count() {
        return tupleBuffer.getTupleNum();
    }
}
