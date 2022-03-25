package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    // методы не изменились
    @Override
    protected void insertItem(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void fillEmptyItem(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
