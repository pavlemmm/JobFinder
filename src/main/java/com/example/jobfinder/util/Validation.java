package com.example.jobfinder.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isEmailValid(String email) {
        // Compile the regex pattern
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }

    public static boolean isNameValid(String name) {
        // Compile the regex pattern
        Pattern pattern = Pattern.compile("^[A-z]{1,32}$");

        // Create a Matcher object
        Matcher matcher = pattern.matcher(name);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }

    public static boolean isCompanyNameValid(String companyName) {
        // Compile the regex pattern
        Pattern pattern = Pattern.compile("^[A-z ]{1,32}$");

        // Create a Matcher object
        Matcher matcher = pattern.matcher(companyName);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }

    public static boolean isPasswordValid(String password) {
        // Compile the regex pattern
        Pattern pattern = Pattern.compile("^.{3,32}$");

        // Create a Matcher object
        Matcher matcher = pattern.matcher(password);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }

    public static int isNumberValid(String num) {
        Pattern pattern = Pattern.compile("^\\d+$");

        // Create a Matcher object
        Matcher matcher = pattern.matcher(num);

        if(!matcher.matches()) return -1;

        int numVal = Integer.parseInt(num);

        if(numVal < 0) {
            return -1;
        }

        if(numVal > 999999) {
            return -1;
        }

        // Return true if the email matches the pattern, otherwise false
        return numVal;
    }
}
