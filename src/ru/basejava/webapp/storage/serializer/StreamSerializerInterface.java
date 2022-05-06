package ru.basejava.webapp.storage.serializer;

import ru.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializerInterface {
    Resume makeRead(InputStream is) throws IOException;

    void makeWrite(Resume r, OutputStream os) throws IOException;
}
