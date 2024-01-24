package com.example.jobfinder.ui_elements;

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

public class Elements {
    public static Button buttonWithImage(String imageName) {
        Button btn = new Button();
        ImageView icon = new ImageView(new Image(String.format("file:icons/%s.png", imageName)));
        icon.setFitWidth(20);
        icon.setPreserveRatio(true);
        btn.setGraphic(icon);
        btn.setPadding(new Insets(5));
        return btn;
    }

    public static HBox message(Message message) {
        HBox hb = new HBox();
        Label sender = new Label(message.getSender() == UserTypes.Employee ? message.getEmployee().getFirstName() + ": " : message.getEmployer().getCompanyName() + ": ");
        hb.getChildren().addAll(sender, new Label(message.getText()));

        return hb;
    }

    public static HBox messageInput(Job job, VBox messagesList, UserTypes userType) {
        HBox hb = new HBox();
        TextField tf = new TextField();
        Button btn = new Button("Send");
        tf.setPrefWidth(260);
        btn.setPrefWidth(60);
        hb.getChildren().addAll(tf, btn);
        btn.setOnAction(e -> {
            MessageCRUD.sendMessage(tf.getText(), job, userType);
            Message message = new Message( tf.getText(), userType, job.getEmployee(), job.getEmployer());
            tf.setText("");
            messagesList.getChildren().add(Elements.message(message));
        });
        return hb;
    }

}
