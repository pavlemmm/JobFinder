package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.db.UserCRUD;
import com.example.jobfinder.entities.Employer;
import com.example.jobfinder.entities.Job;
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

public class ElementsEmployer {
    /**
     * Create job offer BorderPane
     *
     * @param job - job object
     * @return - job offer BorderPane
     */
    public static BorderPane myJob(Job job) {
        BorderPane bp = new BorderPane();
        Label title = new Label(job.getTitle());
        title.setFont(new Font(14));
        Label employer = new Label(job.getEmployer().getCompanyName());
        Label payout = new Label("Payout: " + String.valueOf(job.getPayout()));
        Label status = new Label("Status: " + String.valueOf(job.getJobState()));



        HBox rightHb = new HBox();

        if(!job.getJobState().equals("Active")) {
            Button messagesBtn = Elements.buttonWithImage("message");

            messagesBtn.setOnAction(e -> {
                new MessagesScene().start(job);
            });
            rightHb.getChildren().add(messagesBtn);
        }

        Button deleteBtn = new Button("Delete");
        deleteBtn.setPadding(new Insets(6));

        deleteBtn.setOnAction(e -> {
            JobCRUD.deleteJob(job.getID());
            bp.setLeft(null);
            bp.setRight(null);
        });

        VBox v = new VBox(title, employer);
        v.setAlignment(Pos.CENTER_LEFT);

        HBox leftHb = new HBox(v, payout, status);
        rightHb.getChildren().add(deleteBtn);
        leftHb.setSpacing(25);
        leftHb.setAlignment(Pos.CENTER);
        rightHb.setSpacing(5);

        bp.setLeft(leftHb);
        bp.setRight(rightHb);
        bp.setPadding(new Insets(8));
        return bp;
    }

    /**
     * Create top bar BorderPane
     *
     * @param root - root Border Pane object
     * @param stage - current stage
     * @return - job offer BorderPane
     */
    public static BorderPane topBar(BorderPane root, Stage stage) {
        Employer user = Session.getEmployer();

        BorderPane topBar = new BorderPane();

        Label nameLabel = new Label(user.getCompanyName());
        nameLabel.setFont(new Font(17));

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
