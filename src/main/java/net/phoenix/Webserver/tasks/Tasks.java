package net.phoenix.Webserver.tasks;

import net.phoenix.Webserver.handlers.api.HypixelHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tasks {

    @Scheduled(fixedRate = 300000)
    public static void recordWynnPlaytime() {
        //WynncraftPlaytime.recordPlaytime();
    }

    @Scheduled(fixedRate = 300000)
    public static void updateBazaar() {
        HypixelHandler.update_bazaar();
    }

}
