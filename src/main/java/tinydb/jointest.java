package tinydb;

import java.io.File;

public class jointest {
    public static void main(String[] args){
        Type[] types = new Type[] {Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE};
        String[] names = new String[] {"field0", "field1", "field2"};

        TupleDesc tupleDesc = new TupleDesc(types, names);

        HeapFile table1 = new HeapFile(new File("some_data_file.dat"), tupleDesc);
        Database.getCatalog().addTable(table1, "t1");

        HeapFile table2 = new HeapFile(new File("some_data_file2.dat"), tupleDesc);
        Database.getCatalog().addTable(table2, "t2");

        TransactionId transactionId = new TransactionId();

        SeqScan seqScan1 = new SeqScan(transactionId, table1.getId(), "t1");
        SeqScan seqScan2 = new SeqScan(transactionId, table2.getId(), "t2");

        Filter filter = new Filter(
                new Predicate(0, Predicate.Op.GREATER_THAN, new IntField(1)),
                seqScan1
        );

        JoinPredicate joinPredicate = new JoinPredicate(1, Predicate.Op.EQUALS, 1);
        Join join = new Join(joinPredicate, filter, seqScan2);

        try {
            join.open();
            while (join.hasNext()){
                Tuple tuple = join.next();
                System.out.println(tuple);
            }
            join.close();
            Database.getBufferPool().transactionComplete(transactionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
