package db.query;

import db.Table;

public class Project {
    public static class ProjectElement {
        private Table table = null;
        private String attrName = null;
//        private String alias = null;

        public ProjectElement(Table table, String attrName) {
            this.table = table;
            this.attrName = attrName;
//            this.alias = alias;
        }
    }

    public Project(ProjectElement[] projectElements, OpIterator child) {

    }
}
