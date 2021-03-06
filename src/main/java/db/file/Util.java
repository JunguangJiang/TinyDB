package db.file;

import db.Setting;
import db.error.NotNullViolation;
import db.field.Field;
import db.file.BTree.BTreePageId;
import db.file.heap.HeapPageId;
import db.tuple.TDItem;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Util {
    /**
     * write a byte for n times
     * @param dos
     * @param b
     * @param n
     * @throws IOException
     */
    public static void writeBytes(DataOutputStream dos, int b, int n) throws IOException {
        for (int i=0; i<n; i++) {
            dos.write((byte)b);
        }
    }

    /**
     * Check whether the not null constraint is satisfied
     * @param tdItem
     * @param value
     * @throws Exception if the constraint is not satisfied
     */
    public static void checkNotNullConstraint(TDItem tdItem, String value) throws NotNullViolation {
        if (tdItem.notNull || tdItem.isPrimaryKey) {
            if (db.field.Util.isNull(value)) {
                throw new NotNullViolation(tdItem.fieldName);
            }
        }
    }

    /**
     * parse a tuple from DataInputStream
     * @param td
     * @param dis
     * @return
     */
    public static Tuple parseTuple(TupleDesc td, DataInputStream dis) {
        try {
            Tuple t = new Tuple(td);
            for (int j = 0; j < td.numFields(); j++) {
                Field f = td.getTDItem(j).parse(dis);
                t.setField(j, f);
            }
            /*try {
                int tableid = dis.readInt();
                PageId tmpPageId;
                if(Setting.isBTree){
                    tmpPageId = new BTreePageId(tableid, 0, 0);
                }
                else{
                    tmpPageId = new HeapPageId(tableid, 0);
                }
                t.setRecordId(new RecordId(tmpPageId, 0));
            }
            catch (IOException e){
                throw new java.text.ParseException("couldn't parse", 0);
            }*/
            return t;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            throw new NoSuchElementException("parsing error!");
        }
    }
}
