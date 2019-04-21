package db;

import file.DbFile;
import file.DbFileIterator;
import java.io.File;

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

    public boolean insertTuple(Tuple tuple) {
        return false;
    }

    public boolean deleteTuple(Tuple tuple) {
        return false;
    }

    public DbFileIterator iterator() {
        return null;
    }
}
