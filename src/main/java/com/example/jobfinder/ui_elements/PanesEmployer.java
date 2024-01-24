package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.db.MessageCRUD;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.entities.Message;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    public static VBox messagesPane(Job job) {
        VBox messagesList = new VBox();

        ArrayList<Message> messages = MessageCRUD.getAllMessagesFromJob(job.getID());

        for (Message message : messages) {
            messagesList.getChildren().add(Elements.message(message));
        }

        return messagesList;
    }

    public static BorderPane addNewJobPane() {
        BorderPane bp = new BorderPane();

        Label log = new Label("Add new job");

        Label l1 = new Label("Title : ");
        TextField tf1 = new TextField();

        Label l2 = new Label("Description : ");
        TextArea ta1 = new TextArea();

        HBox title = new HBox(l1, tf1);
        HBox desc = new HBox(l2, ta1);

        Button post = new Button("Post");
        post.setPadding(new Insets(6));
        post.setPrefWidth(780);

        VBox v = new VBox(title, desc);
        v.setSpacing(10);
        v.setPadding(new Insets(20));

        bp.setTop(log);
        bp.setCenter(v);
        bp.setBottom(post);
        bp.setPadding(new Insets(10));

        return bp;
    }
}
