package db.file;

import db.DbException;
import db.GlobalManager;
import db.error.NotNullViolation;
import db.error.PrimaryKeyViolation;
import db.error.SQLError;
import db.file.BTree.BTreeFile;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import db.error.TypeMismatch;
import db.field.Util;
import db.file.heap.HeapFile;
import db.query.QueryResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public long autoIncrementNumber=0;
    public long count;

    /**
     * @param id
     * @param name the name of the Table
     * @param tupleDesc the tuple descriptor of the Table
     * @param file the disk File that the Table is stored in
     * @param isBTree whether the table should build on BTree
     * @param hasPrimaryKeyConstraint whether the Table has primary key constraint
     */
    public Table(Integer id, String name, TupleDesc tupleDesc, File file,
                 boolean isBTree, boolean hasPrimaryKeyConstraint) {
        this.id = id;
        if (isBTree) {
            this.dbFile = new BTreeFile(id, file, tupleDesc);
        } else {
            this.dbFile = new HeapFile(id, file, tupleDesc, hasPrimaryKeyConstraint);
        }
        this.name = name;
        this.file = file;
        count = 0;
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
     * after insertTuple, autoIncrementNumber will increase by 1
     * @param tuple
     * @return the QueryResult of the insert
     * @see QueryResult
     */
    public QueryResult insertTuple(Tuple tuple) {
        try {
            GlobalManager.getBufferPool().insertTuple(this.id, tuple);
            autoIncrementNumber++;
            count++;
        } catch (IOException | DbException e){
            e.printStackTrace();
        } catch (PrimaryKeyViolation e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, "Query OK, 1 row affected.");
    }

    /**
     * insert a new Tuple into the Table
     * the Tuple has the form
     *      INSERT attrNames VALUES(values);
     * after insertTuple, autoIncrementNumber will increase by 1
     * must ensure each tuple satisfies the primary key and not null constraint
     * @param attrNames attribute names. If attrNames is null, then it refers to all
     *                  the attributes of the Tuple
     * @param values each value might be String, Integer, Long, Float or Double
     * @return the QueryResult of the insert
     * @see QueryResult
     */
    public QueryResult insertTuple(String[] attrNames, Object[] values) {
        try {
            Tuple tuple = createTuple(attrNames, values, getTupleDesc(), autoIncrementNumber);
            return insertTuple(tuple);
        } catch (SQLError e) {
            return new QueryResult(false, e.getMessage());
        }

    }

    /**
     * create a new Tuple
     * the Tuple has the form
     *      INSERT attrNames VALUES(values);
     * @param attrNames attribute names. If attrNames is null, then it refers to all
     *                  the attributes of the Tuple
     * @param values each value might be String, Integer, Long, Float or Double
     * @param tupleDesc
     * @param autoIncrementNumber default number for PRIMARY
     * @return the new Tuple
     * @throws Exception
     */
    public static Tuple createTuple(String[] attrNames, Object[] values,
                                     TupleDesc tupleDesc, long autoIncrementNumber) throws SQLError, NotNullViolation, TypeMismatch{
        if (attrNames == null) {
            //attrNames are all the attribute names of the table
            ArrayList<String> attrNameList = new ArrayList<>(Arrays.asList(tupleDesc.getAttrNames()));
            attrNameList.remove("PRIMARY");
            attrNames = attrNameList.toArray(new String[0]);
        }
        if (attrNames.length != values.length) {
            throw new SQLError("The length of attribute must equal to the length of values");
        } else {
            HashMap<String, Object> hashMap = new HashMap<>(); // map attrName to value
            for (int i=0; i<attrNames.length; i++) {
                hashMap.put(attrNames[i], values[i]);
            }
            hashMap.put("PRIMARY", autoIncrementNumber); // PRIMARY is an auto increment attribute

            Tuple tuple = new Tuple(tupleDesc);
            // Set each attribute to the corresponding value
            for (int i=0; i<tupleDesc.numFields(); i++) {
                TDItem tdItem = tupleDesc.getTDItem(i);
                Object value = hashMap.remove(tdItem.fieldName);
                db.file.Util.checkNotNullConstraint(tdItem,value);
                tuple.setField(i, Util.getField(value, tdItem.fieldType, tdItem.maxLen, tdItem.fieldName));
            }
            hashMap.remove("PRIMARY");
            // Ensure that all insert attributes exist.
            if (!hashMap.isEmpty()) {
                String attribute = hashMap.keySet().toArray(new String[0])[0];
                throw new SQLError("FullColumnName " + attribute +" doesn't exist.");
            }
            return tuple;
        }
    }

    /**
     * @return the iterator over the Table
     */
    public DbFileIterator iterator() {
        return dbFile.iterator();
    }

}
