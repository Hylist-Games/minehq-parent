package dev.lugami.potpvp.command;

import dev.lugami.potpvp.PotPvPSI;
import dev.lugami.potpvp.follow.FollowHandler;
import dev.lugami.potpvp.match.Match;
import dev.lugami.potpvp.match.MatchHandler;
import dev.lugami.potpvp.match.MatchTeam;
import dev.lugami.potpvp.party.PartyHandler;
import dev.lugami.potpvp.queue.QueueHandler;
import dev.lugami.qlib.command.Command;
import dev.lugami.qlib.command.Param;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class PStatusCommand {

    @Command(names = {"pstatus"}, permission = "op")
    public static void pStatus(Player sender, @Param(name="target", defaultValue = "self") Player target) {
        MatchHandler matchHandler = PotPvPSI.getInstance().getMatchHandler();
        QueueHandler queueHandler = PotPvPSI.getInstance().getQueueHandler();
        PartyHandler partyHandler = PotPvPSI.getInstance().getPartyHandler();
        FollowHandler followHandler = PotPvPSI.getInstance().getFollowHandler();

        sender.sendMessage(ChatColor.RED + target.getName() + ":");
        sender.sendMessage("In match: " + matchHandler.isPlayingMatch(target));
        sender.sendMessage("In match (NC): " + noCacheIsPlayingMatch(target));
        sender.sendMessage("Spectating match: " + matchHandler.isSpectatingMatch(target));
        sender.sendMessage("Spectating match (NC): " + noCacheIsSpectatingMatch(target));
        sender.sendMessage("In or spectating match: " + matchHandler.isPlayingOrSpectatingMatch(target));
        sender.sendMessage("In or spectating match (NC): " + noCacheIsPlayingOrSpectatingMatch(target));
        sender.sendMessage("In queue: " + queueHandler.isQueued(target.getUniqueId()));
        sender.sendMessage("In party: " + partyHandler.hasParty(target));
        sender.sendMessage("Following: " + followHandler.getFollowing(target).isPresent());
    }

    private static boolean noCacheIsPlayingMatch(Player target) {
        for (Match match : PotPvPSI.getInstance().getMatchHandler().getHostedMatches()) {
            for (MatchTeam team : match.getTeams()) {
                if (team.isAlive(target.getUniqueId())) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean noCacheIsSpectatingMatch(Player target) {
        for (Match match : PotPvPSI.getInstance().getMatchHandler().getHostedMatches()) {
            if (match.isSpectator(target.getUniqueId())) {
                return true;
            }
        }

        return false;
    }

    private static boolean noCacheIsPlayingOrSpectatingMatch(Player target) {
        return noCacheIsPlayingMatch(target) || noCacheIsSpectatingMatch(target);
    }

}