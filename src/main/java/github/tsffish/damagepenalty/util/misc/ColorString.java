package github.tsffish.damagepenalty.util.misc;

import org.bukkit.ChatColor;

public class ColorString {

    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}