package com.example.jobfinder.scenes;

import com.example.jobfinder.entities.Employer;
import com.example.jobfinder.util.Session;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashboardEmployerScene extends Application {
    @Override
    public void start(Stage stage) {
        Employer user = (Employer) Session.getUser();

        BorderPane root = new BorderPane();
        root.setTop(new Label(user.getCompanyName()));

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("JobFinder - Dashboard");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
