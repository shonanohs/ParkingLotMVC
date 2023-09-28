package com.example.parkinglotmvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParkingLotController {
    private IParkingLot parkingLot;
    private ParkingLotFactory factory;
    private ParkingLotView view;
    private Ticket ticket;

    public ParkingLotController(ParkingLotView view) {
        this.view = view;

        configureCalculateButton();
    }

    private void configureCalculateButton() {
        // Set up button action event
        view.getCalculateButton().setOnAction((event) -> {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime entryTime = LocalDateTime.parse(view.getEntryField(), formatter);
                LocalDateTime exitTime = LocalDateTime.parse(view.getExitField(), formatter);

                if (exitTime.isBefore(entryTime)) {
                    view.displayResult("Exit date/time cannot be before entry date/time.");
                    return;
                }

                ticket = new Ticket(entryTime, exitTime);
                ticket.calculateDuration();

                factory = new ParkingLotFactory();
                parkingLot = factory.createParkingLot(view.getSelectedLotType());
                String fee = String.valueOf(parkingLot.calculateFee(ticket));

                view.displayResult("Parking fee = £" + fee);
            }
            catch (DateTimeParseException dtpe) {
                view.displayResult("Invalid input. Please enter the entry/exit date/time\nin " +
                        "the following format: dd-MM-yyyy HH:mm");
            }
        });
    }
}
