package db.query.pipe;

import db.DbException;
import db.Setting;
import db.error.SQLError;
import db.field.Field;
import db.field.Op;
import db.file.*;
import db.query.FullColumnName;
import db.query.predicate.Predicate;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import db.query.plan.LogicalFilterNode.*;

/**
 * The Join operator implements the relational join operation.
 */
public class Join extends Operator{
    private static final long serialVersionUID = 1L;

    private OpIterator lhs, rhs;
    private TupleDesc mergedTupleDesc;
    private Predicate predicate;
    TupleBuffer tupleBuffer;
    BaseFilterNode cmp;

    /**
     * Join lhs and rhs with cmp as filter predicate
     * @param lhs
     * @param cmp if cmp is null, then do no filtering
     * @param rhs
     */
    public Join(OpIterator lhs, BaseFilterNode cmp, OpIterator rhs) throws DbException{
        this.lhs = lhs;
        this.rhs = rhs;
        this.mergedTupleDesc = TupleDesc.merge(lhs.getTupleDesc(), rhs.getTupleDesc());
        if (cmp == null) {
            cmp = new VVCmpNode(true);
        }
        this.cmp = cmp;
        String filename = "Join:"+this.lhs.getTupleDesc().getTableName() + ":" + rhs.getTupleDesc().getTableName() + ".data";
        File file = new File(filename);
        this.tupleBuffer = new TupleBuffer(Setting.MAX_MEMORY_BYTES_FOR_JOIN_BUFFER,
                file, mergedTupleDesc);
    }

    @Override
    public TupleDesc getTupleDesc() {
        return mergedTupleDesc;
    }

    /**
     *
     * @param child the child OpIterator to read tuples from
     * @param keyIdx the Field idx in the Tuple
     * @return a HashMap which hash Field to Tuple
     * @throws DbException
     * @throws SQLError
     */
    private HashMap<Field, ArrayList<Tuple>> getHashMap(OpIterator child, int keyIdx) throws DbException, SQLError{
        HashMap<Field, ArrayList<Tuple>> hashMap = new HashMap<>();
        while (child.hasNext()) {
            Tuple tuple = child.next();
            Field field = tuple.getField(keyIdx);
            ArrayList<Tuple> arrayList;
            if (hashMap.containsKey(field)){
                arrayList = hashMap.get(field);
            } else {
                arrayList = new ArrayList<>();
            }
            arrayList.add(tuple);
            hashMap.put(field,arrayList);
        }
        return hashMap;
    }

    @Override
    public void open() throws DbException, SQLError {
        this.lhs.open();
        this.rhs.open();

        // tuple count of child
        long lcount = lhs.count();
        long rcount = rhs.count();

        assert lcount >= 0 && rcount >= 0;

        // check when to do HashJoin optimization
        boolean hashJoin = false;
        long lmemoryUsage = lcount * lhs.getTupleDesc().getSize();
        long rmemoryUsage = rcount * rhs.getTupleDesc().getSize();
        if (cmp instanceof KKCmpNode &&  ((KKCmpNode)cmp).op == Op.EQUALS) {
            long hashMemoryUsage = Math.min(lmemoryUsage, rmemoryUsage);
            hashJoin = hashMemoryUsage < Setting.MAX_MEMORY_BYTES_FOR_JOIN_HASH_MAP;
        }
        if (hashJoin) { // HashJoin
            int lidx, ridx; // index of Field in the lhs and rhs
            FullColumnName lname = ((KKCmpNode) this.cmp).lhs,
                    rname=((KKCmpNode) this.cmp).rhs;
            try {
                lidx = lhs.getTupleDesc().fullColumnNameToIndex(lname);
                ridx = rhs.getTupleDesc().fullColumnNameToIndex(rname);
            } catch (NoSuchElementException e){
                try {
                    lidx = lhs.getTupleDesc().fullColumnNameToIndex(rname);
                    ridx = rhs.getTupleDesc().fullColumnNameToIndex(lname);
                }catch (NoSuchElementException e2) {
                    throw new SQLError("the attributes in the on clause must belong to the table before." + e2.getMessage());
                }
            }
            HashMap<Field, ArrayList<Tuple>> inner;
            OpIterator outer;
            int outerIdx;
            boolean swap = false;
            // smaller child table is stored in the HashMap in the memory
            // while the bigger child table can be stored in the disk
            if (lmemoryUsage < rmemoryUsage) {
                inner = getHashMap(lhs, lidx);
                outer = rhs;
                outerIdx = ridx;
                swap = true;
            } else {
                inner = getHashMap(rhs, ridx);
                outer = lhs;
                outerIdx = lidx;
            }
            while (outer.hasNext()) {
                Tuple tuple1 = outer.next();
                Field field = tuple1.getField(outerIdx);
                if (inner.containsKey(field)){
                    ArrayList<Tuple> arrayList = inner.get(field);
                    for (Tuple tuple2: arrayList) {
                        Tuple mergedTuple = Tuple.merge(tuple1, tuple2, getTupleDesc(), swap);
                        tupleBuffer.add(mergedTuple);
                    }
                }
            }
        } else { //Nested-Loop Join
            predicate = cmp.predicate(mergedTupleDesc);

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
    public void rewind() throws DbException{
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
