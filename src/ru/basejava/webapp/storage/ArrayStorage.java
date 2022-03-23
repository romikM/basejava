package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    private int size;

    public void clear() {
        // циклично обнулили каждый эл-т, размер в ноль
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Error updating Resume! Resume with uuid " + r.getUuid() + " doesn't exist!");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            System.out.println("Error saving Resume! Resume with uuid " + r.getUuid() + " already exist!");
        } else if (size == 10000) {
            System.out.println("Error saving resume! Storage size limit exceed.");
        }  else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Error getting Resume! Resume with uuid " + uuid + " doesn't exist!");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Error deleting Resume! Resume with uuid " + uuid + " doesn't exist!");
        } else {
           storage[index] = storage[size - 1];
           storage[size - 1] = null;
           size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        // создали новый массив размера size, поэлементно перенесли в него storage.
        Resume[] allResume = new Resume[size()];
        for (int i = 0; i < size(); i++) {
            allResume[i] = storage[i];
        }
        return allResume;
    }

    public int getIndex (String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }
}
