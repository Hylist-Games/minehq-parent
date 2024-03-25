package dev.lugami.bridge.bukkit.commands.updater.group;

import dev.lugami.bridge.BridgeGlobal;
import dev.lugami.bridge.bukkit.Bridge;
import dev.lugami.qlib.command.Command;
import dev.lugami.qlib.command.Param;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class UpdaterGroupRemoveCommand {

    @Command(names = "updater group remove", permission = "bridge.updater", hidden = true, description = "Remove a group from the update group")
    public static void groupremove(CommandSender sender, @Param(name = "group") String group) {
        List<String> configGroups = Bridge.getInstance().getConfig().getStringList("updaterGroups");
        if(BridgeGlobal.getUpdaterGroups().stream().noneMatch(s -> s.equalsIgnoreCase(group))) {
            sender.sendMessage(ChatColor.RED + "There is no such group in the list with the name \"" + group + "\".");
            return;
        }
        BridgeGlobal.removeUpdaterGroup(group);
        configGroups.remove(group);
        Bridge.getInstance().getConfig().set("updaterGroups", configGroups);
        Bridge.getInstance().saveConfig();
        sender.sendMessage(ChatColor.GREEN + "Successfully removed the group \"" + group + "\" from the update groups.");
    }

}
