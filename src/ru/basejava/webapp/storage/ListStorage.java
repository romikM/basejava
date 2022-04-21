package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> resumeList = new ArrayList<>();

    @Override
    protected Integer getResume(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer key) {
        return key != null;
    }

    @Override
    protected void makeUpdate(Resume r, Integer key) {
        resumeList.set(key, r);
    }

    @Override
    protected void makeSave(Resume r, Integer key) {
        resumeList.add(r);
    }

    @Override
    protected Resume makeTake(Integer key) {
        return resumeList.get(key);
    }

    @Override
    protected void makeDelete(Integer key) {
        resumeList.remove(key.intValue());
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
