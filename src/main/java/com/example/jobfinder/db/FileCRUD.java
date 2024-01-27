package com.example.jobfinder.db;

import com.example.jobfinder.entities.Employee;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCRUD {
    /**
     * Create new file
     *
     * @throws SQLException - throws sql exception
     * @param filePath - Path to the file
     * @return - returns fileID
     */
    public static int createFile(String filePath) throws SQLException {
        DBCon.openConnection();

        PreparedStatement stmt = DBCon.con.prepareStatement("INSERT INTO file(file_path) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, filePath);

        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();

        keys.next();
        int fileID = keys.getInt(1);

        DBCon.closeConnection();
        return fileID;
    }

    /**
     * Get File Path by fileID
     *
     * @param fileID - File ID
     * @return - returns filePath
     */
    public static String getFilePath(int fileID) {
        String filePath = "";
        try {
            DBCon.openConnection();

            PreparedStatement stmt = DBCon.con.prepareStatement("SELECT * FROM File WHERE file_id = ?");
            stmt.setInt(1, fileID);

            stmt.execute();
            ResultSet set = stmt.executeQuery();

            if(set.next()){
                filePath = set.getString("file_path");
            }

            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }
        return filePath;
    }
}
