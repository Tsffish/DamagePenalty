package github.tsffish.damagepenalty;

import github.tsffish.damagepenalty.command.CommandInfo;
import github.tsffish.damagepenalty.listener.PlayerDamageHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

import static github.tsffish.damagepenalty.config.main.MainConfigLoad.loadMainConfig;
import static github.tsffish.damagepenalty.util.DamageMultipliers.damageMultipliers;
import static github.tsffish.damagepenalty.util.misc.MessSender.l;


public class DamagePenalty extends JavaPlugin {
    private static final String pluginName = "DamagePenalty";
    public static String pluginName(){
        return pluginName;
    }
    private static final String pluginVersion = "1.0";
    public static String pluginVersion(){
        return pluginVersion;
    }
    private static final String pluginNameConsole = "[" + pluginName + "]";
    public static String pluginNameConsole(){
        return pluginNameConsole;
    }
    private static final String author = "Tsffish";
    public static String author(){
        return author;
    }
    public static final String msgline = "================================";
    private static boolean isDebug = false;
    public static boolean isDebug(){
        return isDebug;
    }
    public static void changeIsDebug(){
        isDebug = !isDebug;
    }
    private static final int bstatId = 21055;
    static int bstatId(){
        return bstatId;
    }
    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        if (pluginManager.getPlugin("BedwarsKit") != null){
            l("Enabled.");
        }else {
            sendPluginStartUpInfo(ChatColor.GREEN);
        }

        loadMainConfig(null, true);
        pluginManager.registerEvents(new PlayerDamageHandler(), this);
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (UUID uuid : damageMultipliers.keySet()) {
                double multiplier = damageMultipliers.get(uuid);

                if (multiplier > 1.0) {
                    damageMultipliers.put(uuid, Math.max(multiplier - 0.001, 1.0));
                }
            }
        }, 20L, 20L);

        getCommand("dpe").setExecutor(new CommandInfo());
    }
    private void sendPluginStartUpInfo(ChatColor color){
        l(color + msgline);
        l(" ");
        l(ChatColor.WHITE + pluginName + " " + ChatColor.AQUA + pluginVersion);
        l(" ");
        l(ChatColor.WHITE + "Author: " + ChatColor.YELLOW  + author);
        l(" ");
        l(color + msgline);
    }
    public void onDisable() {
        l("Disabled.");
    }
}
