package net.phoenix.Webserver.controllers.websocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.phoenix.Webserver.controllers.discord.Bot;
import net.phoenix.Webserver.controllers.discord.guilds.AlphaPSI;
import net.phoenix.Webserver.controllers.discord.guilds.LambdaPi;
import net.phoenix.Webserver.controllers.discord.guilds.SBUniveristy;
import net.phoenix.Webserver.controllers.discord.guilds.SigmaChi;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class WebsocketServer extends WebSocketServer {

    public WebsocketServer() {
        super(new InetSocketAddress(80));

    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        if (!handshake.getFieldValue("token").equals(WebsocketInfo.access_token)) {
            conn.send("""
                    {
                        "status": "error",
                        "message": "Invalid token"
                    }
                    """);
            conn.close();
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        JsonObject json = JsonParser.parseString(message).getAsJsonObject();
        String guild = json.get("guild").getAsString();
        if (!Bot.guilds.containsKey(guild)) {
            conn.send("""
                    {
                        "status": "error",
                        "message": "Invalid guild"
                    }
                    """);
            conn.close();
        }
        Bot.guilds.get(guild).incomingJSON(json);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {

    }

    @Override
    public void onStart() {

    }

    public void sendRequest(String json) {
        for (WebSocket conn : getConnections()) {
            conn.send(json);
        }
    }
}
