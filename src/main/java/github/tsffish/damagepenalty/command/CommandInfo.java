package github.tsffish.damagepenalty.command;

import github.tsffish.damagepenalty.DamagePenalty;
import github.tsffish.damagepenalty.api.PlayerMultiplierImpl;
import github.tsffish.damagepenalty.config.main.MainConfigLoad;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static github.tsffish.damagepenalty.DamagePenalty.*;
import static github.tsffish.damagepenalty.config.lang.LangConfigHandler.command_help;
import static github.tsffish.damagepenalty.util.misc.ColorString.t;

public class CommandInfo implements CommandExecutor {
    private final PlayerMultiplierImpl playerMultipliers = new PlayerMultiplierImpl();
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (!sender.isOp()) {
                showPluginInfo(sender);
            } else {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        reloadConfig(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("debug")) {
                        toggleDebug(sender);
                        return true;
                    }
                }

                if (isDebug()) {
                    showDebugInfo(sender);
                } else {
                    showHelpMess(sender);
                }
            }
        } else {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    reloadConfig(sender);
                    return true;
                } else if (args[0].equalsIgnoreCase("debug")) {
                    toggleDebug(sender);
                    return true;
                }
            }

            if (isDebug()) {
                showDebugInfo(sender);
            } else {
                showHelpMess(sender);
            }

        }
        return true;
    }

    private void reloadConfig(CommandSender sender) {
        MainConfigLoad.loadMainConfig(sender, false);
    }

    private void toggleDebug(CommandSender sender) {
        changeIsDebug();
        if (isDebug()) {
            sender.sendMessage("Debug now Enabled");
        } else {
            sender.sendMessage("Debug now Disabled");
        }
    }

    private void showDebugInfo(CommandSender sender) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();
            player.sendMessage("You Current damageMultipliers: " + playerMultipliers.getDamageMultiplier(uuid));

        }
    }

    private static void showPluginInfo(CommandSender sender) {
        sender.sendMessage(ChatColor.GREEN + " ================================");
        sender.sendMessage(" ");
        sender.sendMessage(ChatColor.WHITE + pluginName() + " " + ChatColor.AQUA + pluginVersion());
        sender.sendMessage(" ");
        sender.sendMessage(ChatColor.WHITE + "Author: " + ChatColor.YELLOW + author());
        sender.sendMessage(" ");
        sender.sendMessage(ChatColor.GREEN + " ================================");
    }

    private void showHelpMess(CommandSender sender) {
        if (command_help == null) {
            sender.sendMessage(DamagePenalty.msgline);
            helpMsg(sender);
            sender.sendMessage(DamagePenalty.msgline);

        } else if (command_help.isEmpty()) {
            sender.sendMessage(DamagePenalty.msgline);
            helpMsg(sender);
            sender.sendMessage(DamagePenalty.msgline);

        } else {
            for (String list : command_help) {
                sender.sendMessage(t(list));
            }
        }
    }
    private static void helpMsg(CommandSender sender) {
        sender.sendMessage(" ");
        sender.sendMessage(ChatColor.WHITE + pluginName() + " " + ChatColor.AQUA + "Commands:");
        sender.sendMessage(" ");
        sender.sendMessage(" " + ChatColor.WHITE + "/dpe" + ChatColor.YELLOW + " Display this help information.");
        sender.sendMessage(" " + ChatColor.WHITE + "/dpe reload" + ChatColor.YELLOW + " Reload configuration file.");
        sender.sendMessage(" ");
    }
}

