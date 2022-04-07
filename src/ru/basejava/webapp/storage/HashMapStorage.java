package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.*;


public class HashMapStorage extends AbstractStorage {
    final Map<String, Resume> hashMap = new HashMap<>();
    @Override
    protected boolean isExist(Object resumeIdx) {
        return hashMap.containsKey((String) resumeIdx);
    }

    @Override
    protected void makeUpdate(Resume r, Object resumeIdx) {
        hashMap.replace((String) resumeIdx, r);
    }

    @Override
    protected void makeSave(Resume r, Object resumeIdx) {
        hashMap.put((String) resumeIdx, r);
    }

    @Override
    protected Resume makeTake(Object resumeIdx) {
        return hashMap.get((String) resumeIdx);
    }

    @Override
    protected void makeDelete(Object resumeIdx) {
        hashMap.remove((String) resumeIdx);
    }

    @Override
    public List<Resume> makeStorageCopy() {
        return new ArrayList<>((Collection) hashMap);
    }

    @Override
    protected String getResumeIdx(String resumeIdx) {
        return resumeIdx;
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public ArrayList<Resume> getAll() {
        return new ArrayList<>(hashMap.values());
    }

    @Override
    public int size() {
        return hashMap.size();
    }
}
