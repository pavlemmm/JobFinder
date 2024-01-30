package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.entities.Job;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PanesEmployee {
    /**
     * Create all jobs Pane
     *
     * @return - job offers VBox
     */
    public static VBox allJobsPane() {
        VBox jobsList = new VBox();

        ArrayList<Job> jobs = JobCRUD.getAllActiveJobs();

        for (Job job: jobs) {
            jobsList.getChildren().add(ElementsEmployee.jobOffer(job));
        }

        return jobsList;
    }

    /**
     * Create my jobs Pane
     *
     * @return - job offers VBox
     */
    public static VBox myJobsPane() {
        VBox jobsList = new VBox();

        ArrayList<Job> jobs = JobCRUD.getJobsAsEmployee();

        for (Job job: jobs) {
            jobsList.getChildren().add(ElementsEmployee.myJob(job));
        }

        return jobsList;
    }
}
