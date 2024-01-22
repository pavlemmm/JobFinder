package com.example.jobfinder.db;

import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Employer;
import com.example.jobfinder.entities.User;
import com.example.jobfinder.util.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserCRUD {
    /**
     * Get logged user
     *
     * @throws SQLException throws sql exception
     * @param email Email string
     * @param password Password string
     * @return Returns User
     */
    public static User readLoggedUser(String email, String password) throws SQLException {
        DBCon.openConnection();

        PreparedStatement stmt = DBCon.con.prepareStatement("SELECT * FROM employee WHERE email = ? AND password = ?");
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet set = stmt.executeQuery();

        if (set.next()) {
            Employee user = new Employee();

            user.setID(set.getInt("Employee_ID"));
            user.setFirstName(set.getString("First_Name"));
            user.setLastName(set.getString("Last_Name"));
            user.setEmail(set.getString("Email"));
            user.setJobID(set.getInt("Job_ID"));

            DBCon.closeConnection();
            return user;
        }
        else {
            stmt = DBCon.con.prepareStatement("SELECT * FROM employer WHERE email = ? AND password = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            set = stmt.executeQuery();

            if (set.next()) {
                Employer user = new Employer();

                user.setID(set.getInt("Employer_ID"));
                user.setCompanyName(set.getString("Company_Name"));
                user.setEmail(set.getString("Email"));

                DBCon.closeConnection();
                return user;
            }
        }
        DBCon.closeConnection();
        return null;
    }

    /**
     * Insert new Employee in Database
     *
     * @throws SQLException - throws sql exception
     * @param email - Email string
     * @param password - Password string
     * @param firstName - First Name string
     * @param lastName - Last Name string
     * @return - Returns Employee
     */
    public static Employee registerEmployee(String email, String password, String firstName, String lastName) throws SQLException {
        DBCon.openConnection();

        PreparedStatement stmt = DBCon.con.prepareStatement("INSERT INTO employee(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, email);
        stmt.setString(4, password);

        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();

        Employee user = new Employee();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        keys.next();
        user.setID(keys.getInt(1));

        DBCon.closeConnection();
        return user;
    }

    /**
     * Insert new Employer in Database
     *
     * @throws SQLException - throws sql exception
     * @param email - Email string
     * @param password - Password string
     * @param companyName - Company Name string
     * @return - Returns Employer
     */
    public static Employer registerEmployer(String email, String password, String companyName) throws SQLException {
        DBCon.openConnection();

        PreparedStatement stmt = DBCon.con.prepareStatement("INSERT INTO employer(COMPANY_NAME, EMAIL, PASSWORD) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, companyName);
        stmt.setString(2, email);
        stmt.setString(3, password);

        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();

        Employer user = new Employer();
        user.setEmail(email);
        user.setCompanyName(companyName);

        keys.next();
        user.setID(keys.getInt(1));

        DBCon.closeConnection();
        return user;
    }

    /**
     * Method for checking if email is available
     *
     * @throws SQLException - throws sql exception
     * @param email - Email string
     */
    public static boolean checkIfEmailAvailable(String email) throws SQLException{
        DBCon.openConnection();

        PreparedStatement stmt = DBCon.con.prepareStatement("SELECT * FROM Employee WHERE Email = ?");
        stmt.setString(1, email);

        ResultSet set = stmt.executeQuery();

        if(set.next()){
            DBCon.closeConnection();
            return false;
        }

        stmt = DBCon.con.prepareStatement("SELECT * FROM Employer WHERE Email = ?");
        stmt.setString(1, email);
        set = stmt.executeQuery();

        if(set.next()){
            DBCon.closeConnection();
            return false;
        }

        DBCon.closeConnection();
        return true;
    }

    public static void deleteCurrentUser() throws SQLException {
        DBCon.openConnection();

        PreparedStatement stmt;
        if(Session.getUser() instanceof Employee) {
            stmt = DBCon.con.prepareStatement("DELETE FROM Employee WHERE Employee_ID = ?");
        } else {
            stmt = DBCon.con.prepareStatement("DELETE FROM Employer WHERE Employer_ID = ?");
        }
        stmt.setInt(1, Session.getUser().getID());
        stmt.execute();

        DBCon.closeConnection();
    }
}
