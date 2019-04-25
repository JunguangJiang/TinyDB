package db.query;

/**
 * The result of a query
 */
public class QueryResult {
    private boolean succeed;
    private String info;

    /**
     * Constructor
     * @param succeed whether the query was executed successfully
     * @param info the string to be printed to the output, e.g error message
     */
    public QueryResult(boolean succeed, String info) {
        this.succeed = succeed;
        this.info = info;
    }

    /**
     *
     * @return whether the query was executed successfully
     */
    public boolean succeeded() {
        return this.succeed;
    }

    /**
     *
     * @return the string to be printed to the output, e.g error message
     */
    public String getInfo() {
        return this.info;
    }

}
