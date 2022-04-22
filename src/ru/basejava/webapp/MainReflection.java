package ru.basejava.webapp;

import ru.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("newone");

        Class<? extends Resume> resumeClass;
        resumeClass = r.getClass();
        Field field = resumeClass.getDeclaredFields()[0];

        // give access to private & final uuid-field
        field.setAccessible(true);

        System.out.println("Resume field[0] is: " + field.getName());
        System.out.println("Resume field[0] value is: " + field.get(r));

        // set new value to Resume.field[0] (or uuid field, as the only field in Resume now)
        field.set(r, "new_uuid");

        Method method = resumeClass.getMethod("toString");
        System.out.println("Resume.toString() now returns new uuid: " + method.invoke(r));
    }
}
