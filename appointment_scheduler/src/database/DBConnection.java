package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class holds methods for Database connection
 */

public abstract class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "root"; // Username
    private static String password = "password"; // Password
    public static Connection connection;  // Connection Interface

    /**
     * This method is used for connecting to Database within the Lab environment
     */

    public static void openConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * This method is used for closing connection to Database within the Lab environment
     */

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
