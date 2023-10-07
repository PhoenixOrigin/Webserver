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
        WynncraftEndpoints.SERVER_LIST.consumeLimit();
        JsonObject object = JsonParser.parseString(Utilities.queryAPI(WynncraftEndpoints.SERVER_LIST.getUrl())).getAsJsonObject();
        WynncraftEndpoints.SERVER_LIST.releaseLimit();
        List<String> onlinePlayers = new ArrayList<>();
        object.remove("request");
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            onlinePlayers.addAll(entry.getValue().getAsJsonArray().asList().stream().map((JsonElement::getAsString)).toList());
        }
        return onlinePlayers;
    }

    public static List<String> guildList() throws IOException {
        WynncraftEndpoints.GUILD_LIST.consumeLimit();
        JsonObject object = JsonParser.parseString(Utilities.queryAPI(WynncraftEndpoints.SERVER_LIST.getUrl())).getAsJsonObject();
        WynncraftEndpoints.GUILD_LIST.releaseLimit();
        return new ArrayList<>(object.get("guilds").getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList());
    }

    public static JsonObject guildStats(String name) throws IOException {
        WynncraftEndpoints.GUILD_STATS.consumeLimit();
        JsonObject object = JsonParser.parseString(Utilities.queryAPI(WynncraftEndpoints.GUILD_STATS.getUrl().replace("name", name))).getAsJsonObject();
        WynncraftEndpoints.GUILD_STATS.releaseLimit();
        return object;
    }


    public enum WynncraftEndpoints {
        SERVER_LIST("https://api.wynncraft.com/public_api.php?action=onlinePlayers"),
        GUILD_LIST("https://api.wynncraft.com/public_api.php?action=guildList"),
        GUILD_STATS("GET https://api.wynncraft.com/public_api.php?action=guildStats&command={name}");



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
