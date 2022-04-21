package ru.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private final String title;
    private final String description;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final String url;

    public Organization(String title, String description, LocalDate dateFrom, LocalDate dateTo, String url) {
        Objects.requireNonNull(dateFrom, "Start date can't be null!");
        Objects.requireNonNull(dateTo, "End date can't be null!");
        Objects.requireNonNull(title, "Title can't be null!");
        this.title = title;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!title.equals(that.title)) return false;
        if (!dateFrom.equals(that.dateFrom)) return false;
        return dateTo.equals(that.dateTo);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + dateFrom.hashCode();
        result = 31 * result + dateTo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", url='" + url + '\'' +
                '}';
    }
}
