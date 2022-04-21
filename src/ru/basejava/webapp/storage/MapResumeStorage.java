package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected void makeUpdate(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void makeSave(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume makeTake(Resume resume) {
        return resume;
    }

    @Override
    protected void makeDelete(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    public List<Resume> getResumeList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getResume(String uuid) {
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
