module com.example.jobfinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.jobfinder to javafx.fxml;
    exports com.example.jobfinder;
}