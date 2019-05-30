package db.query.plan;

import db.GlobalManager;
import db.error.SQLError;
import db.error.TypeMismatch;
import db.file.BTree.BTreeFile;
import db.file.BTree.IndexPredicate;
import db.file.Table;
import db.query.pipe.BTreeScan;
import db.query.pipe.Filter;
import db.query.pipe.OpIterator;
import db.query.pipe.SeqScan;
import db.query.plan.LogicalFilterNode.*;
import db.query.predicate.Predicate;

public class Util {

    /**
     * Get a Filter OpIterator from scanNode and andNode.
     * will do BTreeScan based on scanNode and IndexPredicate extracted from andNode
     * and then do Filter based on the KVCmpPredicate extracted from andNode.
     *
     * @param scanNode
     * @param andNode
     * @return
     * @throws TypeMismatch
     */
    public static OpIterator getOptimizedBTreeScan(LogicalScanNode scanNode,
                                                   AndNode andNode) throws TypeMismatch, SQLError {
        IndexPredicate indexPredicate = andNode.extractIndexPredicate(scanNode);
        BTreeScan scan = new BTreeScan(scanNode.table, indexPredicate);
        Predicate predicate = andNode.extractKVPredicate(scanNode).predicate(scanNode.tupleDesc);
        return new Filter(predicate, scan);
    }

    /**
     * Get a Scan OpIterator from scanNode
     * Scan might be BTreeScan or SeqScan, depending on GlobalManager
     *
     * @param scanNode
     * @param andNode
     * @param optimized
     * @return
     * @throws TypeMismatch
     */
    public static OpIterator getScan(LogicalScanNode scanNode, AndNode andNode, boolean optimized)
            throws TypeMismatch, SQLError {
        if (GlobalManager.isBTree()) {
            if (optimized) {
                return getOptimizedBTreeScan(scanNode, andNode);
            } else {
                return new BTreeScan(scanNode.table,null);
            }
        } else {
            return new SeqScan(scanNode.table);
        }
    }

    /**
     * get the Table of a certain name.
     * @param tableName
     * @param tableAlias
     * @return
     * @throws SQLError if the Table doesn't exist
     */
    public static Table getTable(String tableName, String tableAlias) throws SQLError{
        String errorMessage;
        if(tableAlias == null || tableAlias.length() == 0) {
            errorMessage = "Table "+ tableName + " doesn't exist.";
        } else{
            errorMessage = "Table " + tableAlias + "(" +tableName + ") doesn't exist.";
        }
        try {
            Table table = GlobalManager.getDatabase().getTable(tableName);
            if(table == null) {
                throw new SQLError(errorMessage);
            }
            return table;
        } catch (NullPointerException e){
            throw new SQLError(errorMessage);
        }
    }

}
