package github.tsffish.damagepenalty.listener;

import github.tsffish.damagepenalty.api.PlayerMultiplierImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitHandler implements Listener {
    private final PlayerMultiplierImpl playerMultipliers = new PlayerMultiplierImpl();
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        playerMultipliers.removeDamageMultiplier(uuid);
    }
}
