package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.db.MessageCRUD;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.entities.Message;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PanesEmployee {
    public static VBox allJobsPane() {
        VBox jobsList = new VBox();

        ArrayList<Job> jobs = JobCRUD.getAllActiveJobs();

        for (Job job: jobs) {
            jobsList.getChildren().add(ElementsEmployee.jobOffer(job));
        }

        return jobsList;
    }

    public static VBox myJobsPane() {
        VBox jobsList = new VBox();

        ArrayList<Job> jobs = JobCRUD.getJobsAsEmployee();

        for (Job job: jobs) {
            jobsList.getChildren().add(ElementsEmployee.myJob(job));
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
}
