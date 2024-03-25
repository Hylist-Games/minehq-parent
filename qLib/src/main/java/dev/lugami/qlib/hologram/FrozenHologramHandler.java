package dev.lugami.qlib.hologram;

import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import dev.lugami.qlib.hologram.builder.HologramBuilder;
import dev.lugami.qlib.hologram.construct.Hologram;
import dev.lugami.qlib.hologram.listener.HologramListener;
import dev.lugami.qlib.qLib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FrozenHologramHandler {

	@Getter private static Set<Hologram> cache = new HashSet<>();

	public static void init() {
		Bukkit.getServer().getPluginManager().registerEvents(new HologramListener(), qLib.getInstance());
	}

	public static HologramBuilder forPlayer(Player player) {
		return new HologramBuilder(Collections.singleton(player.getUniqueId()));
	}

	public static HologramBuilder forPlayers(Collection<Player> players) {

		if (players == null) {
			return new HologramBuilder(null);
		}

		return new HologramBuilder(players.stream().map(Player::getUniqueId).collect(Collectors.toList()));
	}

	public static HologramBuilder createHologram() {
		return forPlayers(null);
	}

}
