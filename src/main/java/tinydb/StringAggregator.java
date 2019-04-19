package tinydb;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Knows how to compute some aggregate over a set of StringFields.
 */
public class StringAggregator implements Aggregator {

    private static final long serialVersionUID = 1L;
    private int gbField;
    private Type gbFieldType;
    private int aggregateField;
    private Op op;
    private boolean noGrouping = false;
    private HashMap<Field, Integer> groups; // (groupValue, aggregateValue)
    private String fieldName = "", groupFieldName="";

    /**
     * Aggregate constructor
     * @param gbfield the 0-based index of the group-by field in the tuple, or NO_GROUPING if there is no grouping
     * @param gbfieldtype the type of the group by field (e.g., Type.INT_TYPE), or null if there is no grouping
     * @param afield the 0-based index of the aggregate field in the tuple
     * @param what aggregation operator to use -- only supports COUNT
     * @throws IllegalArgumentException if what != COUNT
     */

    public StringAggregator(int gbfield, Type gbfieldtype, int afield, Op what) {
        if (what != Op.COUNT) {
            throw new IllegalArgumentException("op must be count");
        } else if (gbfield == Aggregator.NO_GROUPING) {
            noGrouping = true;
        }
        this.gbField = gbfield;
        this.gbFieldType = gbfieldtype;
        this.aggregateField = afield;
        this.op = what;
        groups = new HashMap<>();
    }

    /**
     * Merge a new tuple into the aggregate, grouping as indicated in the constructor
     * @param tuple the Tuple containing an aggregate field and a group-by field
     */
    public void mergeTupleIntoGroup(Tuple tuple) {
        Field key; // tuple's groupby field
        fieldName = tuple.getTupleDesc().getFieldName(aggregateField);

        // find field key
        if (noGrouping) {
            key = new IntField(Aggregator.NO_GROUPING);
        } else {
            key = tuple.getField(gbField);
            groupFieldName = tuple.getTupleDesc().getFieldName(gbField);
        }

        if (!groups.containsKey(key)) {
            groups.put(key, 0);
        }
        groups.put(key, groups.get(key)+1);
    }

    /**
     * Create a OpIterator over group aggregate results.
     *
     * @return a OpIterator whose tuples are the pair (groupVal,
     *   aggregateVal) if using group, or a single (aggregateVal) if no
     *   grouping. The aggregateVal is determined by the type of
     *   aggregate specified in the constructor.
     */
    public OpIterator iterator() {
        Type[] typeArray;
        String[] nameArray;
        TupleDesc tupleDesc;

        if (noGrouping) {
            typeArray = new Type[] {Type.INT_TYPE};
            nameArray = new String[] {fieldName};
        } else {
            typeArray = new Type[] {gbFieldType, Type.INT_TYPE};
            nameArray = new String[] {groupFieldName, fieldName};
        }
        tupleDesc = new TupleDesc(typeArray, nameArray);

        ArrayList<Tuple> tuples = new ArrayList<>();

        if (noGrouping) {
            for (Field key : groups.keySet()){
                int value = groups.get(key);
                Tuple tuple = new Tuple(tupleDesc);
                tuple.setField(0, new IntField(value));
                tuples.add(tuple);
            }
        } else {
            for (Field key : groups.keySet()) {
                int value = groups.get(key);
                Tuple tuple = new Tuple(tupleDesc);
                tuple.setField(0, key);
                tuple.setField(1, new IntField(value));
                tuples.add(tuple);
            }
        }
        return new TupleIterator(tupleDesc, tuples);
    }

}
