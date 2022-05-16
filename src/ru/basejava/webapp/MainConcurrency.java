package ru.basejava.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                //throw new IllegalStateException();
            }
        };
        thread0.start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);

        final Object objA = new Object();
        final Object objB = new Object();
        initDeadLock(objA,objB);
        initDeadLock(objB,objA);

    }

    private synchronized void inc() {
        counter++;
    }

    private static void initDeadLock(Object objA, Object objB) {
        new Thread(() -> {
            synchronized (objA) {
                System.out.println("Locked on object: " + objA);
                try {
                    // do something
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Prepare to lock object: " + objB);
                synchronized (objB) {
                    // do something
                    System.out.println("Locked on object: " + objB);
                }
            }
        }).start();
    }
}