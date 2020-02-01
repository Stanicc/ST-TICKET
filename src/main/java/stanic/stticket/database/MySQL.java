package stanic.stticket.database;

import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    public Connection connection;
    public Statement statement;

    private String url, user, password;

    public MySQL(FileConfiguration config) {
        this(config.getString("Database.host"), config.getInt("Database.port"),
                config.getString("Database.database"),
                config.getString("Database.user"), config.getString("Database.password"));
    }

    public MySQL(String host, int port, String database, String user, String password) {
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true";
        this.user = user;
        this.password = password;
    }

    public boolean open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (connection == null) connection = DriverManager.getConnection(url, user, password);

            if (statement == null && connection != null) statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return connection != null;
    }

    public boolean close() {
        if (connection != null) try {
            if (statement != null) statement.close();
            connection.close();

            connection = null;
            statement = null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return connection != null;
    }

}