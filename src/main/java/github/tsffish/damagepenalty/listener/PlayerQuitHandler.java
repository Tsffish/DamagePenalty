package github.tsffish.damagepenalty.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static github.tsffish.damagepenalty.util.DamageMultipliers.damageMultipliers;

public class PlayerQuitHandler implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        damageMultipliers.remove(uuid);
    }
}
