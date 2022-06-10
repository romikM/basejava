package ru.basejava.webapp.utils;

import ru.basejava.webapp.model.Organization;

public class HtmlUtil {
    public static boolean strIsEmpty(String s) {
        return s.trim().length() == 0 || s == null;
    }

    public static String formatDates(Organization.CareerStage stage) {
        return "c " + DateUtil.format(stage.getdateFrom()) + " по " + DateUtil.format(stage.getdateTo());
    }

}