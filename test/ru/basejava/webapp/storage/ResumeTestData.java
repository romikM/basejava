package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.*;
import ru.basejava.webapp.utils.DateUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static Resume fillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        //System.out.println(resume);

        resume.contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        resume.contacts.put(ContactType.SKYPE, "grigory.kislin");
        resume.contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        resume.contacts.put(ContactType.WEBPAGE, "http://gkislin.ru/");

        //System.out.println(resume.contacts);

        resume.sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achi = new ArrayList<>();
        achi.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achi.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников. ");
        achi.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achi.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achi.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achi.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achi.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        resume.sections.put(SectionType.ACHIEVEMENT, new ListSection(achi));

        List<String> qual = new ArrayList<>();

        qual.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qual.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qual.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qual.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qual.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qual.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qual.add("Python: Django.");
        qual.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qual.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qual.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qual.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qual.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        qual.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qual.add("Родной русский, английский \"upper intermediate\"");

        resume.sections.put(SectionType.QUALIFICATION, new ListSection(qual));

        Organization org1 = new Organization(
                "Java Online Projects",
                "http://javaops.ru/",
                new Organization.CareerStage(
                        DateUtil.of(2013, Month.of(10)),
                        DateUtil.of(2015, Month.of(4)),
                        "Автор проекта, \n Создание, организация и проведение Java онлайн проектов и стажировок"
                ),
                new Organization.CareerStage(
                        DateUtil.of(2017, Month.of(6)),
                        DateUtil.of(2022, Month.of(11)),
                        "Автор проекта, \n Создание, организация и проведение Java онлайн проектов и стажировок"
                )
        );
        Organization org2 = new Organization(
                "Wrike",
                "https://www.wrike.com/",
                new Organization.CareerStage(
                        DateUtil.of(2014, Month.of(10)),
                        DateUtil.of(2016, Month.of(1)),
                        "Старший разработчик (backend) \n Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
                )

        );
        Organization org3 = new Organization(
                "RIT Center",
                "",
                new Organization.CareerStage(
                        DateUtil.of(2012, Month.of(4)),
                        DateUtil.of(2014, Month.of(10)),
                        "Java архитектор \n Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"
                )
        );

        List<Organization> jobs = new ArrayList<>();

        jobs.add(org1);
        jobs.add(org2);
        jobs.add(org3);

        Organization edu1 = new Organization(
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.imfo.ru",
                new Organization.CareerStage(
                        DateUtil.of(1993, Month.of(9)),
                        DateUtil.of(1996, Month.of(7)),
                        "Аспирантура (программист С, С++)"),
                new Organization.CareerStage(
                        DateUtil.of(1987, Month.of(9)),
                        DateUtil.of(1993, Month.of(7)),
                        "Инженер (программист Fortran, C)"
                )
        );

        Organization edu2 = new Organization(
                "Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru",
                new Organization.CareerStage(
                        DateUtil.of(1984, Month.of(9)),
                        DateUtil.of(1987, Month.of(6)),
                        "Закончил с отличием"
                )

        );

        List<Organization> educ = new ArrayList<>();

        educ.add(edu1);
        educ.add(edu2);

        resume.sections.put(SectionType.EXPERIENCE, new OrganizationSection(jobs));
        resume.sections.put(SectionType.EDUCATION, new OrganizationSection(educ));

        //System.out.println(resume.sections);

        return resume;
    }

    public static void main(String[] args) {
        Resume testResume = ResumeTestData.fillResume("007", "James Bond");
    }
}
