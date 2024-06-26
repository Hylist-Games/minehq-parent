package dev.lugami.qlib.protocol.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerCloseInventoryEvent extends PlayerEvent {

    private static final HandlerList handlerList = new HandlerList();

    public PlayerCloseInventoryEvent(Player player) {
        super(player);
    }

    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}

