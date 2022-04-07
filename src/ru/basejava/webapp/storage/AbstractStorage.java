package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected abstract boolean isExist (Object resumeIdx);
    protected abstract void makeUpdate (Resume r, Object resumeIdx);
    protected abstract void makeSave (Resume r, Object resumeIdx);
    protected abstract Resume makeTake (Object resumeIdx);
    protected abstract void makeDelete(Object resumeIdx);
    protected abstract Object getResumeIdx(String uuid);
    protected abstract List<Resume> makeStorageCopy();

    @Override
    public List<Resume> getAll() {
        List<Resume> arr = makeStorageCopy();
        Collections.sort(arr);
        return arr;
    }

    public void update(Resume r) {
        Object resumeIdx = getExistingResumeKey(r.getUuid());
        makeUpdate(r, resumeIdx);
    }

    public void save(Resume r) {
        Object resumeIdx = getNotExistingResumeKey(r.getUuid());
        makeSave(r, resumeIdx);
    }

    public void delete(String uuid) {
        Object resumeIdx = getExistingResumeKey(uuid);
        makeDelete(resumeIdx);
    }

    public Resume get(String uuid) {
        Object resumeIdx = getExistingResumeKey(uuid);
        return makeTake(resumeIdx);
    }

    private Object getExistingResumeKey (String uuid) {
        Object resumeIdx = getResumeIdx(uuid);
        if (!isExist(resumeIdx)) {
            throw new NotExistStorageException(uuid);
        }
        return resumeIdx;
    }

    private Object getNotExistingResumeKey (String uuid) {
        Object resumeIdx = getResumeIdx(uuid);
        if (isExist(resumeIdx)) {
            throw new ExistStorageException(uuid);
        }
        return resumeIdx;
    }

}
