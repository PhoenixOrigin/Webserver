package net.phoenix.Webserver.handlers.callables;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.phoenix.Webserver.handlers.api.HypixelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class NickChecker implements Callable<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(NickChecker.class);
    private final String username;
    private final Pattern username_pattern;

    public NickChecker(String username) {
        this.username = username;
        username_pattern = Pattern.compile("[A-Za-z0-9]*");
    }

    @Override
    public Boolean call() {
        HttpClient client = HttpClient.newHttpClient();
        String uuid = toUUID(username, client);
        if (uuid == null) return false;
        return loggedIn(uuid, client);
    }

    @Nullable
    public String toUUID(String username, HttpClient client) {
        if (!username_pattern.matcher(username).matches()) return null;
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.mojang.com/users/profiles/minecraft/" + username)).build();
        JsonObject obj = null;
        try {
            obj = JsonParser.parseString(client.send(request, HttpResponse.BodyHandlers.ofString()).body()).getAsJsonObject();
        } catch (IOException | InterruptedException e) {
            return null;
        }
        if (obj.has("errorMessage")) return null;
        return obj.get("id").getAsString();
    }

    public boolean loggedIn(String uuid, HttpClient client) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(String.format("https://api.hypixel.net/player?key=%s&uuid=%s", HypixelHandler.api_key, uuid))).build();
        HttpResponse<String> resp = null;
        try {
            resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (resp.statusCode() == 403) {
            logger.error("URGENT || Invalid API Key");
            return false;
        }
        return !resp.body().equals("{\"success\":true,\"player\":null}");
    }

}