package db.query;

public class Query {

    public Query(OpIterator root) {

    }

    public QueryResult execute() {
        return new QueryResult(false, "");
    }
}
