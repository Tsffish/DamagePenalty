package github.tsffish.damagepenalty;

import github.tsffish.damagepenalty.api.PlayerMultiplierImpl;
import github.tsffish.damagepenalty.command.CommandInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

import static github.tsffish.damagepenalty.config.main.MainConfigHandler.minusMultiplier_onPerSecond;
import static github.tsffish.damagepenalty.config.main.MainConfigLoad.loadMainConfig;
import static github.tsffish.damagepenalty.util.misc.MessSender.l;
import static github.tsffish.damagepenalty.util.misc.bstats.StartMetrics.startMetrics;


public class DamagePenalty extends JavaPlugin {
    private static final String pluginName = "DamagePenalty";
    public static String pluginName(){
        return pluginName;
    }
    private static final String pluginVersion = "1.0.2";
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
    private static final int spigotId = 115162;
    public static int spigotId(){
        return spigotId;
    }
    private static final int bstatId = 21055;
    public static int bstatId(){
        return bstatId;
    }
    private static DamagePenalty instance;
    public static DamagePenalty getInstance(){
        return instance;
    }
    public static boolean isBungeeMode;
    public static boolean isBungeeMode() {
        return isBungeeMode;
    }
    public static String language;
    public static String language() {
        return language;
    }
    private static boolean isLastestVersion;
    public static boolean getIsLastestVersion(){
        return isLastestVersion;
    }
    public static void setIsLastestVersion(boolean setTo){
        isLastestVersion = setTo;
    }
    private final PlayerMultiplierImpl playerMultipliers = new PlayerMultiplierImpl();
    @Override
    public void onEnable() {
        instance = this;

        ServicesManager servicesManager = Bukkit.getServicesManager();
        sendPluginStartUpInfo();

        PlayerMultiplierImpl playerMultiplierImpl = new PlayerMultiplierImpl();

        servicesManager.register(PlayerMultiplierImpl.class, playerMultiplierImpl, this, ServicePriority.Normal);

        loadMainConfig(null, true);

        startMetrics();

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (UUID uuid : playerMultipliers.getDamageMultiplierMap().keySet()) {
                double multiplier = playerMultipliers.getDamageMultiplier(uuid);

                if (multiplier < 1.0) {
                    return;
                }

                if (multiplier > 1.0) {
                    playerMultipliers.setDamageMultiplier(uuid, multiplier - minusMultiplier_onPerSecond);
                }
            }
        }, 20L, 20L);

        getCommand("dpe").setExecutor(new CommandInfo());
    }
    private void sendPluginStartUpInfo(){
        l(green + msgline);
        l(" ");
        l(white + pluginName + " " + aqua + pluginVersion);
        l(" ");
        l(white + "Author: " + yellow  + author);
        l(" ");
        l(green + msgline);
    }
    public void onDisable() {
        l("Disabled.");
    }
    private static final ChatColor green = ChatColor.GREEN;
    private static final ChatColor white = ChatColor.WHITE;
    private static final ChatColor aqua = ChatColor.AQUA;
    private static final ChatColor yellow = ChatColor.YELLOW;
}
