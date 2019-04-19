package tinydb;

import java.io.File;

public class test {
    public static void main(String[] args){
        Type types[] = new Type[] {Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE};
        String names[] = new String[] {"field0", "field1", "field2"};
        TupleDesc tupleDesc = new TupleDesc(types, names);

        HeapFile table1 = new HeapFile(new File("some_data_file.dat"), tupleDesc);
        Database.getCatalog().addTable(table1, "test");

        TransactionId tid = new TransactionId();
        SeqScan f = new SeqScan(tid, table1.getId());

        try {
            f.open();
            while (f.hasNext()) {
                Tuple tuple = f.next();
                System.out.println(tuple);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
