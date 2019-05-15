package db.file;

import db.DbException;
import db.GlobalManager;
import db.file.BTree.BTreeFile;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import db.field.TypeMismatch;
import db.field.Util;
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
    public File file;

    /**
     * @param id
     * @param name the name of the Table
     * @param tupleDesc the tuple descriptor of the Table
     * @param file the disk File that the Table is stored in
     */
    public Table(Integer id, String name, TupleDesc tupleDesc, File file) {
        this.id = id;
        // this.dbFile = new HeapFile(id, file, tupleDesc);
        this.dbFile = new BTreeFile(id, file, tupleDesc);
        this.name = name;
        this.file = file;
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
        try {
            GlobalManager.getBufferPool().insertTuple(this.id, tuple);
        } catch (IOException e){
            e.printStackTrace();
        } catch (PrimaryKeyViolation e) {
            return new QueryResult(false, "Violation of PRIMARY KEY constraint."+e.toString());
        } catch (DbException e){
            e.printStackTrace();
        }
        return new QueryResult(true, "Query OK, 1 row affected.");
    }

    /**
     * insert a new Tuple into the Table
     * the Tuple has the form
     *      INSERT attrNames VALUES(values);
     * must ensure each tuple satisfies the primary key and not null constraint
     * @param attrNames attribute names. If attrNames is null, then it refers to all the attributes of the Tuple
     * @param values each value might be String, Integer, Float
     * @return the QueryResult of the insert
     * @see QueryResult
     */
    public QueryResult insertTuple(String[] attrNames, Object[] values) {
        if (attrNames == null) {
            //attrNames are all the attribute names of the table
            attrNames = getTupleDesc().getAttrNames();
        }
        if (attrNames.length != values.length) {
            return new QueryResult(false,
                    "The length of attribute must equal to the length of values");
        } else {
            HashMap<String, Object> hashMap = new HashMap<>();
            for (int i=0; i<attrNames.length; i++) {
                hashMap.put(attrNames[i], values[i]);
            }

            TupleDesc tupleDesc = getTupleDesc();
            Tuple tuple = new Tuple(tupleDesc);
            for (int i=0; i<tupleDesc.numFields(); i++) {
                TDItem tdItem = tupleDesc.getField(i);
                Object value = hashMap.get(tdItem.fieldName);
                hashMap.remove(tdItem.fieldName);
                if (value != null) {
                    try {
                        tuple.setField(i, Util.getField(value, tdItem.fieldType, tdItem.maxLen, tdItem.fieldName));
                    } catch (TypeMismatch e) {
                        return new QueryResult(false, e.toString());
                    }
                } else {
                    if (tdItem.notNull || tdItem.isPrimaryKey) {
                        return new QueryResult(false, "Attribute " + tdItem.fieldName + " can not be null");
                    }
                }
            }
            // Ensure that all insert attributes exist.
            if (!hashMap.isEmpty()) {
                String attribute = hashMap.keySet().toArray(new String[0])[0];
                return new QueryResult(false, "Attribute " + attribute +" doesn't exist.");
            }

            return insertTuple(tuple);
        }
    }

    /**
     * @return the iterator over the Table
     */
    public DbFileIterator iterator() {
        return dbFile.iterator();
    }
}
