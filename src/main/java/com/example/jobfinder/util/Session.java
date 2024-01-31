package com.example.jobfinder.util;

import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Employer;
import com.example.jobfinder.enums.UserTypes;

public class Session {
    private static Session instance;

    private static UserTypes userType;
    private static Employee employee;
    private static Employer employer;

    private Session() {
    }

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public static Employee getEmployee() {
        return employee;
    }

    public static Employer getEmployer() {
        return employer;
    }

    public static void setEmployee(Employee employee) {
        Session.employee = employee;
    }

    public static void setEmployer(Employer employer) {
        Session.employer = employer;
    }

    public static UserTypes getUserType() {
        return userType;
    }

    public static void setUserType(UserTypes userType) {
        Session.userType = userType;
    }
}
