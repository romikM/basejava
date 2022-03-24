package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_MAX_SIZE = 10000;
    protected final Resume[] storage = new Resume[STORAGE_MAX_SIZE];

    protected int size;

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
