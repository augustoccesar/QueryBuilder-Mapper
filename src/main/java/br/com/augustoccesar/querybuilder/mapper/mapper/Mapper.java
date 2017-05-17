package br.com.augustoccesar.querybuilder.mapper.mapper;

import br.com.augustoccesar.querybuilder.mapper.readers.ClassReader;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: augustoccesar
 * Date: 16/05/17
 */
public class Mapper<T> {
    private String alias;

    public Mapper(String alias) {
        this.alias = alias;
    }

    @SuppressWarnings("unchecked")
    public T map(ResultSet rs, Class clazz) {
        try {
            T obj = (T) clazz.newInstance();

            ClassReader classReader = ClassReader.read(clazz);
            classReader.getColumnDataList().forEach(columnData -> {
                try {
                    String columnName = alias + "_" + columnData.getName();
                    if (hasColumn(rs, columnName)) {
                        BeanUtils.setProperty(obj, columnData.getAttributeName(), rs.getObject(columnName));
                    }
                } catch (IllegalAccessException | InvocationTargetException | SQLException e) {
                    e.printStackTrace();
                }
            });

            return obj;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
