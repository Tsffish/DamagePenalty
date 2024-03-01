package github.tsffish.damagepenalty.listener;

import github.tsffish.damagepenalty.DamagePenalty;
import github.tsffish.damagepenalty.api.PlayerMultiplierImpl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

import static github.tsffish.damagepenalty.DamagePenalty.isDebug;
import static github.tsffish.damagepenalty.config.main.MainConfigHandler.noGoldAppleExtraHeart;
import static github.tsffish.damagepenalty.util.misc.MessSender.l;

public class PlayerEatHandler implements Listener {
    private static final PotionEffectType absorption = PotionEffectType.ABSORPTION;
    private static final Material goldenApple = Material.GOLDEN_APPLE;
    private final PlayerMultiplierImpl playerMultipliers = new PlayerMultiplierImpl();
    private final DamagePenalty plugin = DamagePenalty.getInstance();
    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        if (event.getItem().getType() == goldenApple) {
            Player player = event.getPlayer();
            String playerName = player.getName();
            UUID uuid = player.getUniqueId();
            if (playerMultipliers.getDamageMultiplier(uuid) >= noGoldAppleExtraHeart){
                if (isDebug()){
                 l("player " +  playerName + "reach noGoldAppleExtraHeart, remove potion effect.");
                }

                Bukkit.getScheduler().runTaskLater(plugin, () -> player.removePotionEffect(absorption), 1L);
            }
        }
    }
}
