package db.query.pipe;

import db.DbException;
import db.GlobalManager;
import db.error.SQLError;
import db.error.TypeMismatch;
import db.error.PrimaryKeyViolation;
import db.file.DbFileIterator;
import db.file.Table;
import db.query.QueryResult;
import db.query.plan.LogicalFilterNode;
import db.query.predicate.Predicate;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Join operator implements the relational join operation.
 */
public class Join extends Operator{
    private static final long serialVersionUID = 1L;

    private OpIterator lhs, rhs;
    private TupleDesc mergedTupleDesc;
    private Predicate predicate;
    private DbFileIterator iterator;

    /**
     * Join lhs and rhs with cmp as filter predicate
     * @param lhs
     * @param cmp if cmp is null, then do no filtering
     * @param rhs
     * @throws TypeMismatch
     */
    public Join(OpIterator lhs, LogicalFilterNode.BaseFilterNode cmp, OpIterator rhs) throws SQLError{
        this.lhs = lhs;
        this.rhs = rhs;
        this.mergedTupleDesc = TupleDesc.merge(lhs.getTupleDesc(), rhs.getTupleDesc());
        if (cmp == null) {
            cmp = new LogicalFilterNode.VVCmpNode(true);
        }
        this.predicate = cmp.predicate(mergedTupleDesc);
    }

    @Override
    public TupleDesc getTupleDesc() {
        return mergedTupleDesc;
    }

    @Override
    public void open() throws DbException, SQLError {
        this.lhs.open();
        this.rhs.open();
        // TODO decrease the loop times
        String lTableName = this.lhs.getTupleDesc().getTableName();
        String rTableName = this.rhs.getTupleDesc().getTableName();
        String tableName = "JOIN:" + lTableName + ":" + rTableName;
        mergedTupleDesc.setTableName(tableName);
        QueryResult queryResult = GlobalManager.getDatabase().createTable(tableName, mergedTupleDesc,
                false, false);
        if (!queryResult.succeeded()) {
            throw new SQLError("Can not join " + lTableName + " with " + rTableName);
        }
        Table table = GlobalManager.getDatabase().getTable(tableName);
        while (lhs.hasNext()) {
            Tuple tuple1 = lhs.next();
            while (rhs.hasNext()) {
                Tuple tuple2 = rhs.next();
                Tuple mergedTuple = Tuple.merge(tuple1, tuple2, mergedTupleDesc);
                if (predicate.filter(mergedTuple)) {
                    table.insertTuple(mergedTuple);
                }
            }
            rhs.rewind();
        }
        super.open();
        iterator = table.iterator();
        iterator.open();
    }

    @Override
    public void close() {
        lhs.close();
        rhs.close();
        super.close();
        iterator.close();
        iterator = null;
        GlobalManager.getDatabase().dropTable(mergedTupleDesc.getTableName());
    }

    @Override
    public void rewind() {
        iterator =  GlobalManager.getDatabase().getTable(mergedTupleDesc.getTableName()).iterator();
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
        if (iterator != null && iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }
}
