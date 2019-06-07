package db.query.plan;

import com.github.freva.asciitable.AsciiTable;
import db.DbException;
import db.error.SQLError;

import db.error.PrimaryKeyViolation;
import db.query.QueryResult;
import db.query.pipe.OpIterator;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import java.util.ArrayList;

/**
 * Only used for select
 */
public class PhysicalPlan {
    OpIterator root;

    public PhysicalPlan(OpIterator root) {
        this.root = root;
    }

    /**
     * execute the select operation
     * @return the result String of select query
     */
    public QueryResult execute(String[] header, boolean countOnly) {
        try {
            this.root.open();
            // add tuple desc to the result
            TupleDesc tupleDesc = root.getTupleDesc();

            ArrayList<String[]> data = new ArrayList<>();
            if (countOnly) {
                header = new String[] {"count(*)"};
                long count=0;
                while (root.hasNext()) {
                    Tuple tuple = root.next();
                    count++;
                }
                data.add(new String[]{String.valueOf(count)});
            } else {
                // add tuples to the result
                while (root.hasNext()) {
                    Tuple tuple = root.next();
                    String[] body = new String[tupleDesc.numFields()];
                    for (int i=0; i<tupleDesc.numFields(); i++) {
                        body[i] = tuple.getField(i).toString();
                    }
                    data.add(body);
                }
            }
            String result = AsciiTable.getTable(header, data.toArray(new String[0][])) +
                    System.lineSeparator() + String.format("%d rows in set", data.size());
            return new QueryResult(true, result);
        } catch (DbException e){
            e.printStackTrace();
            return new QueryResult(false, e.toString());
        } catch (SQLError e) {
            return new QueryResult(false, e.getMessage());
        } finally {
            this.root.close();
        }
    }
}
