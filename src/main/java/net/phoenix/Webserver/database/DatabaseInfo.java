package net.phoenix.Webserver.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource(name = "database", value = "database.properties")
public class DatabaseInfo {

    public static String wynn_db_name;

    public static String wynn_db_usrname;

    public static String wynn_db_pwd;

    @Value("${wynn_db_name}")
    public void setWynn_db_name(String wynn_db_name) {
        DatabaseInfo.wynn_db_name = wynn_db_name;
    }

    @Value("${wynn_db_usrname}")
    public void setWynn_db_usrname(String wynn_db_usrname) {
        DatabaseInfo.wynn_db_usrname = wynn_db_usrname;
    }

    @Value("${wynn_db_pwd}")
    public void setWynn_db_pwd(String wynn_db_pwd) {
        DatabaseInfo.wynn_db_pwd = wynn_db_pwd;
    }

}

