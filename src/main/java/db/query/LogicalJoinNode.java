package db.query;
/** A LogicalJoinNode represents the state needed of a join of two
 * tables in a LogicalPlan */
public class LogicalJoinNode {
    public LogicalFilterNode.Cmp cmp;
    public LogicalScanNode scanNode;

    /**
     * `JOIN table ON clause`
     * @param cmp the node of clause
     * @param scanNode node of table
     */
    public LogicalJoinNode(LogicalFilterNode.Cmp cmp, LogicalScanNode scanNode) {
        this.cmp = cmp;
        this.scanNode = scanNode;
    }
}
