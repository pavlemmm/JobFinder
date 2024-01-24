package com.example.jobfinder.entities;

import com.example.jobfinder.db.UserCRUD;
import com.example.jobfinder.enums.UserTypes;

import java.util.Date;

public class Message {
    private int messageID;
    private int employerID;
    private int employeeID;
    private Employee employee;
    private Employer employer;
    private int jobID;
    private String text;
    private UserTypes sender;
    private String file;

    public Message(int messageID, String text, UserTypes sender, int employerID, int employeeID) {
        this.messageID = messageID;
        this.text = text;
        this.sender = sender;
        this.employerID = employerID;
        this.employeeID = employeeID;

        employee = UserCRUD.getEmployeeByID(employeeID);
        employer = UserCRUD.getEmployerByID(employerID);
    }

    public Message(String text, UserTypes sender, Employee employee, Employer employer) {
        this.text = text;
        this.sender = sender;
        this.employee = employee;
        this.employer = employer;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
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

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserTypes getSender() {
        return sender;
    }

    public void setSender(UserTypes sender) {
        this.sender = sender;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Employer getEmployer() {
        return employer;
    }
}
