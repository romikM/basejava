package ru.basejava.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume with uuid=" + uuid + " already exist.", uuid);
    }
}
