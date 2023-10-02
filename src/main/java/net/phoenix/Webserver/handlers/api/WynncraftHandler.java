package net.phoenix.Webserver.handlers.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.phoenix.Webserver.Constants;
import net.phoenix.Webserver.utils.Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WynncraftHandler {

    public static List<String> onlinePlayers() throws IOException {
        //WynncraftEndpoints.SERVER_LIST.consumeLimit();
        System.out.println("e");
        JsonObject object = JsonParser.parseString(Utilities.queryAPI(WynncraftEndpoints.SERVER_LIST.getUrl())).getAsJsonObject();
        System.out.println("f");
        //WynncraftEndpoints.SERVER_LIST.releaseLimit();
        List<String> onlinePlayers = new ArrayList<>();
        object.remove("request");
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            System.out.println("x");
            System.out.println(entry.getValue().getAsJsonArray());
            onlinePlayers.addAll(entry.getValue().getAsJsonArray().asList().stream().map((JsonElement::getAsString)).toList());
        }
        System.out.println(onlinePlayers);
        return onlinePlayers;
    }

    public enum WynncraftEndpoints {
        SERVER_LIST(" https://api.wynncraft.com/public_api.php?action=onlinePlayers");


        private final String url;

        WynncraftEndpoints(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void consumeLimit() {
            Constants.WYNNCRAFT_RATE_LIMIT.consume();
        }

        public void releaseLimit() {
            Constants.WYNNCRAFT_RATE_LIMIT.release();
        }

    }

}
