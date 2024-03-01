package github.tsffish.damagepenalty.config.main;

import github.tsffish.damagepenalty.DamagePenalty;
import github.tsffish.damagepenalty.listener.PlayerDamageHandler;
import github.tsffish.damagepenalty.listener.PlayerEatHandler;
import github.tsffish.damagepenalty.listener.PlayerJoinHandler;
import github.tsffish.damagepenalty.listener.PlayerQuitHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

import static github.tsffish.damagepenalty.DamagePenalty.pluginName;
import static github.tsffish.damagepenalty.DamagePenalty.spigotId;
import static github.tsffish.damagepenalty.config.lang.LangConfigLoad.loadLangConfig;
import static github.tsffish.damagepenalty.config.main.MainConfigHandler.*;
import static github.tsffish.damagepenalty.config.misc.ErrorConfigHandler.er;
import static github.tsffish.damagepenalty.util.misc.ColorString.t;
import static github.tsffish.damagepenalty.util.misc.MessSender.l;
import static github.tsffish.damagepenalty.util.misc.update.StartCheck.checkUpdate;

public class MainConfigLoad{
    private static final String name = "MainConfigLoad";
    private static final String reason = "vaule is null";
    private static final DamagePenalty plugin = DamagePenalty.getInstance();
    public static void loadMainConfig(CommandSender executer, boolean firstload) {

        plugin.saveDefaultConfig();

        c = plugin.getConfig();

        plugin.reloadConfig();



        if (!firstload) {
            if (executer != null) {
                executer.sendMessage(t(messreloadnow));
            }
        }

        if (firstload) {
            if (c.getString(MainConfigPath.path_update_checker) != null) {
                boolean update_checker = c.getBoolean(MainConfigPath.path_update_checker);
                if (update_checker) {
                    checkUpdate(spigotId());
                }
            } else {
                sendError(MainConfigPath.path_update_checker);
                checkUpdate(spigotId());
            }
        }

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

        if (c.getString(MainConfigPath.path_addMultiplier_onPerDamage) != null) {
            addMultiplier_onPerDamage = c.getDouble(MainConfigPath.path_addMultiplier_onPerDamage);
        } else {
            sendError(MainConfigPath.path_addMultiplier_onPerDamage);
        }

        if (c.getString(MainConfigPath.path_minusMultiplier_onPerSecond) != null) {
            minusMultiplier_onPerSecond = c.getDouble(MainConfigPath.path_minusMultiplier_onPerSecond);
        } else {
            sendError(MainConfigPath.path_minusMultiplier_onPerSecond);
        }

        if (c.getString(MainConfigPath.path_setMultiplier_onDeath) != null) {
            setMultiplier_onDeath = c.getDouble(MainConfigPath.path_setMultiplier_onDeath);
        } else {
            sendError(MainConfigPath.path_setMultiplier_onDeath);
        }

        if (c.getString(MainConfigPath.paht_noGoldAppleExtraHeart) != null) {
            noGoldAppleExtraHeart = c.getDouble(MainConfigPath.paht_noGoldAppleExtraHeart);
        } else {
            sendError(MainConfigPath.paht_noGoldAppleExtraHeart);
        }

        if (update_tip == null){
            update_tip = new ArrayList<>(9);
        }else {
            update_tip.clear();
        }
        update_tip.add("&a ================================");
        update_tip.add("&f ");
        update_tip.add("&f " + pluginName() + " &bFound a new version!");
        update_tip.add("&f ");
        update_tip.add("&f You can download here: https://www.spigotmc.org/resources/damage-penalty.115162");
        update_tip.add("&f If you don't want to check for updates, please turn off the update_checker in config");
        update_tip.add("&f ");
        update_tip.add("&a ================================");
        loadLangConfig();

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerDamageHandler(),plugin);
        pluginManager.registerEvents(new PlayerEatHandler(),plugin);
        pluginManager.registerEvents(new PlayerJoinHandler(),plugin);
        pluginManager.registerEvents(new PlayerQuitHandler(),plugin);

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