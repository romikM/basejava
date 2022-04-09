package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_MAX_SIZE = 10000;
    protected Resume[] storage = new Resume[STORAGE_MAX_SIZE];

    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


    @Override
    protected boolean isExist(Object resumeIdx) {
        return (int) resumeIdx >= 0;
    }

    @Override
    protected void makeUpdate(Resume r, Object resumeIdx) {
        storage[(int) resumeIdx] = r;
    }

    @Override
    protected void makeSave(Resume r, Object resumeIdx) {
        if (size == STORAGE_MAX_SIZE) {
            throw new StorageException("Storage size limit exceed.", r.getUuid());
        }
        insertItem(r, (int) resumeIdx);
        size++;
    }

    @Override
    protected Resume makeTake(Object resumeIdx) {
        return storage[(int) resumeIdx];
    }

    @Override
    protected void makeDelete(Object resumeIdx) {
        fillEmptyItem((int) resumeIdx);
        storage[size - 1] = null;
        size--;
    }

    protected abstract Integer getResumeIdx(String uuid);

    protected abstract void fillEmptyItem(int resumeIdx);

    protected abstract void insertItem(Resume r, int resumeIdx);
}
