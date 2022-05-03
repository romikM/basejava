package ru.basejava.webapp.storage;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    public ObjectStreamStorageTest() {
        super((Storage) new ObjectStreamSerializer());
    }
}
