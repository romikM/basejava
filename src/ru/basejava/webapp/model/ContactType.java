package ru.basejava.webapp.model;

public enum ContactType {
    PHONE("Контактный телефон"),
    EMAIL("Контактный email"),
    SKYPE("Скайп"),
    WEBPAGE("Персональная страница"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    TELEGRAM("Телеграм");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
