package net.phoenix.Webserver.controllers.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.phoenix.Webserver.controllers.discord.api.Command;
import net.phoenix.Webserver.controllers.discord.api.CommandRegistrar;
import net.phoenix.Webserver.controllers.websocket.WebsocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

public class Bot {

    public static final Logger logger = LoggerFactory.getLogger(Bot.class);
    public static JDA jda;
    public static WebsocketServer server;
    public static HashMap<String, Guild> guilds = new HashMap<>();

    public void init() {
        init_websocket();
        logger.info("Websocket || Initialized");
        jda = JDABuilder.createDefault(BotInfo.bot_token).build();
        logger.info("JDA-Bot || Initialized");
        initGuilds();
        logger.info("Guilds || Initialized");
    }

    public void init_websocket() {
        server = new WebsocketServer();
    }

    public void initGuilds() {
        CommandRegistrar registrar = new CommandRegistrar(jda);
        Command command = new Command("guild", "Guild commands", Command.CommandExecutors.SUBCOMMAND_GROUP.getExecutor());

        Guild alpha = new Guild("SB Alpha PSI", "alpha");
        Guild lambda = new Guild("SB Lambda PI", "lambda");
        Guild sigma = new Guild("SB Sigma Chi", "sigma");
        Guild uni = new Guild("SB University", "uni");

        guilds.put("SB Alpha PSI", alpha);
        guilds.put("SB Lambda PI", lambda);
        guilds.put("SB Sigma Chi", sigma);
        guilds.put("SB University", uni);


        command.addSubcommandGroups(List.of(alpha.subcommand(), lambda.subcommand(), sigma.subcommand(), uni.subcommand()));
        registrar.registerCommand(command);
    }

}
