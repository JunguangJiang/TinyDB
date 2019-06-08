package db.query.plan;

import java.util.ArrayList;
import java.util.HashMap;

/** A LogicalJoinNode represents the state needed of a join of two
 * tables in a LogicalPlan */
public class LogicalJoinNode {
    public LogicalFilterNode.BaseFilterNode cmp;
    public LogicalScanNode scanNode;
    public Type type;
    public enum Type{
        JOIN,
        NATURAL_JOIN,
        START
    }

    /**
     * `JOIN table ON clause`
     * @param cmp the node of clause
     * @param scanNode node of table
     */
    public LogicalJoinNode(LogicalFilterNode.BaseFilterNode cmp, LogicalScanNode scanNode, Type type) {
        this.cmp = cmp;
        this.scanNode = scanNode;
        this.type = type;
    }

    /**
     * get an AttrToTable from JoinNodes
     * @param joinNodes
     * @return AttrToTable will map an attrName to its belonging tableName
     *          map to null if attrName might belong to different tables
     */
    public static HashMap<String, String> getAttrToTable(ArrayList<LogicalJoinNode> joinNodes) {
        HashMap<String, String> attrToTable = new HashMap();
        for (LogicalJoinNode node: joinNodes) {
            String[] attrNames = node.scanNode.tupleDesc.getAttrNames();
            String tableAlias = node.scanNode.tableAlias;
            for(String attrName: attrNames) {
                if (attrToTable.containsKey(attrName)) { //ambiguous attrName
                    attrToTable.put(attrName, null);
                } else {
                    attrToTable.put(attrName, tableAlias);
                }
            }
        }
        return attrToTable;
    }
}
