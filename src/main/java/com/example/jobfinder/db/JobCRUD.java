package com.example.jobfinder.db;

import com.example.jobfinder.entities.Job;
import com.example.jobfinder.util.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobCRUD {
    /**
     * Get all jobs as employer
     *
     * @return - Get List of jobs
     */
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

    /**
     * Get all jobs as employee
     *
     * @return - Get List of jobs
     */
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

    /**
     * Get all active jobs
     *
     * @return - Get List of jobs
     */
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

    /**
     * Accept job by jobID
     *
     * @param jobID - Job ID
     */
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

    /**
     * Mark job as done by jobID
     *
     * @param jobID - Job ID
     */
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

    /**
     * Delete job by jobID
     *
     * @param jobID - Job ID
     */
    public static void deleteJob(int jobID) {
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("DELETE FROM Job WHERE Job_ID = ?");
            stmt.setInt(1, jobID);
            stmt.execute();
            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Create new job
     *
     * @param employerID - Employer ID
     * @param titleText - Text for title
     * @param descriptionText - Text for description
     * @param payoutVal - Payout value
     */
    public static void createJob(int employerID, String titleText, String descriptionText, int payoutVal) {
        try {
            DBCon.openConnection();

            PreparedStatement stmt;
            stmt = DBCon.con.prepareStatement("INSERT INTO Job(Employer_ID, Title, Description, Payout) VALUES(?, ?, ?, ?)");
            stmt.setInt(1, employerID);
            stmt.setString(2, titleText);
            stmt.setString(3, descriptionText);
            stmt.setInt(4, payoutVal);
            stmt.execute();
            DBCon.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(JobCRUD.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
