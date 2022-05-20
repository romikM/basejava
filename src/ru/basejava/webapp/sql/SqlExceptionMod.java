package ru.basejava.webapp.sql;

import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.StorageException;

import java.sql.SQLException;

public class SqlExceptionMod {
    private SqlExceptionMod() {
    }

    public static StorageException modifyException(SQLException e) {
        //System.out.println(e.getSQLState());
        if (e.getSQLState().equals("23505")) {
            return new ExistStorageException(null);
        }
        return new StorageException(e);
    }
}
