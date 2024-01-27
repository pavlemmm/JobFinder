module com.example.jobfinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;
    requires junit;


    opens com.example.jobfinder to javafx.fxml;
    exports com.example.jobfinder;
    exports com.example.jobfinder.test.db;
}