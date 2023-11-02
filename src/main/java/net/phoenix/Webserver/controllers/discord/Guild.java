package net.phoenix.Webserver.controllers.discord;

import com.google.gson.JsonObject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.phoenix.Webserver.controllers.discord.api.Subcommand;
import net.phoenix.Webserver.controllers.discord.api.SubcommandGroup;

import java.util.List;
import java.util.stream.Collectors;

public class Guild {

    public String guildName;
    public String shortName;

    public Guild(String guildName, String shortName) {
        this.guildName = guildName;
        this.shortName = shortName;
    }

    public void chat(JsonObject object, String channel) {
        String username = object.get("username").getAsString();
        String message = object.get("message").getAsString();
        String url = object.get("profile_url").getAsString();
        TextChannel chanel = Bot.jda.getTextChannelById(channel);
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(username, url);
        embed.setDescription(message);
        chanel.sendMessageEmbeds(embed.build()).queue();
    }

    public void incomingJSON(JsonObject object) {
        String action = object.get("action").getAsString();
        if (action.equals("chat")) {
            chat(object, BotInfo.alpha_chanel_id);
        }
    }

    public void invite(String username) {
        action(username, "invite");
    }

    public void kick(String username) {
        action(username, "kick");
    }

    public void execute(String username) {
        action(username, "execute");
    }

    public void promote(String username) {
        action(username, "promote");
    }

    public void demote(String username) {
        action(username, "demote");
    }

    public void mute(String username) {
        action(username, "mute");
    }

    public void unmute(String username) {
        action(username, "unmute");
    }

    public void massKick(List<String> usernames) {
        Bot.server.sendRequest("""
                {
                    "action": "massKick",
                    "guild": "%s",
                    "usernames": [%s],
                }
                """.formatted(guildName, usernames.stream().map((str) -> String.format("\"%s\"", str)).collect(Collectors.joining(", "))));
    }

    public void setRank(String username, String rank) {
        Bot.server.sendRequest("""
                {
                    "action": "setRank",
                    "guild": "%s",
                    "username": "%s",
                    "rank": "%s"
                }
                """.formatted(guildName, username, rank));
    }

    private void action(String data, String action) {
        Bot.server.sendRequest("""
                {
                    "action": "%s",
                    "guild": "%s",
                    "data": "%s"
                }
                """.formatted(action, guildName, data));
    }

    public SubcommandGroup subcommand() {
        Subcommand invite = new Subcommand("invite", "Invite a user to the guild!", List.of(new OptionData(OptionType.STRING, "user", "The user to invite", true)), (event) -> {
            if (event.getMember().getRoles().contains(event.getGuild().getRoleById(BotInfo.helper_role_id))) {
                invite(event.getOption("user").getAsString());
            }
        });

        Subcommand kick = new Subcommand("kick", "Kick a user from the guild!", List.of(new OptionData(OptionType.STRING, "user", "The user to kick", true)), (event) -> {
            if (event.getMember().getRoles().contains(event.getGuild().getRoleById(BotInfo.jr_mod_role_id))) {
                kick(event.getOption("user").getAsString());
            }
        });

        Subcommand execute = new Subcommand("execute", "Execute a user from the guild!", List.of(new OptionData(OptionType.STRING, "command", "The command to execute", true)), (event) -> {
            if (event.getMember().getRoles().contains(event.getGuild().getRoleById(BotInfo.admin_role_id))) {
                execute(event.getOption("user").getAsString());
            }
        });

        Subcommand promote = new Subcommand("promote", "Promote a user in the guild!", List.of(new OptionData(OptionType.STRING, "user", "The user to promote", true)), (event) -> {
            if (event.getMember().getRoles().contains(event.getGuild().getRoleById(BotInfo.jr_mod_role_id))) {
                promote(event.getOption("user").getAsString());
            }
        });

        Subcommand demote = new Subcommand("demote", "Demote a user in the guild!", List.of(new OptionData(OptionType.STRING, "user", "The user to demote", true)), (event) -> {
            if (event.getMember().getRoles().contains(event.getGuild().getRoleById(BotInfo.jr_mod_role_id))) {
                demote(event.getOption("user").getAsString());
            }
        });

        Subcommand mute = new Subcommand("mute", "Mute a user in the guild!", List.of(new OptionData(OptionType.STRING, "user", "The user to mute", true)), (event) -> {
            if (event.getMember().getRoles().contains(event.getGuild().getRoleById(BotInfo.jr_mod_role_id))) {
                mute(event.getOption("user").getAsString());
            }
        });

        Subcommand unmute = new Subcommand("unmute", "Unmute a user in the guild!", List.of(new OptionData(OptionType.STRING, "user", "The user to unmute", true)), (event) -> {
            if (event.getMember().getRoles().contains(event.getGuild().getRoleById(BotInfo.jr_mod_role_id))) {
                unmute(event.getOption("user").getAsString());
            }
        });

        Subcommand massKick = new Subcommand("massKick", "Mass kick users from the guild!", List.of(new OptionData(OptionType.STRING, "users", "The users to kick", true)), (event) -> {
            if (event.getMember().getRoles().contains(event.getGuild().getRoleById(BotInfo.jr_mod_role_id))) {
                massKick(List.of(event.getOption("users").getAsString().split(" ")));
            }
        });

        Subcommand setRank = new Subcommand("setRank", "Set a user's rank in the guild!", List.of(new OptionData(OptionType.STRING, "user", "The user to set the rank of", true), new OptionData(OptionType.STRING, "rank", "The rank to set the user to", true)), (event) -> {
            if (event.getMember().getRoles().contains(event.getGuild().getRoleById(BotInfo.jr_mod_role_id))) {
                setRank(event.getOption("user").getAsString(), event.getOption("rank").getAsString());
            }
        });

        return new SubcommandGroup(shortName, shortName + " commands", List.of(invite, kick, execute, promote, demote, mute, unmute, massKick, setRank));
    }

}
