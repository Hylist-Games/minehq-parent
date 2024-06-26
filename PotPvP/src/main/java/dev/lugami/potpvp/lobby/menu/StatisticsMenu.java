package dev.lugami.potpvp.lobby.menu;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev.lugami.potpvp.kittype.KitType;
import dev.lugami.potpvp.lobby.menu.statistics.GlobalEloButton;
import dev.lugami.potpvp.lobby.menu.statistics.KitButton;
import dev.lugami.potpvp.lobby.menu.statistics.PlayerButton;
import dev.lugami.qlib.menu.Button;
import dev.lugami.qlib.menu.Menu;
import dev.lugami.qlib.util.ItemBuilder;

public final class StatisticsMenu extends Menu {

    private static final Button BLACK_PANE = Button.fromItem(ItemBuilder.of(Material.STAINED_GLASS_PANE).data(DyeColor.BLACK.getData()).name(" ").build());

    public StatisticsMenu() {
        setAutoUpdate(true);
    }

    @Override
    public String getTitle(Player player) {
        return "Statistics";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(getSlot(3, 1), new PlayerButton());
        buttons.put(getSlot(5, 1), new GlobalEloButton());

        int y = 3;
        int x = 1;

        for (KitType kitType : KitType.getAllTypes()) {
            if (!kitType.isSupportsRanked()) continue;

            buttons.put(getSlot(x++, y), new KitButton(kitType));

            if (x == 8) {
                y++;
                x = 1;
            }
        }

        for (int i = 0; i < 54; i++) {
            buttons.putIfAbsent(i, BLACK_PANE);
        }

        return buttons;
    }

    @Override
    public int size(Map<Integer, Button> buttons) {
        return 9 * 6;
    }

}