package com.example.jobfinder.scenes;

import com.example.jobfinder.db.UserCRUD;
import com.example.jobfinder.entities.Employee;
import com.example.jobfinder.entities.Employer;
import com.example.jobfinder.entities.User;
import com.example.jobfinder.enums.UserTypes;
import com.example.jobfinder.util.Session;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginScene extends Application {


    @Override
    public void start(Stage stage) {
        Label log = new Label("Login");
        log.setPadding(new Insets(5));
        log.setFont(new Font(15));

        Label l1 = new Label("Email : ");
        TextField tf1 = new TextField();

        Label l2 = new Label("Password : ");
        TextField tf2 = new PasswordField();

        HBox email = new HBox(l1, tf1);
        HBox pass = new HBox(l2, tf2);

        Button login = new Button("Login");
        Button register = new Button("Register");
        Button exit = new Button("Exit");
        Button info = new Button("?");

        Label badCredentials = new Label("Incorrect login credentials! Try again!");
        badCredentials.setVisible(false);


        // DEBUG
//        Button quickEmployee = new Button("QUICK EMPLOYEE");
//        quickEmployee.setOnAction(e -> {
//            tf1.setText("pavlemitic@gmail.com");
//            tf2.setText("sifra123");
//        });
//        Button quickEmployer = new Button("QUICK EMPLOYER");
//        quickEmployer.setOnAction(e -> {
//            tf1.setText("google@gmail.com");
//            tf2.setText("sifra123");
//        });
//        HBox debugButtons = new HBox(quickEmployee, quickEmployer);


        HBox buttons = new HBox(login, register, exit, info);
        buttons.setPadding(new Insets(4));
        buttons.setSpacing(3);
        VBox v = new VBox(log, email, pass, badCredentials);
        v.setPadding(new Insets(4));
        v.setSpacing(4);

        BorderPane bp = new BorderPane();
        bp.setCenter(v);
        bp.setBottom(buttons);

        // Exit from app
        exit.setOnAction(e -> {
            System.exit(0);
        });

        // Login to dashboard
        login.setOnAction(e -> {
            try {
                String emailText = tf1.getText();
                String passwordText = tf2.getText();
                User user = UserCRUD.readLoggedUser(emailText, passwordText);
                if(user != null) {
                    stage.close();
                    if(user instanceof Employer) {
                        Session.setEmployer((Employer)user);
                        Session.setUserType(UserTypes.Employer);
                    }
                    else {
                        Session.setEmployee((Employee)user);
                        Session.setUserType(UserTypes.Employee);
                    }
                    new DashboardScene().start(stage);
                }
                else {
                    badCredentials.setVisible(true);
                }
            } catch (SQLException | NullPointerException exc) {
                badCredentials.setVisible(true);
            }

        });

        register.setOnAction(e -> {
            stage.close();
            new RegisterScene().start(stage);
        });

        info.setOnAction(e -> {
            new InfoScene();
        });

        Scene scene = new Scene(bp, 280, 180);

        stage.setTitle("JobFinder - Login");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
