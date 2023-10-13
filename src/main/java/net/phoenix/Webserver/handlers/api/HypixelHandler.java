package net.phoenix.Webserver.handlers.api;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@PropertySource(name = "api", value = "api.properties")
public class HypixelHandler {

    public static HypixelAPI api;
    public static SkyBlockBazaarReply bazaar;
    public static String api_key;

    public HypixelHandler(@Value("${api_key}") String api_key) {
        HypixelHttpClient client = new ApacheHttpClient(UUID.fromString(api_key));
        api = new HypixelAPI(client);
        update_bazaar();
        HypixelHandler.api_key = api_key;
    }

    public static void update_bazaar() {
        api.getSkyBlockBazaar().thenAccept(bazaar -> {
            HypixelHandler.bazaar = bazaar;
        });
    }

}
