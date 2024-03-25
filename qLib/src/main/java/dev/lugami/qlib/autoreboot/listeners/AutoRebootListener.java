package dev.lugami.qlib.autoreboot.listeners;

import java.util.concurrent.TimeUnit;

import dev.lugami.qlib.autoreboot.AutoRebootHandler;
import dev.lugami.qlib.event.HourEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AutoRebootListener implements Listener {

    @EventHandler
    public void onHour(HourEvent event) {
        if (AutoRebootHandler.getRebootTimes().contains(event.getHour())) {
            AutoRebootHandler.rebootServer(5, TimeUnit.MINUTES);
        }
    }

}

