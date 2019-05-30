package db.query.plan;
import db.error.SQLError;
import db.query.pipe.*;
import db.query.*;
import db.query.predicate.Predicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;


/**
 * LogicalPlan represents a logical query plan that has been through
 * the parser and is ready to be processed by the optimizer.
 * <p>
 * A LogicalPlan consits of a collection of table scan nodes, join
 * nodes, filter nodes, a select list.
 * <p>
 * LogicalPlans can be converted to physical (optimized) plans using
 * the {@link #physicalPlan} method
 */
public class LogicalPlan
{
    private LogicalFilterNode.OrNode or;
    private ArrayList<LogicalJoinNode> joinNodes;
    private ArrayList<FullColumnName> fullColumnNames;

    /**
     * SELECT `fullColumnNames` FROM `joinNodes` WHERE `or`
     * @param or nodes of the clause. Might be null
     * @param joinNodes nodes of joined table
     * @param fullColumnNames nodes of project elements. null if project all the elements
     */
    public LogicalPlan(LogicalFilterNode.OrNode or,
                       ArrayList<LogicalJoinNode> joinNodes,
                       ArrayList<FullColumnName> fullColumnNames){
        this.or = or;
        this.joinNodes = joinNodes;
        this.fullColumnNames = fullColumnNames;
        disambiguateName();
    }


    /**
     * Fulfill the tableName of all the FullColumnNames
     *  inside `or`, `joinNodes` and `fullColumnNames`
     * @throws NoSuchElementException if we cannot find the table alias name or
     *      there exists different tables for one attribute.
     */
    private void disambiguateName() throws NoSuchElementException {
        HashMap<String, String> attrToTable = LogicalJoinNode.getAttrToTable(joinNodes);

        if (or != null) {
            or.disambiguateName(attrToTable);
        }
        for(LogicalJoinNode joinNode: joinNodes) {
            if (joinNode.cmp != null) {
                joinNode.cmp.disambiguateName(attrToTable);
            }
        }
        if (fullColumnNames != null) {
            fullColumnNames.iterator().forEachRemaining(x->x.disambiguateName(attrToTable));
        }
    }

    /** Convert this LogicalPlan into a physicalPlan represented by a {@link OpIterator}.
     * Attempts to find the optimal plan to order the joins in the plan.
     *  @return A OpIterator representing this plan.
     */
    public OpIterator physicalPlan() throws SQLError {
        OpIterator scans[]= new OpIterator[this.joinNodes.size()];

        LogicalFilterNode.AndNode andNode=null;
        boolean optimized=false;
        if (or != null && or.size() == 1) {
            // If there are only "AND", we first use BTreeIndexSearch,
            // then we Filter each Table separately.
            andNode = or.get(0);
            optimized = true;
        }
        // If there are "OR", we do no optimization
        // If there are no where clause, we cannot do optimization

        // Scan
        for (int i = 0; i < joinNodes.size(); i++) {
            LogicalScanNode scanNode = joinNodes.get(i).scanNode;
            scans[i] = Util.getScan(scanNode, andNode, optimized);
        }

        // Join (no optimization here)
        assert scans.length >= 1;
        OpIterator opIterator = scans[0];
        for (int i=1; i<scans.length; i++){
            opIterator = new Join(opIterator, joinNodes.get(i).cmp, scans[i]);
        }

        // Filter the result
        if (or != null) {
            Predicate filterPredicate = or.predicate(opIterator.getTupleDesc());
            opIterator = new Filter(filterPredicate, opIterator);
        }

        // Project the result
        if (fullColumnNames == null) {
            fullColumnNames = opIterator.getTupleDesc().fullColumnNames();
        }
        opIterator = new Project(fullColumnNames.toArray(new FullColumnName[0]), opIterator);

        return opIterator;
    }

}

