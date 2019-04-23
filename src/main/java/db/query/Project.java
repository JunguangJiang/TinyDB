package db.query;

public class Project {
    public static class ProjectElement {
        private String tableName = null;
        private String attrName = null;
        private String alias = null;

        public ProjectElement(String tableName, String attrName, String alias) {
            this.tableName = tableName;
            this.attrName = attrName;
            this.alias = alias;
        }
    }

    public Project(ProjectElement[] projectElements, OpIterator child) {

    }
}
