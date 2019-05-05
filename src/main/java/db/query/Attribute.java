package db.query;

public class Attribute {
    public String tableName = null;
    public String attrName = null;

    public Attribute(String tableName, String attrName) {
        this.tableName = tableName;
        this.attrName = attrName;
    }

    @Override
    public String toString() {
        return this.tableName + '.' + this.attrName;
    }
}
