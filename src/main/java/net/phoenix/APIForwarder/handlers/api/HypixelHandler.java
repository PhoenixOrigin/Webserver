package net.phoenix.APIForwarder.handlers.api;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;
import net.phoenix.APIForwarder.api.HypixelController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@PropertySource(name = "api", value = "api.properties")
public class HypixelHandler {

    public HypixelAPI api;
    public SkyBlockBazaarReply bazaar;

    public HypixelHandler(@Value("${api_key}") String api_key) {
        HypixelHttpClient client = new ApacheHttpClient(UUID.fromString(api_key));
        this.api = new HypixelAPI(client);
    }

    @Scheduled(fixedRate = 300000)
    public void update_bazaar(){
        api.getSkyBlockBazaar().thenAccept(bazaar -> {
            System.out.println("r");
            this.bazaar = bazaar;
            System.out.println(bazaar.getProduct("GOBLIN_EGG"));
        });
    }



}
