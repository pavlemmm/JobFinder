package com.example.jobfinder;

import com.example.jobfinder.scenes.LoginScene;
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