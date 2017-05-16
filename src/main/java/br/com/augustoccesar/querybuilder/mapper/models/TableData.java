package br.com.augustoccesar.querybuilder.mapper.models;

/**
 * Author: augustoccesar
 * Date: 15/05/17
 */
public class TableData {
    private String prefix;
    private String name;

    public TableData() {
    }

    public TableData(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
