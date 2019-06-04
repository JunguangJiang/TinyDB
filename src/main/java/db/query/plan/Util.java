package db.query.plan;

import db.DbException;
import db.GlobalManager;
import db.Setting;
import db.error.SQLError;

import db.file.BTree.BTreeFile;
import db.file.BTree.IndexPredicate;
import db.file.Table;
import db.query.pipe.*;
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
     * @throws SQLError
     */
    public static OpIterator getOptimizedBTreeScan(LogicalScanNode scanNode,
                                                   AndNode andNode) throws SQLError, DbException {
        IndexPredicate indexPredicate = andNode.extractIndexPredicate(scanNode);
        BTreeScan scan = new BTreeScan(scanNode.table, indexPredicate);
        Predicate predicate = andNode.extractKVPredicate(scanNode).predicate(scanNode.tupleDesc);
        return new BufferedFilter(predicate, scan);
    }

    public static OpIterator getOptimizedSeqScan(LogicalScanNode scanNode, AndNode andNode) throws SQLError, DbException{
        SeqScan scan = new SeqScan(scanNode.table);
        Predicate predicate = andNode.extractKVPredicate(scanNode).predicate(scanNode.tupleDesc);
        return new BufferedFilter(predicate,scan);
    }

    /**
     * Get a Scan OpIterator from scanNode
     * Scan might be BTreeScan or SeqScan, depending on GlobalManager
     *
     * @param scanNode
     * @param andNode
     * @param optimized
     * @return
     */
    public static OpIterator getScan(LogicalScanNode scanNode, AndNode andNode, boolean optimized)
            throws SQLError, DbException {
        if (Setting.isBTree) {
            if (optimized) {
                return getOptimizedBTreeScan(scanNode, andNode);
            } else {
                return new BTreeScan(scanNode.table,null);
            }
        } else {
            if (optimized) {
                return getOptimizedSeqScan(scanNode, andNode);
            } else {
                return new SeqScan(scanNode.table);
            }
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
