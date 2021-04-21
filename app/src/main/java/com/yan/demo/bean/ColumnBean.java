package com.yan.demo.bean;

public class ColumnBean {
    private float columnValue;
    private String columnName;

    public ColumnBean(float columnValue, String columnName) {
        this.columnValue = columnValue;
        this.columnName = columnName;
    }

    public float getColumnValue() {
        return columnValue;
    }

    public String getColumnName() {
        return columnName;
    }
}
