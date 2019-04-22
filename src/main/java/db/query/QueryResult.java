package db.query;

public class QueryResult {
    private boolean succeed;
    private String info;

    public QueryResult(boolean succeed, String info) {
        this.succeed = succeed;
        this.info = info;
    }

    public boolean succeeded() {
        return this.succeed;
    }

    public String getInfo() {
        return this.info;
    }

}
