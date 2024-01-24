package com.example.jobfinder.db;

import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.util.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobCRUD {
    public static ArrayList<Job> getJobsAsEmployer() {
        ArrayList<Job> jobs = new ArrayList<>();
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("SELECT * FROM Job WHERE Employer_ID = ?");
            stmt.setInt(1, Session.getEmployer().getID());
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                int Employer_ID = set.getInt("Employer_ID");
                int ID = set.getInt("Job_ID");
                int Employee_ID = set.getInt("Employee_ID");
                String title = set.getString("title");
                String description = set.getString("description");
                double payout = set.getDouble("payout");
                String state = set.getString("state");
                jobs.add(new Job(ID, Employer_ID, Employee_ID, title, description, payout, state));
            }

            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }

        return jobs;
    }

    public static ArrayList<Job> getJobsAsEmployee() {
        ArrayList<Job> jobs = new ArrayList<>();
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("SELECT * FROM Job WHERE Employee_ID = ? AND State != 'Finished'");
            stmt.setInt(1, Session.getEmployee().getID());
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                int Employee_ID = set.getInt("Employee_ID");
                int Employer_ID = set.getInt("Employer_ID");
                int ID = set.getInt("Job_ID");
                String title = set.getString("title");
                String description = set.getString("description");
                double payout = set.getDouble("payout");
                String state = set.getString("state");
                jobs.add(new Job(ID, Employer_ID, Employee_ID, title, description, payout, state));
            }

            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }

        return jobs;
    }

    public static ArrayList<Job> getAllActiveJobs() {
        ArrayList<Job> jobs = new ArrayList<>();
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("SELECT * FROM Job WHERE State = 'Active'");
            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                int ID = set.getInt("Job_ID");
                int Employer_ID = set.getInt("Employer_ID");
                int Employee_ID = set.getInt("Employee_ID");
                String title = set.getString("title");
                String description = set.getString("description");
                double payout = set.getDouble("payout");
                String state = set.getString("state");
                jobs.add(new Job(ID, Employer_ID, Employee_ID, title, description, payout, state));
            }

            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }

        return jobs;
    }

    public static void acceptJob(int jobID) {
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("UPDATE Job SET Employee_ID = ?, State = 'In Progress' WHERE Job_ID = ?");
            stmt.setInt(1, Session.getEmployee().getID());
            stmt.setInt(2, jobID);
            stmt.executeUpdate();
            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void jobDone(int jobID) {
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("UPDATE Job SET State = 'Finished' WHERE Job_ID = ?");
            stmt.setInt(1, jobID);
            stmt.executeUpdate();
            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void deleteJob(int jobID) {
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("DELETE FROM Job WHERE Job_ID = ?");
            stmt.setInt(1, jobID);
            stmt.executeUpdate();
            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
