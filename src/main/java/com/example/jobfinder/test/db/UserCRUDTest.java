package com.example.jobfinder.test.db;

import com.example.jobfinder.db.UserCRUD;
import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Employer;
import com.example.jobfinder.entities.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserCRUDTest {
    /**
     * Test of registerEmployer method, of class UserCRUD.
     */
    @Test(expected = SQLException.class)
    public void testRegisterEmployer() throws SQLException {
        System.out.println("registerEmployer");
        String email = null;
        String password = null;
        String companyName = null;
        Employer result = UserCRUD.registerEmployer(email, password, companyName);
    }

    /**
     * Test of registerEmployee method, of class UserCRUD.
     */
    @Test(expected = SQLException.class)
    public void testRegisterEmployee() throws SQLException {
        System.out.println("registerEmployee");
        String email = null;
        String password = null;
        String firstName = null;
        String lastName = null;
        Employee result = UserCRUD.registerEmployee(email, password, firstName, lastName);
    }

    /**
     * Test of readLoggedUser method, of class UserCRUD.
     */
    @Test
    public void readLoggedUser() throws SQLException {
        System.out.println("readLoggedUser");
        String email = null;
        String password = null;
        User result = UserCRUD.readLoggedUser(email, password);
        assertNull(result);
    }

    /**
     * Test of checkIfEmailAvailable method, of class UserCRUD.
     */
    @Test
    public void checkIfEmailAvailable() throws SQLException {
        System.out.println("checkIfEmailAvailable");
        String email = "testmail123123@gmail.com";
        Boolean result = UserCRUD.checkIfEmailAvailable(email);
        assertEquals(true, result);
    }

    /**
     * Test of getEmployerByID method, of class UserCRUD.
     */
    @Test
    public void getEmployerByID() {
        System.out.println("getEmployerByID");
        int employerID = 0;
        Employer result = UserCRUD.getEmployerByID(employerID);
        assertEquals(0, result.getID());
    }

    /**
     * Test of getEmployeeByID method, of class UserCRUD.
     */
    @Test
    public void getEmployeeByID() {
        System.out.println("getEmployeeByID");
        int employeeID = 0;
        Employee result = UserCRUD.getEmployeeByID(employeeID);
        assertEquals(0, result.getID());
    }




}
