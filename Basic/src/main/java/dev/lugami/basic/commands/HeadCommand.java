package dev.lugami.basic.commands;

import dev.lugami.qlib.command.Command;
import dev.lugami.qlib.command.Param;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadCommand {

    @Command(names={"head"}, permission="op", description = "Spawn yourself a player's head")
    public static void head(Player sender, @Param(name="name", defaultValue="self") String name) {
        if (name.equals("self")) {
            name = sender.getName();
        }
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setOwner(name);
        item.setItemMeta(meta);
        sender.getInventory().addItem(new ItemStack[]{item});
        sender.sendMessage(ChatColor.GOLD + "You were given " + ChatColor.WHITE + name + ChatColor.GOLD + "'s head.");
    }
}

