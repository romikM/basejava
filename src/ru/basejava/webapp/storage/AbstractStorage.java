package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract boolean isExist(SK key);

    protected abstract void makeUpdate(Resume r, SK key);

    protected abstract void makeSave(Resume r, SK key);

    protected abstract Resume makeTake(SK key);

    protected abstract void makeDelete(SK key);

    protected abstract SK getResume(String uuid);

    protected abstract List<Resume> getResumeList();

    public void update(Resume r) {
        LOG.info("Update: " + r);
        SK key = getExistingResumeKey(r.getUuid());
        makeUpdate(r, key);
    }

    public void save(Resume r) {
        LOG.info("Save: " + r);
        SK key = getNotExistingResumeKey(r.getUuid());
        makeSave(r, key);
    }

    public void delete(String uuid) {
        LOG.info("Delete: " + uuid);
        SK key = getExistingResumeKey(uuid);
        makeDelete(key);
    }

    public Resume get(String uuid) {
        LOG.info("Get: " + uuid);
        SK key = getExistingResumeKey(uuid);
        return makeTake(key);
    }

    private SK getExistingResumeKey(String uuid) {
        SK key = getResume(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume with uuid=" + uuid + " doesn't exist.");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private SK getNotExistingResumeKey(String uuid) {
        SK key = getResume(uuid);
        if (isExist(key)) {
            LOG.warning("Resume with uuid=" + uuid + " already exist.");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted call.");
        List<Resume> list = getResumeList();
        Collections.sort(list);
        return list;
    }

}
