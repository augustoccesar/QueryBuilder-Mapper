package br.com.augustoccesar.querybuilder.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Author: augustoccesar
 * Date: 17/05/17
 */
public class Executor {
    public interface ExecutorHandler {
        void handle(ResultSet resultSet);
    }

    public interface ExceptionHandler {
        void handle(Exception e);
    }

    public static void executeQuery(String sql, Connection connection, ExecutorHandler executorHandler) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    executorHandler.handle(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeQuery(String sql, Connection connection, ExecutorHandler executorHandler, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    executorHandler.handle(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeQuery(String sql, Connection connection, ExecutorHandler executorHandler, ExceptionHandler exceptionHandler) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    executorHandler.handle(rs);
                }
            }
        } catch (SQLException e) {
            exceptionHandler.handle(e);
        }
    }

    public static void executeQuery(String sql, Connection connection, ExecutorHandler executorHandler, ExceptionHandler exceptionHandler, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    executorHandler.handle(rs);
                }
            }
        } catch (SQLException e) {
            exceptionHandler.handle(e);
        }
    }
}
