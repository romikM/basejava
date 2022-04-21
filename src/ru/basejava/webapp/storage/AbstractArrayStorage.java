package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    @Override
    public List<Resume> getResumeList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    protected void makeUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    protected void makeSave(Resume r, Integer index) {
        if (size == STORAGE_MAX_SIZE) {
            throw new StorageException("Storage size limit exceed.", r.getUuid());
        }
        insertItem(r, index);
        size++;
    }

    @Override
    protected Resume makeTake(Integer index) {
        return storage[index];
    }

    @Override
    protected void makeDelete(Integer index) {
        fillEmptyItem(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract Integer getResume(String uuid);

    protected abstract void fillEmptyItem(int index);

    protected abstract void insertItem(Resume r, int index);
}
