package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.entities.*;
import com.example.jobfinder.util.Session;
import com.example.jobfinder.util.Validation;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class PanesEmployer {
    public static VBox myJobsPane() {
        VBox jobsList = new VBox();

        ArrayList<Job> jobs = JobCRUD.getJobsAsEmployer();

        for (Job job: jobs) {
            jobsList.getChildren().add(ElementsEmployer.myJob(job));
        }

        return jobsList;
    }

    public static BorderPane addNewJobPane() {
        BorderPane bp = new BorderPane();

        Label log = new Label("Add new job");
        log.setFont(new Font(14));

        Label l1 = new Label("Title : ");
        TextField tf1 = new TextField();

        Label l2 = new Label("Description : ");
        TextArea ta1 = new TextArea();

        Label l3 = new Label("Payout : ");
        TextField tf2 = new TextField();

        HBox title = new HBox(l1, tf1);
        HBox desc = new HBox(l2, ta1);
        HBox payout = new HBox(l3, tf2);

        Label badCredentials = new Label("Incorrect values! Try again!");
        badCredentials.setVisible(false);

        Button post = new Button("Post");
        post.setPadding(new Insets(6));
        post.setPrefWidth(780);

        post.setOnAction(e -> {
            String titleText = tf1.getText();
            String descriptionText = ta1.getText();
            int payoutVal = Validation.isNumberValid(tf2.getText());
            if(payoutVal == -1) {
                badCredentials.setVisible(true);
                return;
            }
            JobCRUD.createJob(Session.getEmployer().getID(), titleText, descriptionText, payoutVal);
            tf1.setText("");
            ta1.setText("");
            tf2.setText("");
        });

        VBox v = new VBox(title, desc, payout, badCredentials);
        v.setSpacing(10);
        v.setPadding(new Insets(20));

        bp.setTop(log);
        bp.setCenter(v);
        bp.setBottom(post);
        bp.setPadding(new Insets(10));

        return bp;
    }
}
