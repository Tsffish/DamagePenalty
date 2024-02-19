package github.tsffish.damagepenalty.config.misc;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

import static github.tsffish.damagepenalty.DamagePenalty.pluginNameConsole;

public class ErrorConfigHandler {
    private static final String mess = "An error occurred while attempting to load -> ";
    private static final Logger l = Bukkit.getLogger();
    public static void er(String name, String path ,String exception){
        l.warning(pluginNameConsole() + " <" + name + "> " + mess + path + " : " + exception);
    }
    public static void er(String name, String path ,Exception exception){
        l.warning(pluginNameConsole() + " <" + name + "> " + mess + path + " : " + exception);
    }
}
