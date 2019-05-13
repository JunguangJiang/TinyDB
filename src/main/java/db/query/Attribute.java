package db.query;

public class Attribute {
    public String tableName;
    public String attrName;
    public String alias;

    public Attribute(String tableName, String attrName, String alias) {
        this.tableName = tableName;
        this.attrName = attrName;
        this.alias = alias;
    }

    public Attribute(String tableName, String attrName) {
        this(tableName, attrName, "");
    }

    @Override
    public String toString() {
        return this.tableName + '.' + this.attrName;
    }
}
