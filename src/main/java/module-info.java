module com.example.parkinglotmvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parkinglotmvc to javafx.fxml;
    exports com.example.parkinglotmvc;
}