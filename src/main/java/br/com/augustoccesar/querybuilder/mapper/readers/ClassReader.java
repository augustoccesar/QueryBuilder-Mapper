package br.com.augustoccesar.querybuilder.mapper.readers;

import br.com.augustoccesar.querybuilder.mapper.models.ColumnData;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: augustoccesar
 * Date: 15/05/17
 */
public class ClassReader {
    private ArrayList<ColumnData> columnDataList = new ArrayList<>();

    public static ClassReader read(Class obj) {
        ClassReader classReader = new ClassReader();

        Arrays.asList(obj.getDeclaredFields()).forEach(field -> {
            final boolean[] setViaAnnotation = {false};

            Arrays.asList(field.getDeclaredAnnotations()).forEach(annotation -> {
                if (annotation instanceof br.com.augustoccesar.querybuilder.mapper.annotations.Column) {
                    ColumnData columnData = new ColumnData();
                    columnData.setAttributeName(field.getName());
                    columnData.setName(((br.com.augustoccesar.querybuilder.mapper.annotations.Column) annotation).name());

                    classReader.columnDataList.add(columnData);
                    setViaAnnotation[0] = true;
                } else if (annotation instanceof javax.persistence.Column) {
                    ColumnData columnData = new ColumnData();
                    columnData.setAttributeName(field.getName());
                    columnData.setName(((javax.persistence.Column) annotation).name());

                    classReader.columnDataList.add(columnData);
                    setViaAnnotation[0] = true;
                }
            });

            if (!setViaAnnotation[0]) {
                ColumnData columnData = new ColumnData();

                String regex = "([a-z])([A-Z]+)";
                String replacement = "$1_$2";

                columnData.setAttributeName(field.getName());
                columnData.setName(field.getName().replaceAll(regex, replacement).toLowerCase());

                classReader.columnDataList.add(columnData);
            }
        });

        return classReader;
    }

    public ArrayList<ColumnData> getColumnDataList() {
        return columnDataList;
    }
}
