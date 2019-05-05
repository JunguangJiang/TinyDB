package db.query;

import db.DbException;
import db.field.Field;
import db.field.TypeMismatch;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Only used for select
 */
public class Query {
    OpIterator root;

    public Query(OpIterator root) {
        this.root = root;
    }

    public QueryResult executeSelect() {
        try {
            this.root.open();
            ArrayList<String> resultList = new ArrayList<>();
            // add tuple desc to the result
            TupleDesc tupleDesc = root.getTupleDesc();
            Iterator<TDItem> iterator = tupleDesc.iterator();
            while (iterator.hasNext()) {
                TDItem tdItem = iterator.next();
                resultList.add(tdItem.tableName + "." +tdItem.fieldName);
            }
            // add tuples to the result
            while (root.hasNext()) {
                Tuple tuple = root.next();
                System.out.println(tuple.toString());
                Iterator<Field> fieldIterator = tuple.fields();
                while (fieldIterator.hasNext()) {
                    resultList.add(fieldIterator.next().toString());
                }
            }
            return new QueryResult(true, format(resultList, tupleDesc.numFields()));
        } catch (DbException e){
            e.printStackTrace();
            return new QueryResult(false, e.toString());
        } catch (TypeMismatch e) {
            return new QueryResult(false, e.toString());
        } finally {
            this.root.close();
        }
    }

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

    private String format(ArrayList<String> list, int len) {
        StringBuilder formatedString = new StringBuilder();
        int i = 0;
        for (String s : list) {
            formatedString.append(s);
            formatedString.append("\t");
            i += 1;
            if (i % len == 0) {
                formatedString.append(System.lineSeparator());
            }
        }
        return formatedString.toString();
    }
}
