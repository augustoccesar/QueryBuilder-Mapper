package br.com.augustoccesar.querybuilder.mapper.models;

/**
 * Author: augustoccesar
 * Date: 15/05/17
 */
public class ColumnData {
    private String attributeName;
    private String name;
    private ColumnType columnType;

    public ColumnData() {
    }

    public ColumnData(String attributeName, String name, ColumnType columnType) {
        this.attributeName = attributeName;
        this.name = name;
        this.columnType = columnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
