package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

interface StreamSerializerInterface {
    Resume makeRead(InputStream is) throws IOException;

    void makeWrite(Resume r, OutputStream os) throws IOException;
}
