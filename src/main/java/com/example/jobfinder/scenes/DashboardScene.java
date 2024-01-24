package com.example.jobfinder.scenes;

import com.example.jobfinder.enums.UserTypes;
import com.example.jobfinder.ui_elements.ElementsEmployee;
import com.example.jobfinder.ui_elements.ElementsEmployer;
import com.example.jobfinder.ui_elements.PanesEmployee;
import com.example.jobfinder.ui_elements.PanesEmployer;
import com.example.jobfinder.util.Session;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashboardScene extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        if(Session.getUserType() == UserTypes.Employee) {
            root.setTop(ElementsEmployee.topBar(root, stage));
            root.setCenter(PanesEmployee.allJobsPane());
        } else {
            root.setTop(ElementsEmployer.topBar(root, stage));
            root.setCenter(PanesEmployer.myJobsPane());
        }




        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("JobFinder - Dashboard");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}
