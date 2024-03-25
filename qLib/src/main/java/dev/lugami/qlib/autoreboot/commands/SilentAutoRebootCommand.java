package dev.lugami.qlib.autoreboot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import dev.lugami.qlib.autoreboot.AutoRebootHandler;
import dev.lugami.qlib.autoreboot.SilentAutoRebootHandler;
import dev.lugami.qlib.command.Command;
import dev.lugami.qlib.command.Param;
import dev.lugami.qlib.qLib;
import dev.lugami.qlib.util.TimeUtils;

import java.util.concurrent.TimeUnit;

public class SilentAutoRebootCommand {

    @Command(names={"sreboot"}, permission="server.reboot")
    public static void reboot(CommandSender sender, @Param(name="time") String unparsedTime) {
            unparsedTime = unparsedTime.toLowerCase();
            int time = TimeUtils.parseTime(unparsedTime);
            SilentAutoRebootHandler.rebootServer(time, TimeUnit.SECONDS);
            sender.sendMessage(ChatColor.YELLOW + "Started auto reboot.");
    }

    @Command(names={"sreboot cancel"}, permission="server.reboot")
    public static void rebootCancel(CommandSender sender) {
        if (!SilentAutoRebootHandler.isRebooting()) {
            sender.sendMessage(ChatColor.RED + "No reboot has been scheduled.");
        } else {
            AutoRebootHandler.cancelReboot();
        }
    }

}

