package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.entities.Job;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Panes {
    public static VBox allJobsPaneEmployee() {
        VBox jobsList = new VBox();

        ArrayList<Job> jobs = JobCRUD.getAllActiveJobs();

        for (Job job: jobs) {
            jobsList.getChildren().add(Elements.jobOfferEmployee(job));
        }

        return jobsList;
    }

    public static VBox myJobsPane() {
        VBox jobsList = new VBox();

        ArrayList<Job> jobs = JobCRUD.getJobsAsEmployee();



        return jobsList;
    }

    public static BorderPane messagesPane() {
        BorderPane bp = new BorderPane();
        return bp;
    }
}
