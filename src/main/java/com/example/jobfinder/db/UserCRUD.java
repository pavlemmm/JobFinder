package com.example.jobfinder.db;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserCRUD {
    public static void readLoggedUser(String email, String password) throws SQLException {
        DBCon.openConnection();

        PreparedStatement stmt = DBCon.con.prepareStatement("SELECT * FROM employee WHERE email = ? AND password = ?");
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet set = stmt.executeQuery();
        while (set.next()) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, set.toString());
        }
    }
}
