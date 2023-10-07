package net.phoenix.Webserver.tasks;

import net.phoenix.Webserver.handlers.api.GuildTagHandler;
import net.phoenix.Webserver.handlers.api.HypixelHandler;
import net.phoenix.Webserver.trackers.WynncraftPlaytime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Tasks {

    @Scheduled(fixedRate = 300000)
    public static void recordWynnPlaytime() {
        WynncraftPlaytime.recordPlaytime();
    }

    @Scheduled(fixedRate = 300000)
    public static void updateBazaar() {
        HypixelHandler.update_bazaar();
    }

    @Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 1)
    public static void updateGuildList(){
        GuildTagHandler.recordGuild();
    }

}
