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

    /**
     *
     * @param tableName the name of the Table
     * @param tupleDesc the tuple descriptor of the Table
     * @param file the disk File that the Table is stored in
     */
    public Table(String tableName, TupleDesc tupleDesc, File file) {

    }

    /**
     *
     * @return the tuple descriptor of the Table
     */
    public TupleDesc getTupleDesc() {
        return tupleDesc;
    }

    /**
     * insert a new Tuple into the Table
     * @param tuple
     * @return the QueryResult of the insert
     * @see QueryResult
     */
    public QueryResult insertTuple(Tuple tuple) {
        return new QueryResult(false, "");
    }

    /**
     * insert a new Tuple into the Table
     * the Tuple has the form
     *      INSERT attrNames VALUES(values);
     * @param attrNames attribute names
     * @param values each value might be String, Integer, Float
     * @return the QueryResult of the insert
     * @see QueryResult
     */
    public QueryResult insertTuple(String[] attrNames, Object[] values) {
        if (attrNames == null) { // attrNames are all the attribute names of the table
        }
        return new QueryResult(false, "");
    }

    /**
     * delete a Tuple from the Table
     * @param tuple
     * @return the QueryResult of the insert
     * @see QueryResult
     */
    public QueryResult deleteTuple(Tuple tuple) {
        return new QueryResult(false, "");
    }

    /**
     * @return the iterator over the Table
     */
    public DbFileIterator iterator() {
        return null;
    }
}
