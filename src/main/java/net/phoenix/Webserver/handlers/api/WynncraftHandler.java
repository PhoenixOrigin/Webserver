package net.phoenix.Webserver.handlers.api;

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
        object.remove("request");
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            onlinePlayers.addAll(entry.getValue().getAsJsonArray().asList().stream().map((JsonElement::getAsString)).toList());
        }
        return onlinePlayers;
    }

    public static List<String> guildList() throws IOException {
        JsonObject object = JsonParser.parseString(Utilities.queryAPI(WynncraftEndpoints.GUILD_LIST.getUrl())).getAsJsonObject();
        return new ArrayList<>(object.get("guilds").getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList());
    }

    public static JsonObject guildStats(String name) throws IOException {
        JsonObject object = JsonParser.parseString(Utilities.queryAPI(WynncraftEndpoints.GUILD_STATS.getUrl().replace("name", name))).getAsJsonObject();
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

    }

}
