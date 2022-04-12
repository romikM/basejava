package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected void makeUpdate(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void makeSave(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume makeTake(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void makeDelete(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }

    @Override
    public List<Resume> makeStorageDump() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getResumeIdx(String uuid) {
        return storage.get(uuid);
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
