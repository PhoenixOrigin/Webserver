package net.phoenix.Webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebserverApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WebserverApplication.class);
        application.setAdditionalProfiles("ssl");
        application.run(args);
        //DatabaseHandler.init();
    }

}
