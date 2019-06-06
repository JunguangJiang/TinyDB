package db.query.plan;

import db.error.SQLError;
import db.field.Field;
import db.field.Op;

import db.field.Util;
import db.file.BTree.IndexPredicate;
import db.query.FullColumnName;
import db.query.predicate.KKCmpPredicate;
import db.query.predicate.KVCmpPredicate;
import db.query.predicate.LogicalPredicate;
import db.query.predicate.Predicate;
import db.tuple.Tuple;
import db.tuple.TupleDesc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** A LogicalFilterNode represents the parameters of a filter in the WHERE clause of a query.
 */
public class LogicalFilterNode {
    public interface BaseFilterNode{
        /**
         * convert a clause into the corresponding Predicate
         * @param tupleDesc
         */
        Predicate predicate(TupleDesc tupleDesc) throws SQLError;

        /**
         * Find a proper table alias name for each attribute that has no table name assigned.
         * @param attrNameToTableName a HashMap that map attribute name to table alias name.
         * @throws NoSuchElementException if we cannot find the table alias name or
         *          there exists different tables for one attribute.
         */
        void disambiguateName(HashMap<String, String> attrNameToTableName) throws SQLError;
    }


    /**key op const*/
    public static class KVCmpNode implements BaseFilterNode{
        public FullColumnName fullColumnName;
        public Op op;
        public String value;
        public KVCmpNode(FullColumnName fullColumnName, Op op, String value) {
            this.fullColumnName = fullColumnName;
            this.op = op;
            this.value = value;
        }

        @Override
        public Predicate predicate(TupleDesc tupleDesc) throws SQLError{
            return new KVCmpPredicate(this, tupleDesc);
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) throws SQLError{
            fullColumnName.disambiguateName(attrNameToTableName);
        }
    }

    /**key op key*/
    public static class KKCmpNode implements BaseFilterNode {
        public FullColumnName lhs, rhs;
        public Op op;
        public KKCmpNode(FullColumnName lhs, Op op, FullColumnName rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
            this.op = op;
        }

        @Override
        public Predicate predicate(TupleDesc tupleDesc) throws SQLError{
            return new KKCmpPredicate(this, tupleDesc);
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) throws SQLError {
            lhs.disambiguateName(attrNameToTableName);
            rhs.disambiguateName(attrNameToTableName);
        }
    }

    /**
     * const op const
     * the result would only be true or false
     * */
    public static class VVCmpNode implements BaseFilterNode{
        private boolean cmp;
        public VVCmpNode(String lhs, Op op, String rhs) throws SQLError{
            Field lField = Util.getField(lhs);
            Field rField = Util.getField(rhs);
            if (lField.getType() != rField.getType()) {
                throw new SQLError(lField.getType() + " cannot be compared with "+rField.getType());
            }
            cmp = Util.getField(lhs).compare(op, Util.getField(rhs));
        }
        public VVCmpNode(boolean cmp) {
            this.cmp = cmp;
        }

        public Predicate predicate(){
            return new Predicate() {
                @Override
                public boolean filter(Tuple tuple) throws SQLError {
                    return cmp;
                }
            };
        }

        @Override
        public Predicate predicate(TupleDesc tupleDesc){
            return predicate();
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) throws SQLError {
        }
    }

    public static class AndNode extends ArrayList<BaseFilterNode> implements BaseFilterNode{
        /**
         * Extract an IndexPredicate from AndNode
         * IndexPredicate is a KVPredicate whose Key is a Primary Key in the LogicalScanNode's Table
         * the KVCmpNode will be removed from AndNode
         * @param scanNode
         * @return
         * @throws SQLError
         */
        public IndexPredicate extractIndexPredicate(LogicalScanNode scanNode) throws SQLError {
            AndNode andNode = getKVCmpNodes(scanNode.tableName, scanNode.tupleDesc.getPrimaryKey());
            if (andNode.size() >= 1) {
                KVCmpNode cmpNode = (KVCmpNode)andNode.get(0);
                this.remove(cmpNode);
                TupleDesc tupleDesc = scanNode.tupleDesc;
                int idx = tupleDesc.fieldNameToIndex(cmpNode.fullColumnName.attrName);
                return new IndexPredicate(cmpNode.op, Util.getField(cmpNode.value, tupleDesc.getTDItem(idx)));
            } else {
                return null;
            }
        }

        /**
         * Extract KVPredicates from AndNode, all the KVCmps will be removed from AndNode
         * @param node
         * @return
         */
        public AndNode extractKVPredicate(LogicalScanNode node) throws SQLError{
            AndNode andNode = getKVCmpNodes(node.tableName,null);
            this.removeAll(andNode);
            return andNode;
        }

        /**
         * Get all the KVCmpNodes that match the given tableName and primary key which means
         * KVCmpNodes.fullColumnName = (`tableName`.`primary key` as *)
         * @param tableName the table name, always match if null
         * @param primaryKey the primary key of the table, always match if null
         * @return
         * @throws SQLError
         */
        public AndNode getKVCmpNodes(String tableName, String primaryKey) throws SQLError {
            AndNode andNode = new AndNode();
            for (BaseFilterNode filterNode: this) {
                if (filterNode instanceof KVCmpNode) {
                    FullColumnName fullColumnName = ((KVCmpNode)filterNode).fullColumnName;
                    if ((tableName == null || tableName.equals(fullColumnName.tableName)) &&
                            (primaryKey == null || primaryKey.equals(fullColumnName.attrName))) {
                        andNode.add(filterNode);
                    }
                }
            }
            return andNode;
        }

        public Predicate predicate(TupleDesc tupleDesc) throws SQLError{
            if (this.size() == 0) {
                return (new VVCmpNode(true)).predicate();
            } else {
                Predicate predicate = this.get(0).predicate(tupleDesc);
                for (int i=1; i<this.size(); i++) {
                    predicate = new LogicalPredicate(predicate, LogicalPredicate.Op.AND, this.get(i).predicate(tupleDesc));
                }
                return predicate;
            }
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) throws SQLError {
            for (BaseFilterNode node: this){
                node.disambiguateName(attrNameToTableName);
            }
//            iterator().forEachRemaining(x->x.disambiguateName(attrNameToTableName));
        }
    }

    public static class OrNode extends ArrayList<AndNode> implements BaseFilterNode{
        @Override
        public Predicate predicate(TupleDesc tupleDesc) throws SQLError{
            if (this.size() == 0) {
                return (new VVCmpNode(false)).predicate();
            } else {
                Predicate predicate = this.get(0).predicate(tupleDesc);
                for (int i=1; i<this.size(); i++) {
                    predicate = new LogicalPredicate(predicate, LogicalPredicate.Op.OR, this.get(i).predicate(tupleDesc));
                }
                return predicate;
            }
        }

        @Override
        public void disambiguateName(HashMap<String, String> attrNameToTableName) throws SQLError {
//            iterator().forEachRemaining(x->x.disambiguateName(attrNameToTableName));
            for(AndNode node: this){
                node.disambiguateName(attrNameToTableName);
            }
        }
    }
}
