package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_MAX_SIZE = 10000;
    protected final Resume[] storage = new Resume[STORAGE_MAX_SIZE];

    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean isExist(Object resumeIdx) {
        return (Integer) resumeIdx >= 0;
    }

    @Override
    protected void makeUpdate(Resume r, Object resumeIdx) {
        storage[(Integer) resumeIdx] = r;
    }

    @Override
    protected void makeSave(Resume r, Object resumeIdx) {
        if (size == STORAGE_MAX_SIZE) {
            throw new StorageException("Storage size limit exceed.", r.getUuid());
        }
        insertItem(r, (Integer) resumeIdx);
        size++;
    }

    @Override
    protected Resume makeTake(Object resumeIdx) {
        return storage[(Integer) resumeIdx];
    }

    @Override
    protected void makeDelete(Object resumeIdx) {
        fillEmptyItem((Integer) resumeIdx);
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract Integer getResumeIdx(String uuid);

    protected abstract void fillEmptyItem(int index);

    protected abstract void insertItem(Resume r, int index);
}
