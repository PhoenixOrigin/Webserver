package net.phoenix.Webserver.trackers;

import net.phoenix.Webserver.database.DatabaseHandler;
import net.phoenix.Webserver.handlers.api.WynncraftHandler;
import net.phoenix.Webserver.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public class WynncraftPlaytime {

    private static Logger logger = LoggerFactory.getLogger(WynncraftPlaytime.class);

    public static void recordPlaytime() {
        try {
            logger.info("Attempting to log playtime");
            List<String> onlinePlayers = WynncraftHandler.onlinePlayers();
            DatabaseHandler.record_playtime(onlinePlayers.stream().map(Utilities::toUUID).toList());
            logger.info("Recorded playtime");
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.error(sw.toString());
        }
    }

}
