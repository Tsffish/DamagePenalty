package github.tsffish.damagepenalty.config.main;

import github.tsffish.damagepenalty.DamagePenalty;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static github.tsffish.damagepenalty.DamagePenalty.pluginName;
import static github.tsffish.damagepenalty.config.lang.LangConfigLoad.loadLangConfig;
import static github.tsffish.damagepenalty.config.misc.ErrorConfigHandler.er;
import static github.tsffish.damagepenalty.util.misc.ColorString.t;
import static github.tsffish.damagepenalty.config.main.MainConfigHandler.*;
import static github.tsffish.damagepenalty.util.misc.MessSender.l;

public class MainConfigLoad{
    private static final String name = "MainConfigLoad";
    private static final String reason = "vaule is null";
    private static final Plugin plugin = JavaPlugin.getPlugin(DamagePenalty.class);
    public static void loadMainConfig(CommandSender executer, boolean firstload) {

        plugin.saveDefaultConfig();

        c = plugin.getConfig();

        plugin.reloadConfig();

        loadLangConfig();

        if (c.getString(MainConfigPath.path_messreloadnow) != null) {
            messreloadnow = c.getString(MainConfigPath.path_messreloadnow);
        } else {
            messreloadnow = t("&b" + pluginName() + " &7>> &eReloading configuration file");
            sendError(MainConfigPath.path_messreloadnow);
        }

        if (c.getString(MainConfigPath.path_messreloadsucc) != null) {
            messreloadsucc = c.getString(MainConfigPath.path_messreloadsucc);
        } else {
            messreloadsucc = t("&b" + pluginName() + " &7>> &aSuccessfully reloaded configuration file");
            sendError(MainConfigPath.path_messreloadsucc);
        }

        if (!firstload) {
            if (executer != null) {
                executer.sendMessage(t(messreloadnow));
            }
        }

        l("<MainConfigLoad> Finish Load Config");

        if (!firstload) {
            if (executer != null) {
                executer.sendMessage(t(messreloadsucc));
            }
        }
    }
    private static void sendError(String path){
        er(name, path, reason);
    }
}