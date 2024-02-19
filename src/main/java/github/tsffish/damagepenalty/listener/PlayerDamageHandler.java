package github.tsffish.damagepenalty.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

import static github.tsffish.damagepenalty.util.DamageMultipliers.damageMultipliers;

public class PlayerDamageHandler implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            UUID uuid = player.getUniqueId();
            double currentMultiplier = damageMultipliers.getOrDefault(uuid, 1.0);
            double newMultiplier = currentMultiplier + 0.005;

            if (newMultiplier < 1){
                newMultiplier = 1;
            }

            damageMultipliers.put(uuid, newMultiplier);

            double newDamage = event.getDamage() * newMultiplier;
            event.setDamage(newDamage);

            player.sendMessage("Damage: " + newDamage);

        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID uuid = player.getUniqueId();
        double currentMultiplier = damageMultipliers.getOrDefault(uuid, 1.0);
        double newMultiplier = currentMultiplier * 0.05; // 将当前值设为当前值的5%
        damageMultipliers.put(uuid, newMultiplier);
    }

}
