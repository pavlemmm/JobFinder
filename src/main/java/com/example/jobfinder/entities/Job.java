package com.example.jobfinder.entities;

import com.example.jobfinder.enums.JobState;

public class Job {
    private int ID;
    private int employerID;
    private int employeeID;
    private String title;
    private String description;
    private double payout;
    private JobState state;

    public Job(int ID, int employerID, int employeeID, String title, String description, double payout, JobState state) {
        this.ID = ID;
        this.employerID = employerID;
        this.employeeID = employeeID;
        this.title = title;
        this.description = description;
        this.payout = payout;
        this.state = state;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getEmployerID() {
        return employerID;
    }

    public void setEmployerID(int employerID) {
        this.employerID = employerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPayout() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public JobState getState() {
        return state;
    }

    public void setState(JobState state) {
        this.state = state;
    }
}
