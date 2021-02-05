package fr.grabuges.api.utils;

public class ThreadUtils {

    public static void start(Runnable runnable) {
        new Thread(runnable).start();
    }

}
