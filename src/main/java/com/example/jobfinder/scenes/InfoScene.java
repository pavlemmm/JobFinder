package com.example.jobfinder.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class InfoScene {
    public InfoScene() {
        VBox v = new VBox();
        v.setSpacing(5);
        v.setPadding(new Insets(3));
        Label title = new Label("What is logging in?");
        title.setFont(new Font(17));
        v.getChildren().add(title);
        Label text = new Label(whatIsLogging());
        text.setWrapText(true);
        v.getChildren().add(text);

        Scene scene = new Scene(v, 320, 240);
        Stage stage = new Stage();
        stage.setTitle("JobFinder - Login");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Scrape text from wikihow about logging in
     *
     * @return - Scraped text
     */
    public static String whatIsLogging() {
        String text = "No data";
        try {
            Document doc = Jsoup.connect("https://www.wikihow.com/Log-In").get();
            Elements el = doc.select("#step-id-00 > div.step");
            text = el.text();
        } catch (IOException ex) {
            ex.getMessage();
        }
        return text;
    }
}
