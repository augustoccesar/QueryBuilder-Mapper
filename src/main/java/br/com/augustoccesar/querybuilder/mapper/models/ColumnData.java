package br.com.augustoccesar.querybuilder.mapper.models;

/**
 * Author: augustoccesar
 * Date: 15/05/17
 */
public class ColumnData {
    private String attributeName;
    private String name;

    public ColumnData() {
    }

    public ColumnData(String attributeName, String name) {
        this.attributeName = attributeName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
