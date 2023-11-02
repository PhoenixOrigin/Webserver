package net.phoenix.Webserver.controllers.discord.api;

import io.micrometer.observation.Observation;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Map;

public class PersistantButtonManager extends ListenerAdapter {

    public PersistantButtonManager(JDA jda) {
        // register event listener
        Runnable runnable = () -> System.out.println(jda);
    }

    public interface ButtonExecutor {
        void execute(ButtonInteractionEvent event);
    }

}
