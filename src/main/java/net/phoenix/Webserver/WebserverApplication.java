package net.phoenix.Webserver;

import net.phoenix.Webserver.database.DatabaseHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebserverApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WebserverApplication.class);
        application.run(args);
        DatabaseHandler.init();
        Constants.bot.init();
    }

}
