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
     * do Filter based on the KVCmpPredicate extracted from andNode.
     * @param scan
     * @param scanNode
     * @param andNode
     * @return
     * @throws SQLError
     * @throws DbException
     */
    private static OpIterator optimize(OpIterator scan, LogicalScanNode scanNode, AndNode andNode) throws SQLError, DbException{
        AndNode kvnodes = andNode.extractKVPredicate(scanNode);
        if (kvnodes.size() == 0){
            return scan;
        } else {
            Predicate predicate = kvnodes.predicate(scanNode.tupleDesc);
            return new BufferedFilter(predicate, scan);
        }
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
        OpIterator scan;
        if (Setting.isBTree) {
            IndexPredicate indexPredicate = null;
            if (optimized){
                indexPredicate = andNode.extractIndexPredicate(scanNode);
            }
            scan = new BTreeScan(scanNode.table,indexPredicate);
        } else {
            scan = new SeqScan(scanNode.table);
        }
        if (optimized) {
            scan = optimize(scan, scanNode, andNode);
        }
        return scan;
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
