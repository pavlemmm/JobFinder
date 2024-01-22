package com.example.jobfinder.util;

import com.example.jobfinder.entities.Job;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class UIUtil {
    public static Button buttonWithImage(String imageName) {
        Button btn = new Button();
        ImageView icon = new ImageView(new Image(String.format("file:icons/%s.png", imageName)));
        icon.setFitWidth(20);
        icon.setPreserveRatio(true);
        btn.setGraphic(icon);
        btn.setPadding(new Insets(5));
        return btn;
    }

    public static BorderPane jobOffer(Job job) {
        Label title = new Label(job.getTitle());
        title.setFont(Font.font(13));
        Label payout = new Label(String.valueOf(job.getPayout()));
        BorderPane bp = new BorderPane();
        HBox leftHb = new HBox(title, payout);
        leftHb.setSpacing(5);
        bp.setLeft(leftHb);
        bp.setRight(new Button("Radi"));
        bp.setPadding(new Insets(8));
        return bp;
    }


}
