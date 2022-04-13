package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void insertItem(Resume r, int resumeIdx) {
        int insertPoint = -resumeIdx - 1;
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, size - insertPoint);
        storage[insertPoint] = r;
    }

    @Override
    protected void fillEmptyItem(int resumeIdx) {
        int countMovingItems = size - resumeIdx - 1;
        if (countMovingItems > 0) {
            System.arraycopy(storage, resumeIdx + 1, storage, resumeIdx, countMovingItems);
        }
    }

    public Integer getResume(String uuid) {
        Resume searchKey = new Resume(uuid, "omg");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

}
