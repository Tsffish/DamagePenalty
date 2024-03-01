package github.tsffish.damagepenalty.util.misc;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.util.logging.Logger;

import static github.tsffish.damagepenalty.DamagePenalty.pluginNameConsole;
import static github.tsffish.damagepenalty.util.misc.ColorString.t;

public class MessSender {
    private static final ConsoleCommandSender console = Bukkit.getConsoleSender();
    private static final Logger l = Bukkit.getLogger();

    public static void l(String string){
        console.sendMessage(t(pluginNameConsole() + " " + string));
    }
    public static void le(String name,String casue){
        l.warning(t(pluginNameConsole() + " " + name + " " + casue));
    }
    public static void le(String name,Exception casue){
        l.warning(t(pluginNameConsole() + " " + name + " " + casue));
    }
}
