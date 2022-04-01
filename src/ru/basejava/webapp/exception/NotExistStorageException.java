package ru.basejava.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume with uuid=" + uuid + " doesn't exist.", uuid);
    }
}
