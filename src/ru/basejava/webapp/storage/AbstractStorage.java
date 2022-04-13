package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected abstract boolean isExist(Object key);

    protected abstract void makeUpdate(Resume r, Object key);

    protected abstract void makeSave(Resume r, Object key);

    protected abstract Resume makeTake(Object key);

    protected abstract void makeDelete(Object key);

    protected abstract Object getResume(String uuid);

    protected abstract List<Resume> getResumeList();

    public void update(Resume r) {
        Object key = getExistingResumeKey(r.getUuid());
        makeUpdate(r, key);
    }

    public void save(Resume r) {
        Object key = getNotExistingResumeKey(r.getUuid());
        makeSave(r, key);
    }

    public void delete(String uuid) {
        Object key = getExistingResumeKey(uuid);
        makeDelete(key);
    }

    public Resume get(String uuid) {
        Object key = getExistingResumeKey(uuid);
        return makeTake(key);
    }

    private Object getExistingResumeKey(String uuid) {
        Object key = getResume(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getNotExistingResumeKey(String uuid) {
        Object key = getResume(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getResumeList();
        Collections.sort(list);
        return list;
    }

}
