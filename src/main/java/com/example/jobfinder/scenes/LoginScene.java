package com.example.jobfinder.scenes;

import com.example.jobfinder.db.UserCRUD;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginScene extends Application {


    @Override
    public void start(Stage stage) {
        Label log = new Label("LOGIN");

        Label l1 = new Label("Email : ");
        TextField tf1 = new TextField();

        Label l2 = new Label("Password : ");
        TextField tf2 = new PasswordField();

        HBox username = new HBox(l1, tf1);
        HBox pass = new HBox(l2, tf2);

        Button login = new Button("Login");
        Button exit = new Button("Exit");

        Label badCredentials = new Label("Incorrect login credentials! Try again!");
        badCredentials.setVisible(false);

        HBox buttons = new HBox(login, exit);
        VBox v = new VBox(log, username, pass, buttons, badCredentials);

        Scene scene = new Scene(v, 320, 240);


        exit.setOnAction(e -> {
            System.exit(0);
        });

        login.setOnAction(e -> {
            try {
                String emailText = tf1.getText();
                String passwordText = tf2.getText();
                UserCRUD.readLoggedUser(emailText, passwordText);
            } catch (SQLException | NullPointerException exc) {
                badCredentials.setVisible(true);
            }

        });

        stage.setTitle("JobFinder - Login");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
