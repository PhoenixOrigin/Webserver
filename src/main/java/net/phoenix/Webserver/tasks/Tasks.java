package net.phoenix.Webserver.tasks;

import net.phoenix.Webserver.handlers.api.HypixelHandler;
import net.phoenix.Webserver.trackers.WynncraftPlaytimeTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tasks {

    private static final Logger logger = LoggerFactory.getLogger(Tasks.class);

    @Scheduled(fixedRate = 300000)
    public static void recordWynnPlaytime() {
        logger.info("Recording Wynncraft playtime");
        WynncraftPlaytimeTracker.recordPlaytime();
        logger.info("Recorded Wynncraft playtime");
    }

    @Scheduled(fixedRate = 300000)
    public static void updateBazaar() {
        HypixelHandler.update_bazaar();
    }

}
