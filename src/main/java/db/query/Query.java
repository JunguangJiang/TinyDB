package db.query;

import com.github.freva.asciitable.AsciiTable;
import db.DbException;
import db.field.TypeMismatch;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import java.util.ArrayList;

/**
 * Only used for select
 */
public class Query {
    OpIterator root;

    public Query(OpIterator root) {
        this.root = root;
    }

    /**
     * execute the select operation
     * @return the result String of select query
     */
    public QueryResult executeSelect(String[] header) {
        try {
            this.root.open();
            // add tuple desc to the result
            TupleDesc tupleDesc = root.getTupleDesc();

            // add tuples to the result
            ArrayList<String[]> data = new ArrayList<>();
            while (root.hasNext()) {
                Tuple tuple = root.next();
                String[] body = new String[tupleDesc.numFields()];
                for (int i=0; i<tupleDesc.numFields(); i++) {
                    body[i] = tuple.getFieldString(i);
                }
                data.add(body);
            }
            String result = AsciiTable.getTable(header, data.toArray(new String[0][])) +
                    System.lineSeparator() + String.format("%d rows in set", data.size());
            return new QueryResult(true, result);
        } catch (DbException e){
            e.printStackTrace();
            return new QueryResult(false, e.toString());
        } catch (TypeMismatch e) {
            return new QueryResult(false, e.toString());
        } finally {
            this.root.close();
        }
    }

    /**
     * execute the delete or update operation
     * @return the result String of the operation
     */
    public QueryResult executeDeleteOrUpdate() {
        try {
            root.open();
            Tuple tuple = root.next();
            root.close();
            return new QueryResult(true, "Query OK, " + tuple.getField(0).toString() + " rows affected.");
        } catch (DbException e){
            e.printStackTrace();
            return new QueryResult(false, e.toString());
        } catch (TypeMismatch e) {
            return new QueryResult(false, e.toString());
        }
    }
}
