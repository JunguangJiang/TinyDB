package db.query;
import db.field.TypeMismatch;
import db.file.BTree.BTreeScan;
import db.file.BTree.IndexPredicate;
import db.query.LogicalFilterNode.*;
import db.tuple.TDItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private LogicalFilterNode.Or or;
    private ArrayList<LogicalJoinNode> joinNodes;
    private ArrayList<FullColumnName> fullColumnNames;

    /**
     * SELECT `fullColumnNames` FROM `joinNodes` WHERE `or`
     * @param or nodes of the clause. Might be null
     * @param joinNodes nodes of joined table
     * @param fullColumnNames nodes of project elements. null if project all the elements
     */
    public LogicalPlan(LogicalFilterNode.Or or,
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
    public OpIterator physicalPlan() throws TypeMismatch {
        OpIterator opIterator = null;
        OpIterator scans[]= new OpIterator[this.joinNodes.size()];
        Predicate filterPredicate;

        if (or != null && or.size() == 1) {
            // If there are only "AND", we first use BTreeIndexSearch,
            // then we Filter each Table separately.
            LogicalFilterNode.And and = or.get(0);
            for (int i=0; i<joinNodes.size(); i++) {
                LogicalJoinNode joinNode = joinNodes.get(i);
                IndexPredicate indexPredicate = and.extractIndexPredicate(joinNode.scanNode);
                OpIterator scan = new BTreeScan(joinNode.scanNode.tableName,
                        joinNode.scanNode.tableAlias, indexPredicate);
                Predicate p = and.extractKVPredicate(joinNode.scanNode).predicate(
                        joinNode.scanNode.tupleDesc);
                scan = new Filter(p, scan);
                scans[i] = scan;
            }
        } else {
            // If there are "OR", we do no optimization
            // If there are no where clause, we cannot do optimization
            for (int i = 0; i < joinNodes.size(); i++) {
                scans[i] = new BTreeScan(joinNodes.get(i).scanNode.tableName,
                        joinNodes.get(i).scanNode.tableAlias,null);
            }
        }

        // Join (no optimization here)
        for (int i=0; i<scans.length; i++){
            OpIterator scan = scans[i];
            LogicalJoinNode joinNode = joinNodes.get(i);
            if (opIterator == null) {
                opIterator = scan;
            } else {
                if (joinNode.cmp != null) {
                    opIterator = new Join(opIterator, joinNode.cmp, scan);
                } else {
                    opIterator = new Join(opIterator, new VVCmp(true), scan);
                }
            }
        }

        if (opIterator == null){
            throw new IllegalStateException();
        }

        // Filter the result
        if (or != null) {
            filterPredicate = or.predicate(opIterator.getTupleDesc());
            opIterator = new Filter(filterPredicate, opIterator);
        }

        if (fullColumnNames == null) {
            fullColumnNames = new ArrayList<>();
            Iterator<TDItem> iterator = opIterator.getTupleDesc().iterator();
            while (iterator.hasNext()) {
                TDItem tdItem = iterator.next();
                if (!tdItem.fieldName.equals("PRIMARY")) {
                    fullColumnNames.add(new FullColumnName(tdItem.tableName, tdItem.fieldName, null));
                }
            }
        }
        // Project the result
        opIterator = new Project(fullColumnNames.toArray(new FullColumnName[0]), opIterator);

        return opIterator;
    }

}

