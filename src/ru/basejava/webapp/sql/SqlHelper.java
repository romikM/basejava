package ru.basejava.webapp.sql;

import ru.basejava.webapp.exception.StorageException;

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

    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection conn = connector.getConnect()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                throw SqlExceptionMod.modifyException(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
