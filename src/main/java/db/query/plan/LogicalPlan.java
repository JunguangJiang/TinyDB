package db.query.plan;
import db.field.TypeMismatch;
import db.query.pipe.BTreeScan;
import db.file.BTree.IndexPredicate;
import db.query.*;
import db.query.pipe.Filter;
import db.query.pipe.Join;
import db.query.pipe.OpIterator;
import db.query.pipe.Project;
import db.query.plan.LogicalFilterNode.*;
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

    /**
     * Get a Filter OpIterator from scanNode and andNode.
     *  will do BTreeScan based on scanNode and IndexPredicate extracted from andNode
     *  and then do Filter based on the KVCmpPredicate extracted from andNode.
     * @param scanNode
     * @param andNode
     * @return
     * @throws TypeMismatch
     */
    private OpIterator getBTreeScan(LogicalScanNode scanNode, AndNode andNode) throws TypeMismatch{
        IndexPredicate indexPredicate = andNode.extractIndexPredicate(scanNode);
        BTreeScan scan = new BTreeScan(scanNode.tableName, scanNode.tableAlias, indexPredicate);
        Predicate predicate = andNode.extractKVPredicate(scanNode).predicate(scanNode.tupleDesc);
        return new Filter(predicate, scan);
    }

    /** Convert this LogicalPlan into a physicalPlan represented by a {@link OpIterator}.
     * Attempts to find the optimal plan to order the joins in the plan.
     *  @return A OpIterator representing this plan.
     */
    public OpIterator physicalPlan() throws TypeMismatch {
        OpIterator scans[]= new OpIterator[this.joinNodes.size()];

        if (or != null && or.size() == 1) {
            // If there are only "AND", we first use BTreeIndexSearch,
            // then we Filter each Table separately.
            LogicalFilterNode.AndNode andNode = or.get(0);
            for (int i=0; i<joinNodes.size(); i++) {
                scans[i] = getBTreeScan(joinNodes.get(i).scanNode,andNode);
            }
        } else {
            // If there are "OR", we do no optimization
            // If there are no where clause, we cannot do optimization
            for (int i = 0; i < joinNodes.size(); i++) {
                LogicalScanNode scanNode = joinNodes.get(i).scanNode;
                scans[i] = new BTreeScan(scanNode.tableName, scanNode.tableAlias,null);
            }
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

