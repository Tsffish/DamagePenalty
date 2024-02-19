package github.tsffish.damagepenalty.config.lang;

import github.tsffish.damagepenalty.DamagePenalty;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static github.tsffish.damagepenalty.config.misc.ErrorConfigHandler.er;
import static github.tsffish.damagepenalty.util.misc.MessSender.l;

public class LangConfigLoad{
    private static final String name = "LangConfigLoad";
    private static final String reason = "vaule is null";
    private static final Plugin plugin = JavaPlugin.getPlugin(DamagePenalty.class);
    public static YamlConfiguration config;

    public static void loadLangConfig(){

        File langFile = new File(plugin.getDataFolder(), "lang.yml");
        if (!langFile.exists()) {
            plugin.saveResource("lang.yml", false);
        }

        FileConfiguration c = YamlConfiguration.loadConfiguration(langFile);

        if (c.getStringList(LangConfigPath.path_command_help) != null) {
            LangConfigHandler.command_help = c.getStringList(LangConfigPath.path_command_help);
        } else {
            sendError(LangConfigPath.path_command_help);
        }





        l("<LangConfigLoad> Finish Load Config");


    }
    private static void sendError(String path){
        er(name, path, reason);
    }
}