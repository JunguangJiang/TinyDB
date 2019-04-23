package db;

import db.file.DbFile;
import db.file.DbFileIterator;
import db.query.QueryResult;
import tinydb.Query;

import java.io.File;
import java.util.HashMap;

/**
 * Table is an interface provided for Database
 * such that Database doesn't have to worry about the implementation of the DbFile.
 * Each Table has an associated DbFile.
 */
public class Table {
    private DbFile dbFile;
    private String tableName;
    private TupleDesc tupleDesc;

    public Table(String tableName, TupleDesc tupleDesc, File file) {

    }

    public QueryResult insertTuple(Tuple tuple) {
        return new QueryResult(false, "");
    }

    public QueryResult insertTuple(String[] attrNames, Object[] values) {
        if (attrNames == null) { // attrNames are all the attribute names of the table
        }
        return new QueryResult(false, "");
    }

    public QueryResult deleteTuple(Tuple tuple) {
        return new QueryResult(false, "");
    }

    public DbFileIterator iterator() {
        return null;
    }
}
