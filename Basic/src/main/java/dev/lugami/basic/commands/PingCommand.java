package dev.lugami.basic.commands;

import dev.lugami.qlib.command.Command;
import dev.lugami.qlib.command.Param;
import dev.lugami.qlib.util.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PingCommand {

    @Command(names={"ping"}, permission = "")
    public static void ping(Player sender, @Param(name="target", defaultValue="self") Player target) {
        int ping = PlayerUtils.getPing(target);
        sender.sendMessage(target.getDisplayName() + ChatColor.YELLOW + "'s Ping: " + ChatColor.RED + ping);
    }
}

