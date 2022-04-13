package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public List<Resume> getResumeList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected String getResume(String key) {
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
