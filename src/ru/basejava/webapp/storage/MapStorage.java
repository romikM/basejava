package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey((String) key);
    }

    @Override
    protected void makeUpdate(Resume r, Object key) {
        storage.replace((String) key, r);
    }

    @Override
    protected void makeSave(Resume r, Object key) {
        storage.put((String) key, r);
    }

    @Override
    protected Resume makeTake(Object key) {
        return storage.get((String) key);
    }

    @Override
    protected void makeDelete(Object key) {
        storage.remove((String) key);
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected String getResumeIdx(String key) {
        return key;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

}
