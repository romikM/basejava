package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

public class ResumeTestData {

    public static void main(String[] args) {

        Resume resume = new Resume("007", "Luke A Skywalker");
        System.out.println("Basic resume: " + resume);
        System.out.println("--------------------------------------------------");

    }
}
