/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author df
 */

public class ConnectionFactory {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null)
                throw new RuntimeException("db.properties not found in resources");
            PROPS.load(in);
            String driver = PROPS.getProperty("driver");
            if (driver != null && !driver.isBlank()) {
                try {
                    Class.forName(driver);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Driver class not found: " + driver, e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading db.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(PROPS.getProperty("url"),
                PROPS.getProperty("user"),
                PROPS.getProperty("password"));
    }
}