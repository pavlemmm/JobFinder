package com.example.jobfinder.ui_elements;

import com.example.jobfinder.db.JobCRUD;
import com.example.jobfinder.db.MessageCRUD;
import com.example.jobfinder.entities.Job;
import com.example.jobfinder.entities.Message;
import com.example.jobfinder.enums.UserTypes;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Elements {

    private static String filePath = "";
    public static Button buttonWithImage(String imageName) {
        Button btn = new Button();
        ImageView icon = new ImageView(new Image(String.format("file:icons/%s.png", imageName)));
        icon.setFitWidth(20);
        icon.setPreserveRatio(true);
        btn.setGraphic(icon);
        btn.setPadding(new Insets(5));
        return btn;
    }

    public static ArrayList<HBox> message(Message message) {
        ArrayList<HBox> list = new ArrayList<>();
        HBox hb = new HBox();
        list.add(hb);
        Label sender = new Label(message.getSender() == UserTypes.Employee ? message.getEmployee().getFirstName() + ": " : message.getEmployer().getCompanyName() + ": ");
        hb.getChildren().addAll(sender, new Label(message.getText()));
        if(!message.getFilePath().equals("")) {
            ImageView img = new ImageView(new Image(message.getFilePath()));
            img.setFitWidth(150);
            img.setPreserveRatio(true);
            HBox imgHb = new HBox(img);
            list.add(imgHb);
        }

        return list;
    }

    public static HBox messageInput(Job job, VBox messagesList, UserTypes userType) {
        HBox hb = new HBox();
        TextField tf = new TextField();
        Button fileBtn = new Button("+");


        fileBtn.setOnAction(e -> {
            File file = new FileChooser().showOpenDialog(new Stage());
            if (file != null) {
                filePath = file.getPath();
            }
        });

        Button btn = new Button("Send");
        tf.setPrefWidth(240);
        btn.setPrefWidth(50);
        fileBtn.setPrefWidth(30);

        hb.getChildren().addAll(tf, fileBtn, btn);
        btn.setOnAction(e -> {
            MessageCRUD.sendMessage(tf.getText(), job, userType, filePath);
            Message message = new Message(tf.getText(), userType, job.getEmployee(), job.getEmployer(), filePath);
            for(HBox msg : Elements.message(message)) {
                messagesList.getChildren().add(msg);
            }

            tf.setText("");
            filePath = "";
        });
        return hb;
    }

    public static VBox messagesPane(Job job) {
        VBox messagesList = new VBox();

        ArrayList<Message> messages = MessageCRUD.getAllMessagesFromJob(job.getID());

        for (Message message : messages) {
            for(HBox msg : Elements.message(message)) {
                messagesList.getChildren().add(msg);
            }
        }

        return messagesList;
    }

}
