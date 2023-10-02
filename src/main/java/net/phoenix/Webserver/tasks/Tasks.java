package net.phoenix.Webserver.tasks;

import net.phoenix.Webserver.handlers.api.HypixelHandler;
import net.phoenix.Webserver.trackers.WynncraftPlaytime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tasks {

    @Scheduled(fixedRate = 60000)
    public static void recordWynnPlaytime() {
        WynncraftPlaytime.recordPlaytime();
    }

    @Scheduled(fixedRate = 300000)
    public static void updateBazaar() {
        HypixelHandler.update_bazaar();
    }

}
