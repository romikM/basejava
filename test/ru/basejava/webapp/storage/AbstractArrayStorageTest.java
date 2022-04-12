package ru.basejava.webapp.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void checkStorageOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 1; i <= AbstractArrayStorage.STORAGE_MAX_SIZE; i++) {
                storage.save(new Resume(i + ". FullName"));
            }
        } catch (StorageException e) {
            Assert.fail("Storage overflowed before maximum size reached.");
        }
        storage.save(new Resume("oversize"));
    }
}
