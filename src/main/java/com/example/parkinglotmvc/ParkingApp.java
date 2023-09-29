package com.example.parkinglotmvc;

import javafx.application.Application;
import javafx.stage.Stage;

public class ParkingApp extends Application {
    private ParkingLotView view;

    public ParkingApp() {
    }

    @Override
    public void start(Stage stage) {

        view = new ParkingLotView();
        view.configureLayout(stage);

        new ParkingLotController(view);
    }

    public static void main(String[] args) {
        launch();
    }
}