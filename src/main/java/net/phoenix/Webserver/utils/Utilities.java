package net.phoenix.Webserver.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.phoenix.Webserver.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Utilities {

    public static String queryAPI(String url) throws IOException {
        return new BufferedReader(
                new InputStreamReader(new URL(url).openConnection().getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static String queryAPI(String url, Map<String, String> headers) throws IOException {
        URL queryurl = new URL(url);
        HttpURLConnection http = (HttpURLConnection) queryurl.openConnection();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            http.addRequestProperty(entry.getKey(), entry.getValue());
        }
        http.connect();
        return new BufferedReader(
                new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static UUID toUUID(String player) {
        if (Constants.uuidCache.get(player) == null) {
            Map<String, String> headers = new HashMap<>();
            headers.put("user-agent", "phoenix.owo");
            JsonObject resp = null;
            try {
                resp = JsonParser.parseString(queryAPI("https://playerdb.co/api/player/minecraft/" + player, headers)).getAsJsonObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String value = resp.get("data").getAsJsonObject().get("player").getAsJsonObject().get("id").getAsString();
            UUID user = UUID.fromString(value);
            Constants.uuidCache.put(player, user);
            return UUID.fromString(value);
        } else {
            return Constants.uuidCache.get(player);
        }
    }

}
