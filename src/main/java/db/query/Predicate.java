package db.query;

import db.Tuple;

public interface Predicate{
    public boolean filter(Tuple tuple);
    public String toString();
}
