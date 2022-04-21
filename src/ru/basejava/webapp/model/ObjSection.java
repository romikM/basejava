package ru.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ObjSection extends AbstractSection {
    private final List<Organization> organizations;

    public ObjSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "Empty organizations not allowed!");
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjSection that = (ObjSection) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
