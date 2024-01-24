package com.example.jobfinder.scenes;

import com.example.jobfinder.entities.Employer;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.entities.User;
import com.example.jobfinder.enums.UserTypes;
import com.example.jobfinder.ui_elements.*;
import com.example.jobfinder.util.Session;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessagesScene {
    public void start(Job job) {
        BorderPane root = new BorderPane();

        if(Session.getUserType() == UserTypes.Employee) {
            VBox messagesList = PanesEmployee.messagesPane(job);
            root.setCenter(messagesList);
            root.setBottom(Elements.messageInput(job, messagesList, UserTypes.Employee));
        } else {
            VBox messagesList = PanesEmployer.messagesPane(job);
            root.setCenter(messagesList);
            root.setBottom(Elements.messageInput(job, messagesList, UserTypes.Employer));
        }


        Scene scene = new Scene(root, 320, 240);

        Stage stage = new Stage();
        stage.setTitle("JobFinder - Messages");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}