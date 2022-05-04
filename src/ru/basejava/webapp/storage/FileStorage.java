package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private StreamSerializerInterface ssi;

    protected FileStorage(File directory, StreamSerializerInterface ssi) {
        Objects.requireNonNull(directory, "Directory can't be null!");
        this.ssi = ssi;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory!");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " has no r/w access!");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
            for (File file : getFilesList()) {
                makeDelete(file);
            }
    }

    @Override
    public int size() {
        return getFilesList().length;
    }

    @Override
    protected File getResume(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void makeUpdate(Resume r, File file) {
        try {
            ssi.makeWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void makeSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        makeUpdate(r, file);
    }

    @Override
    protected Resume makeTake(File file) {
        try {
            return ssi.makeRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void makeDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> getResumeList() {
        List<Resume> list = new ArrayList<>(getFilesList().length);
        for (File file : getFilesList()) {
            list.add(makeTake(file));
        }
        return list;
    }

    private File[] getFilesList() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error");
        }
        return files;
    }

}
