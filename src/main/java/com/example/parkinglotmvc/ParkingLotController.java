package com.example.parkinglotmvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParkingLotController {
    private IParkingLot parkingLot;
    private ParkingLotFactory factory = new ParkingLotFactory();
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
                // Parse date/time inputs as LocalDateTime object
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalDate entryDate = view.getEntryDate();
                LocalTime entryTime = LocalTime.parse(view.getEntryTime(), formatter);
                LocalDate exitDate = view.getExitDate();
                LocalTime exitTime = LocalTime.parse(view.getExitTime(), formatter);

                LocalDateTime entryDateTime = entryTime.atDate(entryDate);
                LocalDateTime exitDateTime = exitTime.atDate(exitDate);

                if (exitDateTime.isBefore(entryDateTime)) {
                    view.displayResult("Exit date/time cannot be before entry date/time.");
                    return;
                }

                // Calculate duration between entry/exit DateTimes
                ticket = new Ticket(entryDateTime, exitDateTime);
                ticket.calculateDuration();

                // Calculate fee for given parking lot type
                parkingLot = factory.createParkingLot(view.getSelectedLotType());
                String fee = String.valueOf(parkingLot.calculateFee(ticket));

                view.displayResult("Parking fee = £" + fee);
            }
            catch (DateTimeParseException dtpe) {
                view.displayResult("Invalid input. Please check date/time fields are selected.");
            }
        });
    }
}
