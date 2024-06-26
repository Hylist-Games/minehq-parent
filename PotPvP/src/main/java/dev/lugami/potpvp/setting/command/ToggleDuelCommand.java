package dev.lugami.potpvp.setting.command;

import dev.lugami.potpvp.PotPvPSI;
import dev.lugami.potpvp.setting.Setting;
import dev.lugami.potpvp.setting.SettingHandler;
import dev.lugami.qlib.command.Command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * /toggleduels command, allows players to toggle {@link Setting#RECEIVE_DUELS} setting
 */
public final class ToggleDuelCommand {

    @Command(names = { "toggleduels", "td", "tduels" }, permission = "")
    public static void toggleDuel(Player sender) {
        if (!Setting.RECEIVE_DUELS.canUpdate(sender)) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return;
        }

        SettingHandler settingHandler = PotPvPSI.getInstance().getSettingHandler();
        boolean enabled = !settingHandler.getSetting(sender, Setting.RECEIVE_DUELS);

        settingHandler.updateSetting(sender, Setting.RECEIVE_DUELS, enabled);

        if (enabled) {
            sender.sendMessage(ChatColor.GREEN + "Toggled duel requests on.");
        } else {
            sender.sendMessage(ChatColor.RED + "Toggled duel requests off.");
        }
    }

}