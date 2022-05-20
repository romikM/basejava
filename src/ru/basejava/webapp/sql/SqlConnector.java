package ru.basejava.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlConnector {
    Connection getConnect() throws SQLException;
}
