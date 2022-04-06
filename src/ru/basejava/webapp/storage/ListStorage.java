package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> resumeList = new ArrayList<>();

    @Override
    protected Object getResumeIdx(String uuid) {
        for(int i = 0; i<resumeList.size(); i++){
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object resumeIdx) {
        return resumeIdx != null;
    }

    @Override
    protected void makeUpdate(Resume r, Object resumeIdx) {
        resumeList.set((Integer) resumeIdx, r);
    }

    @Override
    protected void makeSave(Resume r, Object resumeIdx) {
        resumeList.add(r);
    }

    @Override
    protected Resume makeTake(Object resumeIdx) {
        return resumeList.get((Integer) resumeIdx);
    }

    @Override
    protected void makeDelete(Object resumeIdx) {
        resumeList.remove(((Integer) resumeIdx).intValue());
    }

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[resumeList.size()]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
