package ru.basejava.webapp.storage.serializer;

import ru.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializerInterface {

    @Override
    public void makeWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {

            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = r.sections;
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
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
                        List<String> qual = ((ListSection) section).getContent();
                        dos.writeInt(qual.size());
                        for (String tmp : qual){
                            dos.writeUTF(tmp);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Organization org : organizations) {
                            dos.writeUTF(org.getTitle());
                            dos.writeUTF(org.getUrl());
                            List<Organization.CareerStage> stages = org.getStages();
                            dos.writeInt(stages.size());
                            for (Organization.CareerStage stage : stages) {
                                dos.writeUTF(stage.getDescription());
                                writeLocalDate(dos, stage.getdateFrom());
                                writeLocalDate(dos, stage.getdateTo());
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume makeRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSec = dis.readInt();
            for (int i = 0; i < sizeSec; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        int sizeList = dis.readInt();
                        List<String> listItems = new ArrayList<>();
                        for (int j = 0; j < sizeList; j++) {
                            listItems.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(listItems));
                    case EXPERIENCE:
                    case EDUCATION:
                        int sizeOrg = dis.readInt();
                        List<Organization> orgs = new ArrayList<>();
                        for (int k = 0; k < sizeOrg; k++) {
                            String title = dis.readUTF();
                            String url = dis.readUTF();
                            int sizeStage = dis.readInt();
                            List<Organization.CareerStage> stages = new ArrayList<>();
                            for (int y = 0; y < sizeStage; y++) {
                                stages.set(y, new Organization.CareerStage(readLocalDate(dis), readLocalDate(dis), dis.readUTF()));
                            }
                            orgs.set(k, new Organization(title, url, stages));
                        }
                        resume.addSection(sectionType, new OrganizationSection(orgs));
                    default:
                        throw new IllegalStateException();
                }
            }
            return resume;
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
}
