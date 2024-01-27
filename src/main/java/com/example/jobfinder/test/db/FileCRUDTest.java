package com.example.jobfinder.test.db;

import com.example.jobfinder.db.FileCRUD;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class FileCRUDTest {
    /**
     * Test of createFile method, of class FileCRUD.
     */
    @Test(expected=SQLException.class)
    public void createFile() throws SQLException {
        System.out.println("createFile");
        String filePath = null;
        int result = FileCRUD.createFile(filePath);
    }

    /**
     * Test of getFilePath method, of class FileCRUD.
     */
    @Test
    public void getFilePath() {
        System.out.println("getFilePath");
        int fileID = 0;
        String result = FileCRUD.getFilePath(fileID);
        assertEquals("", result);
    }
}
