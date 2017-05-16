package br.com.augustoccesar.querybuilder.mapper.models;

/**
 * Author: augustoccesar
 * Date: 15/05/17
 */
public class ColumnData {
    private String name;
    private ColumnType columnType;

    public ColumnData() {
    }

    public ColumnData(String name, ColumnType columnType) {
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
}
