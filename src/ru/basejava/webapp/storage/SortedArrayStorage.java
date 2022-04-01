package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertItem(Resume r, int index) {
        int insertPoint = -index - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, size - insertPoint);
        storage[insertPoint] = r;
    }

    @Override
    protected void fillEmptyItem(int index) {
        int countMovingItems = size - index - 1;
        if (countMovingItems > 0) {
            System.arraycopy(storage, index + 1, storage, index, countMovingItems);
        }
    }

    /**
     * Arrays.binarySearch as the 'standard' in Java is to return the position of the value in the array, or,
     * if the value does not exist in the array, return - ip - 1
     * where 'ip' is the 'insertion point' or where the new value should be.
     */
    public int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
