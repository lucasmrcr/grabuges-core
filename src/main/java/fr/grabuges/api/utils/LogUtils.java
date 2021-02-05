package fr.grabuges.api.utils;

import org.bukkit.Bukkit;

public class LogUtils {

    public static void warn(String text, Object... objects) {
        log("%s%s", "&e&l┃ &eATTENTION &r", String.format(text, objects));
    }

    public static void err(String text, Object... objects) {
        log("%s%s", "&c&l┃ &cERREUR &r", String.format(text, objects));
    }

    public static void info(String text, Object... objects) {
        log("%s%s", "&3&l┃ &3INFO &r", String.format(text, objects));
    }

    private static void log(String text, Object... objects) {
        Bukkit.getConsoleSender().sendMessage(ChatUtils.format(
                text,
                objects
        ));
    }
}
