package com.iamstevol.facebooktask.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;
    private static String url = "jdbc:mysql://localhost:3306/facebook_clone";
    private static String username = "root";
    private static String password = "Timilehin@2015";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection UserModel(Connection connection) {
        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }
}
