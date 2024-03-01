package github.tsffish.damagepenalty.listener;

import github.tsffish.damagepenalty.DamagePenalty;
import github.tsffish.damagepenalty.api.PlayerMultiplierImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

import static github.tsffish.damagepenalty.DamagePenalty.isDebug;
import static github.tsffish.damagepenalty.config.main.MainConfigHandler.addMultiplier_onPerDamage;
import static github.tsffish.damagepenalty.config.main.MainConfigHandler.setMultiplier_onDeath;

public class PlayerDamageHandler implements Listener {
    private static final Plugin plugin = JavaPlugin.getPlugin(DamagePenalty.class);
    private final PlayerMultiplierImpl playerMultipliers = new PlayerMultiplierImpl();
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Player
        && event.getDamager() instanceof Player
        ) {
            Player victim = (Player) event.getEntity();
            UUID uuid = victim.getUniqueId();

            Player attacker = (Player) event.getDamager();

            double currentDamage = event.getDamage();
            double finalDamage = event.getFinalDamage();
            double currentMultiplier = playerMultipliers.getDamageMultiplier(uuid);

            if (isDebug()){
                attacker.sendMessage("currentDamage: " + currentDamage);
                attacker.sendMessage("finalDamage: " + finalDamage);
                attacker.sendMessage("currentMultiplier: " + currentMultiplier);
            }


            event.setDamage(finalDamage * currentMultiplier);

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
              if (!event.isCancelled()){

                  double newMultiplier = currentMultiplier + addMultiplier_onPerDamage;

                  if (newMultiplier < 1){
                      newMultiplier = 1;
                  }
                  playerMultipliers.setDamageMultiplier(uuid, newMultiplier);

                  double newDamage = event.getDamage() * newMultiplier;

                  if (isDebug()){
                      attacker.sendMessage("NewDamage: " + newDamage);
                  }
              }
            }, 1L);

        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID uuid = player.getUniqueId();
        double newMultiplier = setMultiplier_onDeath;
        playerMultipliers.setDamageMultiplier(uuid, newMultiplier);
    }

}
