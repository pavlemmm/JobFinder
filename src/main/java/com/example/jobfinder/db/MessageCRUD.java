package com.example.jobfinder.db;

import com.example.jobfinder.entities.Job;
import com.example.jobfinder.entities.Message;
import com.example.jobfinder.enums.UserTypes;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageCRUD {
    public static ArrayList<Message> getAllMessagesFromJob(int jobID) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("SELECT * FROM Message WHERE Job_ID = ?");
            stmt.setInt(1, jobID);
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                int messageID = set.getInt("Message_ID");
                int employeeID = set.getInt("Employee_ID");
                int employerID = set.getInt("Employer_ID");
                int file_id = set.getInt("file_id");
                String text = set.getString("text");
                UserTypes sender = UserTypes.valueOf(set.getString("sender"));
                messages.add(new Message(messageID, text, sender, employerID, employeeID, file_id));
            }

            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }

        return messages;
    }

    public static void sendMessage(String text, Job job, UserTypes sender, String filePath) {
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("INSERT INTO Message(Employer_ID, Job_ID, Employee_ID, Text, Sender, File_ID) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, job.getEmployerID());
            stmt.setInt(2, job.getID());
            stmt.setInt(3, job.getEmployeeID());
            stmt.setString(4, text);
            stmt.setString(5, sender.toString());
            if(!filePath.equals("")) {
                int file_id = FileCRUD.createFile(filePath);
                stmt.setInt(6, file_id);
            } else {
                stmt.setNull(6, Types.INTEGER);
            }
            stmt.execute();

            DBCon.closeConnection();


        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }
    }


}
