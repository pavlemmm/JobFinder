package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.db.MessageCRUD;
import com.example.jobfinder.db.UserCRUD;
import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.entities.Message;
import com.example.jobfinder.enums.UserTypes;
import com.example.jobfinder.scenes.LoginScene;
import com.example.jobfinder.scenes.MessagesScene;
import com.example.jobfinder.util.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ElementsEmployee {
    public static BorderPane jobOffer(Job job) {
        BorderPane bp = new BorderPane();
        Label title = new Label(job.getTitle());
        title.setFont(new Font(14));
        Label employer = new Label(job.getEmployer().getCompanyName());
        Label description = new Label(job.getDescription());
        Label payout = new Label("Payout: " + String.valueOf(job.getPayout()));

        Button acceptButton = new Button("Accept");
        acceptButton.setPadding(new Insets(6));

        acceptButton.setOnAction(e -> {
            JobCRUD.acceptJob(job.getID());
            bp.setLeft(null);
            bp.setRight(null);
        });

        VBox v = new VBox(title, employer);
        v.setAlignment(Pos.CENTER_LEFT);

        HBox leftHb = new HBox(v, description, payout);
        leftHb.setAlignment(Pos.CENTER);
        leftHb.setSpacing(25);
        HBox rightHb = new HBox(acceptButton);
        rightHb.setSpacing(5);

        bp.setLeft(leftHb);
        bp.setRight(rightHb);
        bp.setPadding(new Insets(8));
        return bp;
    }

    public static BorderPane myJob(Job job) {
        BorderPane bp = new BorderPane();
        Label title = new Label(job.getTitle());
        title.setFont(new Font(14));
        Label employer = new Label(job.getEmployer().getCompanyName());
        Label description = new Label(job.getDescription());
        Label payout = new Label("Payout: " + String.valueOf(job.getPayout()));


        Button messagesBtn = Elements.buttonWithImage("message");
        Button doneButton = new Button("Done");
        doneButton.setPadding(new Insets(6));

        messagesBtn.setOnAction(e -> {
            new MessagesScene().start(job);
        });

        doneButton.setOnAction(e -> {
            JobCRUD.jobDone(job.getID());
            bp.setLeft(null);
            bp.setRight(null);
        });

        VBox v = new VBox(title, employer);
        v.setAlignment(Pos.CENTER_LEFT);

        HBox leftHb = new HBox(v, description, payout);
        leftHb.setSpacing(25);
        leftHb.setAlignment(Pos.CENTER);
        HBox rightHb = new HBox(messagesBtn, doneButton);
        rightHb.setSpacing(5);

        bp.setLeft(leftHb);
        bp.setRight(rightHb);
        bp.setPadding(new Insets(8));
        return bp;
    }

    public static BorderPane topBar(BorderPane root, Stage stage) {
        Employee user = Session.getEmployee();

        BorderPane topBar = new BorderPane();

        Label nameLabel = new Label(user.getFirstName() + " " + user.getLastName());
        nameLabel.setFont(new Font(17));

        Button allBtn = Elements.buttonWithImage("all");

        allBtn.setOnAction(e -> {
            root.setCenter(PanesEmployee.allJobsPane());
        });

        Button jobsBtn = Elements.buttonWithImage("jobs");

        jobsBtn.setOnAction(e -> {
            root.setCenter(PanesEmployee.myJobsPane());
        });

        Button deleteBtn = Elements.buttonWithImage("delete");

        deleteBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete user?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    UserCRUD.deleteCurrentUser();

                    Session.setEmployee(null);
                    Session.setEmployer(null);
                    Session.setUserType(null);

                    stage.close();
                    new LoginScene().start(stage);
                }
            });
        });

        Button logoutBtn = Elements.buttonWithImage("logout");

        logoutBtn.setOnAction(e -> {
            Session.setEmployee(null);
            Session.setEmployer(null);
            Session.setUserType(null);

            stage.close();
            new LoginScene().start(stage);
        });

        HBox iconBar = new HBox(allBtn, jobsBtn, deleteBtn, logoutBtn);

        iconBar.setSpacing(8);
        iconBar.setAlignment(Pos.CENTER);
        topBar.setRight(iconBar);
        topBar.setLeft(nameLabel);
        topBar.setPadding(new Insets(8));

        return topBar;
    }
}
