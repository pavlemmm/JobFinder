package com.example.jobfinder.scenes;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.ui_elements.Elements;
import com.example.jobfinder.ui_elements.Panes;
import com.example.jobfinder.util.Session;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardEmployeeScene extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        root.setTop(Elements.topBarEmployee(root));
        root.setCenter(Panes.allJobsPaneEmployee());




        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("JobFinder - Dashboard");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}
