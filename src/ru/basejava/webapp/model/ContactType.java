package ru.basejava.webapp.model;

public enum ContactType {
    PHONE("Контактный телефон"),
    EMAIL("Контактный email"){
        @Override
        public String preHtml(String value) {
            return getTitle() + ": " + toHref("mailto:" + value, value);
        }
    },
    SKYPE("Скайп") {
        @Override
        public String preHtml(String value) {
            return getTitle() + ": " + toHref("skype:" + value, value);
        }
    },
    WEBPAGE("Персональная страница"){
        @Override
        public String preHtml(String value) {
            return toHref(value);
        }
    },
    LINKEDIN("Профиль LinkedIn"){
        @Override
        public String preHtml(String value) {
            return toHref(value);
        }
    },
    GITHUB("Профиль GitHub"){
        @Override
        public String preHtml(String value) {
            return toHref(value);
        }
    },
    TELEGRAM("Телеграм");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String preHtml(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : preHtml(value);
    }

    public String toHref(String href) {
        return toHref(href, title);
    }

    public static String toHref(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }
}
