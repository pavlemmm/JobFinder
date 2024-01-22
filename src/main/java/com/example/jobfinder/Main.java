package com.example.jobfinder;

import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.scenes.DashboardEmployeeScene;
import com.example.jobfinder.scenes.LoginScene;
import com.example.jobfinder.util.Session;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // new LoginScene().start(stage);
        Employee u = new Employee();
        u.setFirstName("Pavle");
        u.setLastName("Mitic");
        Session.setEmployee(u);
        new DashboardEmployeeScene().start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}