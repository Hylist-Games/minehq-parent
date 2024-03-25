package dev.lugami.bridge.bukkit.commands.user;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import dev.lugami.bridge.global.profile.Profile;
import dev.lugami.qlib.command.Command;
import dev.lugami.qlib.command.Param;

public class UserSetColorCommand {

    @Command(names = {"user setcolor", "user setcolour"}, permission = "bridge.user", description = "Set a players color", async = true, hidden = true)
    public static void UserSetColorCmd(CommandSender s, @Param(name = "player") Profile pf, @Param(name = "color") String col) {
        String tag = ChatColor.translateAlternateColorCodes('&', col);
        if (col.equals("clear")) tag = "";
        pf.setColor(tag);
        pf.saveProfile();
        s.sendMessage("§aSuccessfully " + (tag.equals("") ? "cleared" : "set") + " the color of " + pf.getUsername() + (!tag.equals("") ? " to " + tag + pf.getUsername() : ""));
    }
}
