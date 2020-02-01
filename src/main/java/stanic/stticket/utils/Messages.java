package stanic.stticket.utils;

import org.bukkit.ChatColor;
import stanic.stticket.Main;

public class Messages {

    public String get(String message) {
        return ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("Messages." + message).replace("@n", "\n"));
    }

}