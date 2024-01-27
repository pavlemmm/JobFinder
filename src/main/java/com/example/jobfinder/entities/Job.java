package com.example.jobfinder.entities;

import com.example.jobfinder.db.UserCRUD;

public class Job {
    private int ID;
    private int employerID;
    private int employeeID;
    private Employer employer;
    private Employee employee;
    private String title;
    private String description;
    private double payout;
    private String jobState;

    public Job(int ID, int employerID, int employeeID, String title, String description, double payout, String jobState) {
        this.ID = ID;
        this.employerID = employerID;
        this.employeeID = employeeID;
        this.title = title;
        this.description = description;
        this.payout = payout;
        this.jobState = jobState;

        this.employer = UserCRUD.getEmployerByID(employerID);
        this.employee = UserCRUD.getEmployeeByID(employeeID);
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

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    @Override
    public String toString() {
        return "Job{" + "ID=" + ID + ", title=" + title + ", description=" + description + ", payout=" + payout +  ", jobState=" + jobState + '}';
    }
}
