package com.example.jobfinder.scenes;

import com.example.jobfinder.db.UserCRUD;
import com.example.jobfinder.enums.UserTypes;
import com.example.jobfinder.util.Session;
import com.example.jobfinder.util.Validation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterScene extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        TabPane tp = new TabPane();
        root.setCenter(tp);

        tp.getTabs().addAll(regEmployeeTab(stage), regEmployerTab(stage));



        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("JobFinder - Register");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private Tab regEmployeeTab(Stage stage) {
        Tab tab = new Tab("Employee");
        tab.setClosable(false);

        // Tab 1
        Label title = new Label("Register as Employee");

        Label l1 = new Label("Email : ");
        TextField tf1 = new TextField();

        Label l2 = new Label("Password : ");
        TextField tf2 = new PasswordField();

        Label l3 = new Label("First Name : ");
        TextField tf3 = new TextField();

        Label l4 = new Label("Last Name : ");
        TextField tf4 = new TextField();

        Label badCredentials = new Label("");

        HBox name = new HBox(l3, tf3);
        HBox lastName = new HBox(l4, tf4);
        HBox email = new HBox(l1, tf1);
        HBox pass = new HBox(l2, tf2);

        Button register = new Button("Register");
        Button login = new Button("Login");
        Button exit = new Button("Exit");

        HBox buttons = new HBox(register, login, exit);
        VBox v = new VBox(title, name, lastName, email, pass, buttons, badCredentials);

        tab.setContent(v);

        exit.setOnAction(e -> {
            System.exit(0);
        });

        login.setOnAction(e -> {
            stage.close();
            new LoginScene().start(stage);
        });

        register.setOnAction(e -> {
            try {
                String emailText = tf1.getText();
                String passwordText = tf2.getText();
                String firstNameText = tf3.getText();
                String lastNameText = tf4.getText();

                if (!Validation.isEmailValid(emailText)) {
                    badCredentials.setText("Email is not valid!");
                    return;
                }

                if (!UserCRUD.checkIfEmailAvailable(emailText)) {
                    badCredentials.setText("Email is already taken!");
                    return;
                }

                if (!Validation.isNameValid(firstNameText)) {
                    badCredentials.setText("First Name is not valid, it shouldn't have more than 32 characters and should only contain letters!");
                    return;
                }

                if (!Validation.isNameValid(lastNameText)) {
                    badCredentials.setText("Last Name is not valid, it shouldn't have more than 32 characters and should only contain letters!");
                    return;
                }

                if (!Validation.isPasswordValid(passwordText)) {
                    badCredentials.setText("Password is not valid, it should be between 8 and 32 characters!");
                    return;
                }

                Session.setEmployee(UserCRUD.registerEmployee(emailText, passwordText, firstNameText, lastNameText));
                Session.setUserType(UserTypes.Employee);

                stage.close();
                new DashboardScene().start(stage);

            } catch (SQLException | NullPointerException exc) {
                Logger.getLogger(RegisterScene.class.getName()).log(Level.SEVERE, null, exc);
            }
        });

        return tab;
    }

    private Tab regEmployerTab(Stage stage) {
        Tab tab = new Tab("Employer");
        tab.setClosable(false);

        // Tab 2
        Label title = new Label("Register as Employer");

        Label l1 = new Label("Email : ");
        TextField tf1 = new TextField();

        Label l2 = new Label("Password : ");
        TextField tf2 = new PasswordField();

        Label l3 = new Label("Company Name: ");
        TextField tf3 = new TextField();

        Label badCredentials = new Label("");

        HBox companyName = new HBox(l3, tf3);
        HBox email = new HBox(l1, tf1);
        HBox pass = new HBox(l2, tf2);

        Button register = new Button("Register");
        Button login = new Button("Login");
        Button exit = new Button("Exit");

        HBox buttons = new HBox(register, login, exit);
        VBox v = new VBox(title, companyName, email, pass, buttons, badCredentials);

        tab.setContent(v);

        exit.setOnAction(e -> {
            System.exit(0);
        });

        login.setOnAction(e -> {
            stage.close();
            new LoginScene().start(stage);
        });

        register.setOnAction(e -> {
            try {
                String emailText = tf1.getText();
                String passwordText = tf2.getText();
                String companyNameText = tf3.getText();

                if (!Validation.isEmailValid(emailText)) {
                    badCredentials.setText("Email is not valid!");
                    return;
                }

                if (!UserCRUD.checkIfEmailAvailable(emailText)) {
                    badCredentials.setText("Email is already taken!");
                    return;
                }

                if (!Validation.isCompanyNameValid(companyNameText)) {
                    badCredentials.setText("Company Name is not valid, it shouldn't have more than 32 characters and should only contain letters and spaces!");
                    return;
                }

                if (!Validation.isPasswordValid(passwordText)) {
                    badCredentials.setText("Password is not valid, it should be between 8 and 32 characters!");
                    return;
                }

                Session.setEmployer(UserCRUD.registerEmployer(emailText, passwordText, companyNameText));
                Session.setUserType(UserTypes.Employer);

                stage.close();
                new DashboardScene().start(stage);

            } catch (SQLException | NullPointerException exc) {
                Logger.getLogger(RegisterScene.class.getName()).log(Level.SEVERE, null, exc);
            }
        });

        return tab;
    }
}
