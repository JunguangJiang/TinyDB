package db.query;

import db.field.Op;
import db.field.TypeMismatch;
import db.field.Util;
import db.file.BTree.IndexPredicate;
import db.tuple.Tuple;
import db.tuple.TupleDesc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/** A LogicalFilterNode represents the parameters of a filter in the WHERE clause of a query.
 */
public class LogicalFilterNode {
    public interface Cmp{
        /**
         * convert a clause into the corresponding Predicate
         * @param tupleDesc
         * @return
         * @throws TypeMismatch
         */
        Predicate predicate(TupleDesc tupleDesc) throws TypeMismatch;

        /**
         * Find a proper table alias name for each attribute that has no table name assigned.
         * @param attrNameToTableName a HashMap that map attribute name to table alias name.
         * @throws NoSuchElementException if we cannot find the table alias name or
         *          there exists different tables for one attribute.
         */
        void disambiguateName(HashMap<String, String> attrNameToTableName) throws NoSuchElementException;
    }


    /**key op const*/
    public static class KVCmp implements Cmp{
        public FullColumnName fullColumnName;
        public Op op;
        public Object value;
        public KVCmp(FullColumnName fullColumnName, Op op, Object value) {
            this.fullColumnName = fullColumnName;
            this.op = op;
            this.value = value;
        }

        @Override
        public Predicate predicate(TupleDesc tupleDesc) throws TypeMismatch{
            return new KVCmpPredicate(this, tupleDesc);
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) {
            fullColumnName.disambiguateName(attrNameToTableName);
        }
    }

    /**key op key*/
    public static class KKCmp implements Cmp {
        public FullColumnName lhs, rhs;
        Op op;
        public KKCmp(FullColumnName lhs, Op op, FullColumnName rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
            this.op = op;
        }

        @Override
        public Predicate predicate(TupleDesc tupleDesc) throws TypeMismatch{
            return new KKCmpPredicate(this, tupleDesc);
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) throws NoSuchElementException {
            lhs.disambiguateName(attrNameToTableName);
            rhs.disambiguateName(attrNameToTableName);
        }
    }

    /**
     * const op const
     * the result would only be true or false
     * */
    public static class VVCmp implements Cmp{
        private boolean cmp;
        public VVCmp(Object lhs, Op op, Object rhs) {
            cmp = Util.getField(lhs).compare(op, Util.getField(rhs));
        }
        public VVCmp(boolean cmp) {
            this.cmp = cmp;
        }

        public Predicate predicate(){
            return new Predicate() {
                @Override
                public boolean filter(Tuple tuple) throws TypeMismatch {
                    return cmp;
                }
            };
        }

        @Override
        public Predicate predicate(TupleDesc tupleDesc){
            return predicate();
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) throws NoSuchElementException {
        }
    }

    public static class And extends ArrayList<Cmp> implements Cmp{
//        /**
//         * Extract one IndexPredicate from And
//         *
//         * @param table
//         * @return
//         */
//        public IndexPredicate extractIndexPredicate(Table table) throws TypeMismatch{
//            for(int i=0; i<size(); i++) {
//                if (get(i) instanceof KVCmp) {
//                    FullColumnName fullColumnName = ((KVCmp) get(i)).fullColumnName;
//                    if (table.getTupleDesc().isPrimaryKey(fullColumnName.attrName)) {
//                        KVCmp cmp = (KVCmp)remove(i);
//                        TupleDesc tupleDesc = table.getTupleDesc();
//                        int idx = tupleDesc.fieldNameToIndex(fullColumnName.attrName);
//                        return new IndexPredicate(cmp.op, Util.getField(cmp.value, tupleDesc.getTDItem(idx)));
//                    }
//                }
//            }
//            return null;
//        }

        /**
         * Extract an IndexPredicate from And
         * IndexPredicate is a KVPredicate whose Key is a Primary Key in the LogicalScanNode's Table
         * the KVCmp will be removed from And
         * @param node
         * @return
         * @throws TypeMismatch
         */
        public IndexPredicate extractIndexPredicate(LogicalScanNode node) throws TypeMismatch {
            for (int i=0; i<size(); i++) {
                if (get(i) instanceof KVCmp) {
                    FullColumnName fullColumnName = ((KVCmp) get(i)).fullColumnName;
                    if (fullColumnName.tableName.equals(node.tableAlias) &&
                            node.tupleDesc.isPrimaryKey(fullColumnName.attrName)) {
                        KVCmp cmp = (KVCmp)remove(i);
                        TupleDesc tupleDesc = node.tupleDesc;
                        int idx = tupleDesc.fieldNameToIndex(fullColumnName.attrName);
                        return new IndexPredicate(cmp.op, Util.getField(cmp.value, tupleDesc.getTDItem(idx)));
                    }
                }
            }
            return null;
        }

        /**
         * Extract KVPredicates from And, all the KVCmps will be removed from And
         * @param node
         * @return
         */
        public And extractKVPredicate(LogicalScanNode node) {
            And newAnd = new And();
            for (Cmp cmp: this) {
                if (cmp instanceof KVCmp) {
                    newAnd.add(cmp);
                }
            }
            this.removeIf(x -> x instanceof KVCmp);
            return newAnd;
        }

        /**
         *
         * @param tupleDesc
         * @return
         * @throws TypeMismatch
         */
        public Predicate predicate(TupleDesc tupleDesc) throws TypeMismatch{
            if (this.size() == 0) {
                return (new VVCmp(true)).predicate();
            } else {
                Predicate predicate = this.get(0).predicate(tupleDesc);
                for (int i=1; i<this.size(); i++) {
                    predicate = new LogicalPredicate(predicate, LogicalPredicate.Op.AND, this.get(i).predicate(tupleDesc));
                }
                return predicate;
            }
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) throws NoSuchElementException {
            iterator().forEachRemaining(x->x.disambiguateName(attrNameToTableName));
        }
    }

    public static class Or extends ArrayList<And> implements Cmp{
        /**
         *
         * @param tupleDesc
         * @return
         * @throws TypeMismatch
         */
        @Override
        public Predicate predicate(TupleDesc tupleDesc) throws TypeMismatch{
            if (this.size() == 0) {
                return (new VVCmp(false)).predicate();
            } else {
                Predicate predicate = this.get(0).predicate(tupleDesc);
                for (int i=1; i<this.size(); i++) {
                    predicate = new LogicalPredicate(predicate, LogicalPredicate.Op.OR, this.get(i).predicate(tupleDesc));
                }
                return predicate;
            }
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) throws NoSuchElementException {
            iterator().forEachRemaining(x->x.disambiguateName(attrNameToTableName));
        }
    }
}
