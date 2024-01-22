package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.util.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Elements {
    public static Button buttonWithImage(String imageName) {
        Button btn = new Button();
        ImageView icon = new ImageView(new Image(String.format("file:icons/%s.png", imageName)));
        icon.setFitWidth(20);
        icon.setPreserveRatio(true);
        btn.setGraphic(icon);
        btn.setPadding(new Insets(5));
        return btn;
    }

    public static BorderPane jobOfferEmployee(Job job) {
        BorderPane bp = new BorderPane();
        Label title = new Label(job.getTitle());
        Label payout = new Label(String.valueOf(job.getPayout()));
        HBox leftHb = new HBox(title, payout);
        leftHb.setSpacing(5);
        Button acceptButton = new Button("Accept");

        acceptButton.setOnAction(e -> {
            JobCRUD.acceptJob(job.getID());
        });

        bp.setLeft(leftHb);
        bp.setRight(acceptButton);
        bp.setPadding(new Insets(8));
        return bp;
    }

    public static BorderPane topBarEmployee(BorderPane root) {
        Employee user = Session.getEmployee();

        BorderPane topBar = new BorderPane();

        Label nameLabel = new Label(user.getFirstName() + " " + user.getLastName());

        Button allBtn = Elements.buttonWithImage("all");

        allBtn.setOnAction(e -> {
            root.setCenter(Panes.allJobsPaneEmployee());
        });

        Button jobsBtn = Elements.buttonWithImage("jobs");

        jobsBtn.setOnAction(e -> {
            root.setCenter(Panes.myJobsPane());
        });

        Button messagesBtn = Elements.buttonWithImage("message");

        messagesBtn.setOnAction(e -> {
            root.setCenter(Panes.messagesPane());
        });

        Button logoutBtn = Elements.buttonWithImage("logout");

        logoutBtn.setOnAction(e -> {
            System.exit(0);
        });

        HBox iconBar = new HBox(allBtn, jobsBtn, messagesBtn, logoutBtn);

        iconBar.setSpacing(8);
        iconBar.setAlignment(Pos.CENTER);
        topBar.setRight(iconBar);
        topBar.setLeft(nameLabel);
        topBar.setPadding(new Insets(8));

        return topBar;
    }
}
