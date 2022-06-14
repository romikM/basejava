package ru.basejava.webapp.model;

import ru.basejava.webapp.utils.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.basejava.webapp.utils.DateUtil.NOW;
import static ru.basejava.webapp.utils.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Organization EMPTY = new Organization("", "", CareerStage.EMPTY);
    private String title;
    private String url;

    private List<CareerStage> stages = new ArrayList<>();

    public Organization() {
    }

    public Organization(String title, String url, CareerStage... stages) {
        this(title, url, Arrays.asList(stages));
    }

    public Organization(String title, String url, List<CareerStage> stages) {
        this.title = title;
        this.url = url;
        this.stages = stages;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public List<CareerStage> getStages() {
        return stages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!title.equals(that.title)) return false;
        if (!Objects.equals(url, that.url)) return false;
        return stages.equals(that.stages);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + stages.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", stages=" + stages +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CareerStage implements Serializable {
        public static final CareerStage EMPTY = new CareerStage();
        private String description;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateFrom;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateTo;

        public CareerStage() {
        }

        public CareerStage(int yearFrom, Month monthFrom, String description) {
            this(of(yearFrom, monthFrom), NOW, description);
        }

        public CareerStage(int startYear, Month startMonth, int endYear, Month endMonth, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), description);
        }

        public CareerStage(LocalDate dateFrom, LocalDate dateTo, String description) {
            Objects.requireNonNull(dateFrom, "DateFrom can't be null");
            Objects.requireNonNull(dateTo, "DateTo can't be null");
            Objects.requireNonNull(description, "Description can't be null");
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.description = description;
        }

        public LocalDate getdateFrom() {
            return dateFrom;
        }

        public LocalDate getdateTo() {
            return dateTo;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CareerStage that = (CareerStage) o;

            if (!description.equals(that.description)) return false;
            if (!dateFrom.equals(that.dateFrom)) return false;
            return dateTo.equals(that.dateTo);
        }

        @Override
        public int hashCode() {
            int result = description.hashCode();
            result = 31 * result + dateFrom.hashCode();
            result = 31 * result + dateTo.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "CareerStage{" +
                    "description='" + description + '\'' +
                    ", dateFrom=" + dateFrom +
                    ", dateTo=" + dateTo +
                    '}';
        }
    }
}
