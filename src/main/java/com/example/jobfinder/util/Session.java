package com.example.jobfinder.util;

import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Employer;
import com.example.jobfinder.entities.User;
import com.example.jobfinder.enums.UserTypes;

public class Session {
    private static User user;
    private static UserTypes userType;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }

    public static UserTypes getUserType() {
        return userType;
    }

    public static void setUserType(UserTypes userType) {
        Session.userType = userType;
    }
}
