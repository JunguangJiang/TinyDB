package db.query.predicate;

import db.error.SQLError;

import db.tuple.Tuple;

/**
 * Predicate is used to filter tuples
 */
public abstract class Predicate{
    /**
     * @param tuple
     * @return true if reserving the tuple after the filter
     */
    public abstract boolean filter(Tuple tuple) throws SQLError;
}
