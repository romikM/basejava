package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    //Resume[] getAll();

    List<Resume> getAllSorted();

    int size();
}
