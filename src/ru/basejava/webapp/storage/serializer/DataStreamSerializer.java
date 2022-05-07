package ru.basejava.webapp.storage.serializer;

import ru.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements StreamSerializerInterface {

    @Override
    public void makeWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            writeItems(dos, r.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeItems(dos, r.sections.entrySet(), entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        writeItems(dos, ((ListSection) section).getContent(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                        writeItems(dos, organizations, orgentry -> {
                            dos.writeUTF(orgentry.getTitle());
                            dos.writeUTF(orgentry.getUrl());
                            writeItems(dos, orgentry.getStages(), stageentry -> {
                                writeLocalDate(dos, stageentry.getdateFrom());
                                writeLocalDate(dos, stageentry.getdateTo());
                                dos.writeUTF(stageentry.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume makeRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, makeSection(dis, sectionType));
            });
            return resume;
        }
    }

    private AbstractSection makeSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATION:
                return new ListSection(createList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationSection(
                        createList(dis, () -> new Organization(
                                dis.readUTF(),
                                dis.readUTF(),
                                createList(dis, () -> new Organization.CareerStage(
                                   readLocalDate(dis), readLocalDate(dis), dis.readUTF()
                                ))
                        ))
                );
            default:
                throw new IllegalStateException();
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private interface theWriter<T> {
        void write(T t) throws IOException;
    }

    private interface theReader {
        void read() throws IOException;
    }

    private interface listItemReader<T> {
        T readItem() throws IOException;
    }

    private <T> void writeItems(DataOutputStream dos, Collection<T> items, theWriter<T> writer) throws IOException {
        dos.writeInt(items.size());
        for (T item : items) {
            writer.write(item);
        }
    }

    private <T> List<T> createList(DataInputStream dis, listItemReader<T> ireader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(ireader.readItem());
        }
        return list;
    }

    private void readItems(DataInputStream dis, theReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}
