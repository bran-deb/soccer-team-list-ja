package org.bpadilla.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBasedatos {

    private static String url = "jdbc:mysql://localhost:3306/java_lista?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "diosdeb";
    private static Connection connection;       //singleton

    public static  Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url,username,password);
        }
        return connection;
    }
}