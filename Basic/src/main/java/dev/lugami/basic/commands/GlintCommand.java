package dev.lugami.basic.commands;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.*;
import dev.lugami.basic.Basic;
import dev.lugami.qlib.command.Command;
import dev.lugami.qlib.qLib;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class GlintCommand {

    private static final Map<UUID, Boolean> glint = new ConcurrentHashMap<UUID, Boolean>();

    @Command(names={"glint"}, permission= "", description= "Toggle seeing enchantment glint on other players")
    public static void glint(Player sender) {
        glint.put(sender.getUniqueId(), !glint.get(sender.getUniqueId()));
        Bukkit.getScheduler().runTaskAsynchronously(Basic.getInstance(), () -> GlintCommand.save(sender));
        boolean value = glint.get(sender.getUniqueId());
        sender.sendMessage(ChatColor.YELLOW + "You can " + (value ? ChatColor.GREEN + "now" : ChatColor.RED + "no longer") + ChatColor.YELLOW + " see enchantment glints.");
    }

    public static void load(UUID player) {
        qLib.getInstance().runBackboneRedisCommand(redis -> {
            if (redis.exists("glint." + player.toString())) {
                glint.put(player, Boolean.valueOf(redis.get("glint." + player)));
            } else {
                glint.put(player, true);
            }
            return null;
        });
    }

    public static void save(Player player) {
        qLib.getInstance().runBackboneRedisCommand(redis -> {
            redis.set("glint." + player.getUniqueId(), glint.get(player.getUniqueId()).toString());
            return null;
        });
    }

    public static void registerAdapter() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketListener() {
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                UUID uuid = event.getPlayer().getUniqueId();
                ItemStack stack = (ItemStack)packet.getItemModifier().read(0);
                if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasEnchants() && glint.containsKey(uuid) && !(glint.get(uuid)).booleanValue()) {
                    packet = packet.deepClone();
                    event.setPacket(packet);
                    stack = (ItemStack)packet.getItemModifier().read(0);
                    ItemMeta meta = stack.getItemMeta();
                    for (Enchantment enchantment : Enchantment.values()) {
                        if (!meta.hasEnchant(enchantment)) continue;
                        meta.removeEnchant(enchantment);
                    }
                    stack.setItemMeta(meta);
                }
            }

            @Override
            public void onPacketReceiving(PacketEvent packetEvent) {
            }

            @Override
            public ListeningWhitelist getSendingWhitelist() {
                return ListeningWhitelist.EMPTY_WHITELIST;
            }

            @Override
            public ListeningWhitelist getReceivingWhitelist() {
                return ListeningWhitelist.EMPTY_WHITELIST;
            }

            @Override
            public Plugin getPlugin() {
                return Basic.getInstance();
            }
        });
    }
}

