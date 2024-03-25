package dev.lugami.bridge.bukkit.commands.user;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import dev.lugami.bridge.BridgeGlobal;
import dev.lugami.bridge.global.profile.Profile;
import dev.lugami.qlib.command.Command;
import dev.lugami.qlib.command.Param;

public class UserDeleteCommand {

    @Command(names = {"user delete"}, permission = "bridge.user", description = "Delete a players profile", async = true, hidden = true)
    public static void UserDeleteCmd(CommandSender s, @Param(name = "player", extraData = "get") Profile pf) {
        BridgeGlobal.getMongoHandler().removeProfile(pf.getUuid(), callback -> {
            if (callback) {
                if (Bukkit.getOfflinePlayer(pf.getUuid()).isOnline())
                    Bukkit.getPlayer(pf.getUuid()).kickPlayer("§cYour profile has been deleted - please reconnect.");
                BridgeGlobal.getProfileHandler().getProfiles().remove(pf);
                s.sendMessage("§aSuccessfully deleted " + pf.getUsername() + "'s Profile.");
            } else {
                s.sendMessage("§cFailed to delete " + pf.getUsername() + "'s Profile.");
            }
        }, false);
    }
}
