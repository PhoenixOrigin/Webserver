package net.phoenix.Webserver.controllers.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(name = "websocket", value = "websocket.properties")
public class WebsocketInfo {

    public static String access_token;

    @Value("${access_token}")
    public void setAccess_token(String access_token) {
        WebsocketInfo.access_token = access_token;
    }

}

