package db.query.pipe;

import db.DbException;
import db.GlobalManager;
import db.Setting;
import db.error.SQLError;
import db.field.Field;

import db.field.Util;
import db.error.NotNullViolation;
import db.error.PrimaryKeyViolation;
import db.file.TupleBuffer;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.File;
import java.io.IOException;

/**
 * Update is an operator that implements updating.
 */
public class Update extends Operator{

    /**
     * For sql "set attribute=value",
     * we have UpdateElement(attribute, value)
     */
    public static class UpdateElement {
        public String attribute;
        public String value;
        public UpdateElement(String attribute, String value) {
            this.attribute = attribute;
            this.value = value;
        }
    }

    private static final long serialVersionUID = 1L;
    private OpIterator child;
    private int indexes[]; //index of the attributes that needs updating
    private Field fields[]; //the corresponding updating fields
    private boolean changePrimaryKey;
    private Object primaryKeyValue;

    public Update(OpIterator child, UpdateElement[] updateElements) throws SQLError {
        this.child = child;
        this.indexes = new int[updateElements.length];
        this.fields = new Field[updateElements.length];
        TupleDesc tupleDesc = child.getTupleDesc();
        changePrimaryKey = false;
        for (int i=0; i<updateElements.length; i++){
            this.indexes[i] = tupleDesc.fieldNameToIndex(updateElements[i].attribute);
            TDItem tdItem = tupleDesc.getTDItem(indexes[i]);
            this.fields[i] = Util.getField(updateElements[i].value, tdItem.fieldType,
                    tdItem.maxLen, tdItem.fieldName);
            db.file.Util.checkNotNullConstraint(tdItem,updateElements[i].value);
            if (tdItem.isPrimaryKey) {
                changePrimaryKey = true;
                primaryKeyValue = updateElements[i].value;
            }
        }
    }

    @Override
    public TupleDesc getTupleDesc() {
        return child.getTupleDesc();
    }

    @Override
    public void open() throws DbException, SQLError{
        child.open();
        super.open();
    }

    @Override
    public void close() {
        super.close();
        child.close();
    }

    @Override
    public void rewind() throws DbException {
        child.rewind();
    }

    private boolean hasMoreThanOneChild() throws DbException, SQLError{
        int count=0;
        while(child.hasNext()) {
            child.next();
            count++;
            if (count > 1) {
                break;
            }
        }
        child.rewind();
        return count > 1;
    }

    @Override
    protected Tuple fetchNext() throws DbException, SQLError {
        if (changePrimaryKey && hasMoreThanOneChild() ) {
            throw new PrimaryKeyViolation(child.getTupleDesc().getPrimaryKey(), primaryKeyValue);
        }
        int count = 0;
        TupleBuffer oldTupleBuf = new TupleBuffer(Setting.MAX_MEMORY_BYTES_FOR_FILTER_BUFFER,
                new File(getTupleDesc().getTableName()+":oldBuf.data"), getTupleDesc());
        TupleBuffer newTupleBuf = new TupleBuffer(Setting.MAX_MEMORY_BYTES_FOR_FILTER_BUFFER,
                new File(getTupleDesc().getTableName()+":newBuf.data"), getTupleDesc());
        while (child.hasNext()) {
            //todo Í³Ò»Ð´·¨
            Tuple oldTuple = child.next();
            Tuple newTuple = oldTuple.clone();
            if(Setting.isBTree){
                oldTupleBuf.add(oldTuple);
                for (int i=0; i<indexes.length; i++){
                    newTuple.setField(indexes[i], fields[i]);
                }
                newTupleBuf.add(newTuple);
            }
            else{
                //make a new tuple, delete the old tuple and insert the new tuple
                int tableid = oldTuple.getRecordId().getPageId().getTableId();
                GlobalManager.getBufferPool().deleteTuple(oldTuple);

                for (int i=0; i<indexes.length; i++){
                    newTuple.setField(indexes[i], fields[i]);
                }
                try {
                    GlobalManager.getBufferPool().insertTuple(tableid, newTuple);
                } catch (DbException | IOException e) {
                    e.printStackTrace();
                } catch (PrimaryKeyViolation e) {
                    try {
                        GlobalManager.getBufferPool().insertTuple(tableid, oldTuple);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    oldTupleBuf.finisheAdding();
                    newTupleBuf.finisheAdding();
                    oldTupleBuf.close();
                    newTupleBuf.close();
                    throw e;
                }
                count++;
            }
        }
        oldTupleBuf.finisheAdding();
        newTupleBuf.finisheAdding();
        Tuple c;
        while((c = oldTupleBuf.next()) != null){
            int tableid = c.getRecordId().getPageId().getTableId();
            GlobalManager.getBufferPool().deleteTuple(c);
            Tuple t = newTupleBuf.next();
            try {
                GlobalManager.getBufferPool().insertTuple(tableid, t);
            } catch (DbException | IOException e) {
                e.printStackTrace();
            } catch (PrimaryKeyViolation e) {
                try {
                    GlobalManager.getBufferPool().insertTuple(tableid, t);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                oldTupleBuf.close();
                newTupleBuf.close();
                throw e;
            }
            count++;
        }
        oldTupleBuf.close();
        newTupleBuf.close();
        return Util.getCountTuple(count, "update counts");
    }
}
