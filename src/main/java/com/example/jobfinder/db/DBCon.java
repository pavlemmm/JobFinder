package com.example.jobfinder.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBCon {
    public static Connection con = null;

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "jobfinder";
    private static final String username = "root";
    private static final String password = "";

    /**
     * Method for opening connection
     *
     * @throws SQLException -- throws sql exception
     */
    public static void openConnection() throws SQLException {
        con = DriverManager.getConnection(url + dbName, username, password);
    }

    /**
     * Method for closing connection
     *
     * @throws SQLException -- throws sql exception
     */
    public static void closeConnection() throws SQLException {
        con.close();
    }
}
