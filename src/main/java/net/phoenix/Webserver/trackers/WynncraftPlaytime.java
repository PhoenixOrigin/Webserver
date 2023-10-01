package net.phoenix.Webserver.trackers;

import net.phoenix.Webserver.database.DatabaseHandler;
import net.phoenix.Webserver.handlers.api.WynncraftHandler;
import net.phoenix.Webserver.utils.Utilities;

import java.io.IOException;

public class WynncraftPlaytime {

    public static void recordPlaytime() {
        try {
            WynncraftHandler.onlinePlayers().forEach((player) -> {
                DatabaseHandler.record_playtime(Utilities.toUUID(player));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
