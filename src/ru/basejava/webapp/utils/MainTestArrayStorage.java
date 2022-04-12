package ru.basejava.webapp.utils;

import ru.basejava.webapp.model.Resume;
import ru.basejava.webapp.storage.ArrayStorage;
import ru.basejava.webapp.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("name one");
        //r1.setUuid("uuid1");
        Resume r2 = new Resume("name two");
        //r2.setUuid("uuid2");
        Resume r3 = new Resume("name three");
        //r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        //r1.setUuid("upd-uuid");
        //ARRAY_STORAGE.update(r1);
        //System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
