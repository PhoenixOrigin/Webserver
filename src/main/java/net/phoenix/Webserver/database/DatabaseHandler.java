package net.phoenix.Webserver.database;

import net.phoenix.Webserver.utils.Utilities;

import java.sql.*;
import java.time.Instant;
import java.util.Calendar;
import java.util.UUID;

public class DatabaseHandler {

    public static Connection wynncraft_database = null;

    public static void init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            return;
        }
        try {
            wynncraft_database = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DatabaseInfo.wynn_db_name, DatabaseInfo.wynn_db_usrname, DatabaseInfo.wynn_db_pwd);
            fixWynnDb();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fixWynnDb() throws SQLException {
        PreparedStatement statement = wynncraft_database.prepareStatement("CREATE TABLE IF NOT EXISTS playtime (uuid UUID NOT NULL, playtime int NOT NULL, timestamp timestamp);");
        statement.execute();
    }

    public static void record_playtime(UUID uuid) {
        try {
            PreparedStatement statement = wynncraft_database.prepareStatement("INSERT INTO playtime (uuid, playtime, timestamp) VALUES (?, ?, ?);");
            statement.setObject(1, uuid);
            statement.setInt(2, 5);
            statement.setTimestamp(3, Timestamp.from(Instant.now()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int get_playtime(String username) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, -7);
            return playtime(Utilities.toUUID(username), Timestamp.from(cal.toInstant()));
        } catch (SQLException ignored) {
        }
        return 0;
    }

    public static int get_playtime(String username, Timestamp from) {
        try {
            return playtime(Utilities.toUUID(username), from);
        } catch (SQLException ignored) {
        }
        return 0;
    }

    private static int playtime(UUID of, Timestamp from) throws SQLException {
        PreparedStatement request = wynncraft_database.prepareStatement("""
                SELECT SUM(playtime)
                FROM playtime
                WHERE uuid = ?
                AND timestamp > ?
                """);
        request.setObject(1, of);
        request.setTimestamp(2, from);

        ResultSet playtime = request.executeQuery();

        if (!playtime.next()) return 0;
        return playtime.getInt(1);
    }

}
