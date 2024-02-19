package github.tsffish.damagepenalty.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static github.tsffish.damagepenalty.util.DamageMultipliers.damageMultipliers;
import static github.tsffish.damagepenalty.util.DamageMultipliers.defaultMultipliers;

public class PlayerJoinHandler implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        damageMultipliers.put(uuid,defaultMultipliers);
    }
}
