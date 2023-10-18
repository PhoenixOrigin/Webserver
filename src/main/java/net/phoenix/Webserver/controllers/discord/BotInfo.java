package net.phoenix.Webserver.controllers.discord;

import net.phoenix.Webserver.controllers.websocket.WebsocketInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BotInfo {
    public static String bot_token;
    public static String guild_id;
    public static String alpha_chanel_id;
    public static String lambda_chanel_id;
    public static String sigma_chanel_id;
    public static String sb_university_chanel_id;
    public static String access_token;
    public static String helper_role_id;
    public static String jr_mod_role_id;
    public static String admin_role_id;


    @Value("${alpha_chanel_id}")
    public void setAlpha_chanel_id(String alpha_chanel_id) {
        BotInfo.alpha_chanel_id = alpha_chanel_id;
    }

    @Value("${lambda_chanel_id}")
    public void setLambda_chanel_id(String lambda_chanel_id) {
        BotInfo.lambda_chanel_id = lambda_chanel_id;
    }

    @Value("${sigma_chanel_id}")
    public void setSigma_chanel_id(String sigma_chanel_id) {
        BotInfo.sigma_chanel_id = sigma_chanel_id;
    }

    @Value("${sb_university_chanel_id}")
    public void setSb_university_chanel_id(String sb_university_chanel_id) {
        BotInfo.sb_university_chanel_id = sb_university_chanel_id;
    }

    @Value("${bot_token}")
    public void setBot_token(String bot_token) {
        BotInfo.bot_token = bot_token;
    }

    @Value("${guild_id}")
    public void setGuild_id(String guild_id) {
        BotInfo.guild_id = guild_id;
    }

    @Value("${access_token}")
    public void setAccess_token(String access_token) {
        BotInfo.access_token = access_token;
    }

    @Value("${helper_role_id}")
    public void setHelper_role_id(String helper_role_id) {
        BotInfo.helper_role_id = helper_role_id;
    }

    @Value("${jr_mod_role_id}")
    public void setJr_mod_role_id(String jr_mod_role_id) {
        BotInfo.jr_mod_role_id = jr_mod_role_id;
    }

    @Value("${admin_role_id}")
    public void setAdmin_role_id(String admin_role_id) {
        BotInfo.admin_role_id = admin_role_id;
    }

}
