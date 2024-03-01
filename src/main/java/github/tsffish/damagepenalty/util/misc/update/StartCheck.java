package github.tsffish.damagepenalty.util.misc.update;

import github.tsffish.damagepenalty.DamagePenalty;
import org.bukkit.plugin.java.JavaPlugin;

import static github.tsffish.damagepenalty.DamagePenalty.*;
import static github.tsffish.damagepenalty.config.main.MainConfigHandler.update_tip;
import static github.tsffish.damagepenalty.util.misc.MessSender.l;

public class StartCheck {
    public static void checkUpdate(int resId)
    {
        new UpdateChecker(JavaPlugin.getPlugin(DamagePenalty.class), resId).getVersion(version ->
        {
            setIsLastestVersion(pluginVersion().equals(version));
            if (!getIsLastestVersion()){
                if (update_tip != null && !update_tip.isEmpty()){
                    for (String list : update_tip){
                        l(list);
                    }
                }
            }
        });
    }
}
