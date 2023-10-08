package net.phoenix.Webserver.trackers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.phoenix.Webserver.handlers.api.WynncraftHandler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;

public class GuildTagTracker {

    public static Map<String, String> tagCache = new HashMap<>();
    private static List<String> guildListCache = new ArrayList<>();

    public static void recordGuild() {
        File f = new File("./tags.json");
        List<String> guilds;
        try {
            guilds = WynncraftHandler.guildList();
        } catch (IOException e) {
            return;
        }
        JsonObject json;
        try {
            json = parseJSONFile(f);
        } catch (IOException e) {
            return;
        }
        guildListCache.removeAll(guilds);
        for (String guild : guildListCache) {
            json.remove(guild);
        }
        guildListCache = guilds;

        for (String guild : guildListCache) {
            if (json.has(guild)) continue;

            try {
                JsonObject object = WynncraftHandler.guildStats(guild);
                String tag = object.get("prefix").getAsString();
                json.addProperty(guild, tag);
            } catch (IOException e) {
            }
        }


        try (PrintWriter w = new PrintWriter(f)) {
            w.println(json.toString());
            w.flush();
        } catch (IOException ignored) {
        }


        tagCache.clear();
        for (Map.Entry<String, JsonElement> ele : json.entrySet()) {
            System.out.println(ele.getKey() + ele.getValue().getAsString());
            tagCache.put(ele.getKey(), ele.getValue().getAsString());
        }
    }

    public static JsonObject parseJSONFile(File file) throws IOException {
        String content = new String(Files.readAllBytes(file.toPath()));
        return JsonParser.parseString(content).getAsJsonObject();
    }

    public static JsonObject matches(String tag) {
        JsonObject match = new JsonObject();
        for (Map.Entry<String, String> guild : tagCache.entrySet()) {
            if (Objects.equals(guild.getValue(), tag)) {
                match.addProperty(guild.getKey(), guild.getValue());
            }
        }
        return match;
    }

}
