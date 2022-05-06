package ru.basejava.webapp.storage;

import ru.basejava.webapp.exception.StorageException;
import ru.basejava.webapp.model.Resume;
import ru.basejava.webapp.storage.serializer.StreamSerializerInterface;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private StreamSerializerInterface ssi;

    protected PathStorage(String dir, StreamSerializerInterface ssi) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "Directory can't be null!");
        this.ssi = ssi;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " has no r/w access!");
        }
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::makeDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    protected Path getResume(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void makeUpdate(Resume r, Path path) {
        try {
            ssi.makeWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void makeSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " + path, path.getFileName().toString(), e);
        }
        makeUpdate(r, path);
    }

    @Override
    protected Resume makeTake(Path path) {
        try {
            return ssi.makeRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File read error", getFileName(path), e);
        }
    }

    @Override
    protected void makeDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File delete error", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> getResumeList() {
        return getFilesList().map(this::makeTake).collect(Collectors.toList());
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Dir read error", e);
        }
    }
}
