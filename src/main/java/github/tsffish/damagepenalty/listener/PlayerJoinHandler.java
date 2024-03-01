package github.tsffish.damagepenalty.listener;

import github.tsffish.damagepenalty.api.PlayerMultiplierImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinHandler implements Listener {
    private final PlayerMultiplierImpl playerMultipliers = new PlayerMultiplierImpl();
    private static final double defaultValue = 0;
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        playerMultipliers.setDamageMultiplier(uuid,defaultValue);
    }
}
