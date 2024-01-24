package com.example.jobfinder.db;

import com.example.jobfinder.entities.Job;
import com.example.jobfinder.entities.Message;
import com.example.jobfinder.enums.UserTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                String file_id = set.getString("file_id");
                String text = set.getString("text");
                UserTypes sender = UserTypes.valueOf(set.getString("sender"));
                messages.add(new Message(messageID, text, sender, employerID, employeeID));
            }

            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }

        return messages;
    }

    public static void sendMessage(String text, Job job, UserTypes sender) {
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("INSERT INTO Message(Employer_ID, Job_ID, Employee_ID, Text, Sender) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, job.getEmployerID());
            stmt.setInt(2, job.getID());
            stmt.setInt(3, job.getEmployeeID());
            stmt.setString(4, text);
            stmt.setString(5, sender.toString());
            stmt.execute();

            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }
    }


}
