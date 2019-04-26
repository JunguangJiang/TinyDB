package db;

import db.file.DbFile;
import db.file.DbFileIterator;
import db.file.Page;
import db.file.heap.HeapFile;
import db.query.QueryResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Table is an interface provided for Database and Visitor.
 * Each Table has an associated DbFile, yet during Insert/Delete/Update,
 * it must use BufferPool to get Page.
 */
public class Table {
    private DbFile dbFile;
    private Integer id;
    private String name;

    /**
     * @param id
     * @param name the name of the Table
     * @param tupleDesc the tuple descriptor of the Table
     * @param file the disk File that the Table is stored in
     */
    public Table(Integer id, String name, TupleDesc tupleDesc, File file) {
        this.dbFile = new HeapFile(id, file, tupleDesc);
        this.name = name;
    }

    public DbFile getDbFile() {
        return dbFile;
    }
    public Integer getId() {return id;}
    public String getName() {return name;}

    /**
     *
     * @return the tuple descriptor of the Table
     */
    public TupleDesc getTupleDesc() {
        return dbFile.getTupleDesc();
    }



    /**
     * insert a new Tuple into the Table.
     * must use BufferPool to get Page!
     * must ensure each tuple satisfies the primary key and not null constraint
     * @param tuple
     * @return the QueryResult of the insert
     * @see QueryResult
     */
    public QueryResult insertTuple(Tuple tuple) {
        // TODO
        try {
            GlobalManager.getBufferPool().insertTuple(this.id, tuple);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new QueryResult(true, "");
    }

    /**
     * insert a new Tuple into the Table
     * the Tuple has the form
     *      INSERT attrNames VALUES(values);
     * must use BufferPool to get Page!
     * must ensure each tuple satisfies the primary key and not null constraint
     * @param attrNames attribute names
     * @param values each value might be String, Integer, Float
     * @return the QueryResult of the insert
     * @see QueryResult
     */
    public QueryResult insertTuple(String[] attrNames, Object[] values) {
        // TODO
        if (attrNames == null) { // attrNames are all the attribute names of the table
        }
        return new QueryResult(false, "");
    }

    /**
     * delete a Tuple from the Table
     * must use BufferPool to get Page!
     * @param tuple
     * @return the QueryResult of the insert
     * @see QueryResult
     */
    public QueryResult deleteTuple(Tuple tuple) {
        // TODO
        try {
            GlobalManager.getBufferPool().deleteTuple(tuple);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return new QueryResult(false, "");
    }

    /**
     * @return the iterator over the Table
     */
    public DbFileIterator iterator() {
        // TODO
        return null;
    }
}
