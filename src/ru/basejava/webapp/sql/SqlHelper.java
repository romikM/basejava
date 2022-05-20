package ru.basejava.webapp.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final SqlConnector connector;

    public SqlHelper(SqlConnector connector) {
        this.connector = connector;
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String query, SqlRunner<T> executor) {
        try (Connection conn = connector.getConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw SqlExceptionMod.modifyException(e);
        }
    }
}
