package db.query;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class FullColumnName {
    public String tableName;
    public String attrName;
    public String attrAlias;

    /**
     * FullColumnName = tableName.attrName as alias
     * @param tableName might be null
     * @param attrName
     * @param alias might be null
     */
    public FullColumnName(String tableName, String attrName, String alias) {
        this.tableName = tableName;
        this.attrName = attrName;
        this.attrAlias = alias;
    }

//    public FullColumnName(String tableName, String attrName) {
//        this(tableName, attrName, "");
//    }

    @Override
    public String toString() {
        if (attrAlias == null) {
            if (tableName != null) {
                return tableName + '.' + attrName;
            } else {
                return attrName;
            }
        } else {
            return attrAlias;
        }
    }

    /**
     * Find a proper table alias name for FullColumnName if it has no table name assigned.
     * @param attrNameToTableName a HashMap that map attribute name to table alias name.
     * @throws NoSuchElementException if we cannot find the table alias name or
     *          there exists different tables for one attribute.
     */
    public void disambiguateName(HashMap<String, String> attrNameToTableName) throws NoSuchElementException {
        if (this.tableName == null) {
            if (attrNameToTableName.containsKey(attrName)){
                if (attrNameToTableName.get(attrName) == null) {
                    throw new NoSuchElementException(attrName+" might belong to different tables.");
                } else {
                    this.tableName = attrNameToTableName.get(attrName);
                }
            } else {
                throw new NoSuchElementException(attrName + " doesn't belong to any table.");
            }
        }
    }
}
