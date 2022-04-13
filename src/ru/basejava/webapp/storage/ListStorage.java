package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> resumeList = new ArrayList<>();

    @Override
    protected Object getResume(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void makeUpdate(Resume r, Object key) {
        resumeList.set((Integer) key, r);
    }

    @Override
    protected void makeSave(Resume r, Object key) {
        resumeList.add(r);
    }

    @Override
    protected Resume makeTake(Object key) {
        return resumeList.get((Integer) key);
    }

    @Override
    protected void makeDelete(Object key) {
        resumeList.remove(((Integer) key).intValue());
    }

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public List<Resume> getResumeList() {
        return new ArrayList<>(resumeList);
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
