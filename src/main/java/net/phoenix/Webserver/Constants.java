package net.phoenix.Webserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.phoenix.Webserver.utils.RateLimit;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class Constants {

    public static List<String> EGG_TYPES = Arrays.asList("GOBLIN_EGG", "GOBLIN_EGG_GREEN", "GOBLIN_EGG_BLUE", "GOBLIN_EGG_RED", "GOBLIN_EGG_YELLOW");
    public static Gson PRETTY_PRINT_GSON = new GsonBuilder().setPrettyPrinting().create();
    public static Gson GSON = new Gson();
    public static RateLimit WYNNCRAFT_RATE_LIMIT = new RateLimit(50, 1, TimeUnit.SECONDS);
    public static Map<String, UUID> uuidCache = new HashMap<>();

}
