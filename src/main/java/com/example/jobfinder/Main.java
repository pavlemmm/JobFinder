package com.example.jobfinder;

import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.enums.UserTypes;
import com.example.jobfinder.scenes.DashboardScene;
import com.example.jobfinder.scenes.LoginScene;
import com.example.jobfinder.util.Session;
import javafx.application.Application;
import javafx.stage.Stage;
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        new LoginScene().start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}