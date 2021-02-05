package fr.grabuges.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static void broadcast(String text, Object... objects) {
        Bukkit.broadcastMessage(format(text, objects));
    }

    public static void sendMessage(Player player, String text, Object... objects) {
        player.sendMessage(format(text, objects));
    }

    public static String format(String txt, Object... objects) {
        return ChatColor.translateAlternateColorCodes('&', String.format(txt, objects));
    }

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
