package db.query;

import db.GlobalManager;
import db.file.Table;
import db.tuple.TupleDesc;

/** A LogicalScanNode represents table in the FROM list in a
 * LogicalQueryPlan */
public class LogicalScanNode {
    public String tableName; // table name
    public int tableId; // table id
    public String tableAlias; // table alias(same Table may have different alias)
    public TupleDesc tupleDesc; // the TupleDesc of Table

    /**
     *
     * @param tableName the name of the Table
     * @param tableAlias the alias of the Table, might be null
     * @throws NullPointerException
     */
    public LogicalScanNode(String tableName, String tableAlias) throws NullPointerException {
        this.tableName = tableName;
        if (tableAlias == null) {
            this.tableAlias = tableName;
        } else {
            this.tableAlias = tableAlias;
        }
        Table table = GlobalManager.getDatabase().getTable(tableName);
        this.tableId = table.getId();
        table.getTupleDesc().setTableName(this.tableAlias);
        this.tupleDesc = table.getTupleDesc();
    }

    public LogicalScanNode(String tableName) {
        this(tableName, null);
    }
}