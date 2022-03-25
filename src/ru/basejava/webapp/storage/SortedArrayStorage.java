package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertItem(Resume r, int index) {
        int insertPoint = -index - 1; // вычисляем позицию "дырки" для нового эл-та по результатам Arrays.binarySearch
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, size - insertPoint);
        // копируем эл-ты после позиции "дырка и далее" на позицию "вправо"
        storage[insertPoint] = r; // заполняем "дырку"
    }

    @Override
    protected void fillEmptyItem(int index) {
        // System.arrayCopy(fromArr, fromIndex, toArr, toIndex, count);
        int countMovingItems = size - index - 1; // все эл-ты после "дырки"
        if (countMovingItems > 0) { // если нечего двигать, то и нечего двигать
            System.arraycopy(storage, index + 1, storage, index, countMovingItems);
            // с позиции "следующий после дырки" двигаем на позицию "дырка", все эл-ты после "дырки"
        }
    }

    /**
     * Arrays.binarySearch as the 'standard' in Java is to return the position of the value in the array, or,
     * if the value does not exist in the array, return - ip - 1
     * where 'ip' is the 'insertion point' or where the new value should be.
     */
    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
