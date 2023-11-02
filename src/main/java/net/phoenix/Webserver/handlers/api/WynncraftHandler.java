package net.phoenix.Webserver.handlers.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.phoenix.Webserver.utils.Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WynncraftHandler {

    public static List<String> onlinePlayers() throws IOException {
        JsonObject object = JsonParser.parseString(Utilities.queryAPI(WynncraftEndpoints.SERVER_LIST.getUrl())).getAsJsonObject();
        List<String> onlinePlayers = new ArrayList<>();
        JsonObject array = object.get("players").getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : array.entrySet()) {
            onlinePlayers.add(entry.getKey());
        }
        return onlinePlayers;
    }


    public enum WynncraftEndpoints {
        SERVER_LIST("https://api.wynncraft.com/v3/player?fullResult=true");

        private final String url;

        WynncraftEndpoints(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

    }

}
