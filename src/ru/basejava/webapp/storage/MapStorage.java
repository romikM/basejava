package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(String key) {
        return storage.containsKey(key);
    }

    @Override
    protected void makeUpdate(Resume r, String key) {
        storage.replace(key, r);
    }

    @Override
    protected void makeSave(Resume r, String key) {
        storage.put(key, r);
    }

    @Override
    protected Resume makeTake(String key) {
        return storage.get(key);
    }

    @Override
    protected void makeDelete(String key) {
        storage.remove(key);
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
