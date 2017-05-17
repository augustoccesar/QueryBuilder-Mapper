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

    public void map(ResultSet rs, T obj) {
        ClassReader classReader = ClassReader.read(obj.getClass());
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
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    public T map(ResultSet rs, Class clazz) {
        try {
            T obj = (T) clazz.newInstance();

            this.map(rs, obj);

            return obj;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public T mapToNew(ResultSet rs, Class clazz) {
        try {
            T obj = (T) clazz.newInstance();
            this.map(rs, obj);
            return obj;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Private Methods

    private static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
