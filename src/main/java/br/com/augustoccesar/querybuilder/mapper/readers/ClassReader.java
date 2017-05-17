package br.com.augustoccesar.querybuilder.mapper.readers;

import br.com.augustoccesar.querybuilder.mapper.annotations.Column;
import br.com.augustoccesar.querybuilder.mapper.annotations.Table;
import br.com.augustoccesar.querybuilder.mapper.models.ColumnData;
import br.com.augustoccesar.querybuilder.mapper.models.TableData;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: augustoccesar
 * Date: 15/05/17
 */
public class ClassReader {
    private TableData tableData = new TableData();
    private ArrayList<ColumnData> columnDataList = new ArrayList<>();

    public static ClassReader read(Class obj) {
        ClassReader classReader = new ClassReader();

        Arrays.asList(obj.getDeclaredAnnotations()).forEach(annotation -> {
            if (annotation instanceof Table) {
                classReader.tableData.setName(((Table) annotation).name());
                classReader.tableData.setPrefix(((Table) annotation).prefix());
            }
        });

        Arrays.asList(obj.getDeclaredFields()).forEach(field -> {
            Arrays.asList(field.getDeclaredAnnotations()).forEach(annotation -> {
                if (annotation instanceof Column) {
                    ColumnData columnData = new ColumnData();
                    columnData.setAttributeName(field.getName());
                    columnData.setName(((Column) annotation).name());
                    columnData.setColumnType(((Column) annotation).type());

                    classReader.columnDataList.add(columnData);
                }
            });
        });

        return classReader;
    }

    public TableData getTableData() {
        return tableData;
    }

    public ArrayList<ColumnData> getColumnDataList() {
        return columnDataList;
    }
}
