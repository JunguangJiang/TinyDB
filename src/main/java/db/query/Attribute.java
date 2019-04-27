package db.query;

import db.file.Table;

public class Attribute {
//    public Table table = null;
    public String tableName = null;
    public String attrName = null;

    public Attribute(String tableName, String attrName) {
//        this.table = table;
        this.tableName = tableName;
        this.attrName = attrName;
    }
}
