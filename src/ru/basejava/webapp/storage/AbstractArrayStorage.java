package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

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

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Error updating Resume! Resume with uuid " + r.getUuid() + " doesn't exist!");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            System.out.println("Error saving Resume! Resume with uuid " + r.getUuid() + " already exist!");
        } else if (size == STORAGE_MAX_SIZE) {
            System.out.println("Error saving resume! Storage size limit exceed.");
        } else {
            insertItem(r, index);
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Error getting Resume! Resume with uuid " + uuid + " doesn't exist!");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Error deleting Resume! Resume with uuid " + uuid + " doesn't exist!");
        } else {
            fillEmptyItem(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void fillEmptyItem(int index);

    protected abstract void insertItem(Resume r, int index);
}
