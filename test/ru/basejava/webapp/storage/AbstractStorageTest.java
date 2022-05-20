package ru.basejava.webapp.storage;

import org.junit.Before;
import org.junit.Test;
import ru.basejava.webapp.Config;
import ru.basejava.webapp.exception.ExistStorageException;
import ru.basejava.webapp.exception.NotExistStorageException;
import ru.basejava.webapp.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    /* pre-generated uuids
cd25c87b-53b1-4a89-b46d-33667b921c38
dd1c86e5-d1d9-48d1-86b9-64791f8bc3ad
1146226d-517a-4dc1-b708-7eac3eaedb34
a19e1e34-0413-4ae7-b6ab-51cf6a7eeb8c
fcec9d32-ae13-414b-b2bc-dd7a38807c5c
     */

    private static final String UUID_1 = "cd25c87b-53b1-4a89-b46d-33667b921c38";
    private static final String UUID_2 = "dd1c86e5-d1d9-48d1-86b9-64791f8bc3ad";
    private static final String UUID_3 = "1146226d-517a-4dc1-b708-7eac3eaedb34";
    private static final String UUID_4 = "a19e1e34-0413-4ae7-b6ab-51cf6a7eeb8c";

    private static final Resume R1 = ResumeTestData.fillResume(UUID_1, "John Doe");
    private static final Resume R2 = ResumeTestData.fillResume(UUID_2, "James Bond");
    private static final Resume R3 = ResumeTestData.fillResume(UUID_3, "Jimmy Hendrix");
    private static final Resume R4 = ResumeTestData.fillResume(UUID_4, "Jack Sparrow");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume newR = new Resume(UUID_2, "Newbie");
        storage.update(newR);
        assertTrue(newR.equals(storage.get(UUID_2)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistingItem() throws Exception {
        storage.update(R4);
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        assertEquals(R4, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingItem() throws Exception {
        storage.save(R1);
    }

    @Test
    public void get() throws Exception {
        assertEquals(R1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistingItem() throws Exception {
        storage.get(UUID_4);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(Arrays.asList(R2, R3, R1), list);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistingItem() throws Exception {
        storage.delete(UUID_4);
    }
}