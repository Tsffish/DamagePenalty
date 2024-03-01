package github.tsffish.damagepenalty.util.misc.bstats;

import github.tsffish.damagepenalty.DamagePenalty;
import org.bstats.bukkit.Metrics;

import static github.tsffish.damagepenalty.DamagePenalty.bstatId;
import static github.tsffish.damagepenalty.DamagePenalty.isBungeeMode;
import static github.tsffish.damagepenalty.util.misc.MessSender.le;

public class StartMetrics {
    private static final DamagePenalty plugin = DamagePenalty.getInstance();
    private static final String className = "StartMetrics";
    public static void startMetrics(){
        try {
            Metrics metrics = new Metrics(plugin, bstatId());

            metrics.addCustomChart(new Metrics.SimplePie("server_version", () -> plugin.getServer().getVersion()));
            metrics.addCustomChart(new Metrics.SimplePie("server_language", DamagePenalty::language));
            metrics.addCustomChart(new Metrics.SimplePie("server_auth_mode", () -> String.valueOf(plugin.getServer().getOnlineMode())));
            metrics.addCustomChart(new Metrics.SimplePie("bungeecord_enabled", () -> String.valueOf(isBungeeMode())));
            metrics.addCustomChart(new Metrics.SimplePie("plugin_version", DamagePenalty::pluginVersion));
            metrics.addCustomChart(new Metrics.SimplePie("os_name", () -> System.getProperty("os.name")));

        } catch (RuntimeException e) {
            le(className, "bstats error:" + e);
        }
    }
}
