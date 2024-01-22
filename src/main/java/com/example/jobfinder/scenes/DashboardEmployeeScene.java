package com.example.jobfinder.scenes;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.util.Session;
import com.example.jobfinder.util.UIUtil;
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
        Employee user = (Employee) Session.getUser();

        BorderPane root = new BorderPane();
        BorderPane topBar = new BorderPane();
        Label nameLabel = new Label(user.getFirstName() + " " + user.getLastName());

        Button allBtn = UIUtil.buttonWithImage("all");

        allBtn.setOnAction(e -> {
            System.exit(0);
        });

        Button jobsBtn = UIUtil.buttonWithImage("jobs");

        jobsBtn.setOnAction(e -> {
            System.exit(0);
        });

        Button messagesBtn = UIUtil.buttonWithImage("message");

        messagesBtn.setOnAction(e -> {
            System.exit(0);
        });

        Button logoutBtn = UIUtil.buttonWithImage("logout");

        logoutBtn.setOnAction(e -> {
            System.exit(0);
        });

        HBox iconBar = new HBox(allBtn, jobsBtn, messagesBtn, logoutBtn);

        iconBar.setSpacing(8);
        iconBar.setAlignment(Pos.CENTER);
        topBar.setRight(iconBar);
        topBar.setLeft(nameLabel);
        topBar.setPadding(new Insets(8));

        root.setTop(topBar);
        VBox jobsList = new VBox();
        root.setCenter(jobsList);

        ArrayList<Job> jobs = JobCRUD.getAllActiveJobs();

        for (Job job: jobs) {
            jobsList.getChildren().add(UIUtil.jobOffer(job));
        }

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("JobFinder - Dashboard");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
