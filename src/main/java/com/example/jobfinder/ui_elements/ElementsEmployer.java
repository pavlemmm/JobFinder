package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.db.MessageCRUD;
import com.example.jobfinder.db.UserCRUD;
import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Employer;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.entities.Message;
import com.example.jobfinder.enums.UserTypes;
import com.example.jobfinder.scenes.LoginScene;
import com.example.jobfinder.scenes.MessagesScene;
import com.example.jobfinder.util.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ElementsEmployer {
    public static BorderPane myJob(Job job) {
        BorderPane bp = new BorderPane();
        Label title = new Label(job.getTitle());
        Label employer = new Label(job.getEmployer().getCompanyName());
        Label payout = new Label(String.valueOf(job.getPayout()));

        Button messagesBtn = Elements.buttonWithImage("message");
        Button deleteBtn = new Button("Delete");
        deleteBtn.setPadding(new Insets(6));

        messagesBtn.setOnAction(e -> {
            new MessagesScene().start(job);
        });

        deleteBtn.setOnAction(e -> {
            JobCRUD.deleteJob(job.getID());
            bp.setLeft(null);
            bp.setRight(null);
        });

        VBox v = new VBox(title, employer);
        v.setAlignment(Pos.CENTER_LEFT);

        HBox leftHb = new HBox(v, payout);
        leftHb.setSpacing(5);
        HBox rightHb = new HBox(messagesBtn, deleteBtn);
        rightHb.setSpacing(5);

        bp.setLeft(leftHb);
        bp.setRight(rightHb);
        bp.setPadding(new Insets(8));
        return bp;
    }

    public static BorderPane topBar(BorderPane root, Stage stage) {
        Employer user = Session.getEmployer();

        BorderPane topBar = new BorderPane();

        Label nameLabel = new Label(user.getCompanyName());

        Button myJobs = Elements.buttonWithImage("jobs");

        myJobs.setOnAction(e -> {
            root.setCenter(PanesEmployer.myJobsPane());
        });

        Button addBtn = Elements.buttonWithImage("plus");

        addBtn.setOnAction(e -> {
            root.setCenter(PanesEmployer.addNewJobPane());
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

        HBox iconBar = new HBox(myJobs, addBtn, deleteBtn, logoutBtn);

        iconBar.setSpacing(8);
        iconBar.setAlignment(Pos.CENTER);
        topBar.setRight(iconBar);
        topBar.setLeft(nameLabel);
        topBar.setPadding(new Insets(8));

        return topBar;
    }


}