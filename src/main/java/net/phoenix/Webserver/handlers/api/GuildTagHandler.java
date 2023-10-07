package net.phoenix.Webserver.handlers.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GuildTagHandler {

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
        for(String guild : guildListCache) {
            json.remove(guild);
        }
        guildListCache = guilds;

        for(String guild : guildListCache) {
            if(json.has(guild)) continue;

            try {
                JsonObject object = WynncraftHandler.guildStats(guild);
                String tag = object.get("prefix").getAsString();
                json.addProperty(guild, tag);
            } catch (IOException e) {
                return;
            }
        }

        try (PrintWriter w = new PrintWriter(f)){
            w.println(json.toString());
            w.flush();
        } catch (IOException ignored) {
        }

    }

    public static JsonObject parseJSONFile(File file) throws IOException {
        String content = new String(Files.readAllBytes(file.toPath()));
        return JsonParser.parseString(content).getAsJsonObject();
    }

}
