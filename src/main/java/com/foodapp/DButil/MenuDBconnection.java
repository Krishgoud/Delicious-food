package com.foodapp.DButil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MenuDBconnection {

    private static final String URL = "jdbc:mysql://localhost:3306/foodapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection connect() {
        try {
            // Register the JDBC driver if necessary
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
