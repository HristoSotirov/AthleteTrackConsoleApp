package repository;

import client.Colors;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static final Colors color = new Colors();
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("dbconfig.properties")) {
            Properties prop = new Properties();
            if (input != null) {
                prop.load(input);
                URL = prop.getProperty("db.url");
                USER = prop.getProperty("db.user");
                PASSWORD = prop.getProperty("db.password");
            } else {
                ErrorLoggerConfig.logMessage("Error finding configuration faile - dbconfig.properties");
                System.out.println(color.RED + "Error loading database. Please try again later." + color.RESET);
            }
        } catch (IOException e) {
            ErrorLoggerConfig.logMessage("Error: " + e);
            System.out.println(color.RED + "Error performing this operation. Please try again later." + color.RESET);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
