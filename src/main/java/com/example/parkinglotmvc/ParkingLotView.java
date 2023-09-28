package com.example.parkinglotmvc;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ParkingLotView {
    // Buttons
    private Button calculateButton;
    private RadioButton longStayButton, shortStayButton, airportButton, multiStoreyButton;
    private ToggleGroup tg;
    private Tooltip longStayTip, shortStayTip, airportTip, multiStoreyTip;

    // Text/labels
    private Label entryLabel, exitLabel, resultLabel;
    private Text instructions;

    // Date/time fields
    private DatePicker entryPicker, exitPicker;
    private ComboBox<String> entryHoursBox, entryMinsBox, exitHoursBox, exitMinsBox;


    public ParkingLotView() {
        configureText();
        configureTooltips();
        configureButtons();
        configureDateTimeFields();
    }

    private void configureText() {
        entryLabel = new Label("Entry time: ");
        exitLabel = new Label("Exit time: ");
        resultLabel = new Label();
        instructions = new Text("Please enter the date/time you entered and exited\n" +
                "and select the parking lot type.");
    }

    private void configureTooltips() {
        longStayTip = new Tooltip("""
                Price: £2 per hour
                Max Daily Charge: £15
                No charge for first 30 minutes
                Minutes round up (i.e. 2 hours 15 minutes = £6 (3 hour charge))""");
        shortStayTip = new Tooltip("""
                Price: £2 per hour
                No charge for first 30 minutes
                4 hour stay limit - £50 per day fine + standard charge for >4 hours
                Minutes round up (i.e. 2 hours 15 minutes = £6 (3 hour charge))""");
        airportTip = new Tooltip("""
                Price: £7 per hour up to 7 hours
                £50 for >7 hours (per day)
                Minutes round up (i.e. 2 hours 15 minutes = £21 (3 hour charge))""");
        multiStoreyTip = new Tooltip("Price: £5 flat fee + £2 per hour add-on\n" +
                "Minutes round up (i.e. 2 hours 15 minutes = £6 (3 hour charge))");
    }

    private void configureDateTimeFields() {
        entryPicker = new DatePicker();
        exitPicker = new DatePicker();

        String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18","19", "20", "21", "22", "23"};
        String[] minutes = {":00",":05", ":10", ":15", ":20", ":25", ":30",":35", ":40",
                ":45", ":50", ":55"};

        entryHoursBox = new ComboBox<>(FXCollections.observableArrayList(hours));
        entryMinsBox = new ComboBox<>(FXCollections.observableArrayList(minutes));
        exitHoursBox = new ComboBox<>(FXCollections.observableArrayList(hours));
        exitMinsBox = new ComboBox<>(FXCollections.observableArrayList(minutes));
    }

    private void configureButtons() {
        calculateButton = new Button("Calculate Fee");
        longStayButton = new RadioButton("Long stay parking");
        shortStayButton = new RadioButton("Short stay parking");
        airportButton = new RadioButton("Airport parking");
        multiStoreyButton = new RadioButton("Multi-storey parking");
        tg = new ToggleGroup();

        longStayButton.setToggleGroup(tg);
        shortStayButton.setToggleGroup(tg);
        airportButton.setToggleGroup(tg);
        multiStoreyButton.setToggleGroup(tg);
        shortStayButton.setSelected(true);

        longStayButton.setTooltip(longStayTip);
        shortStayButton.setTooltip(shortStayTip);
        airportButton.setTooltip(airportTip);
        multiStoreyButton.setTooltip(multiStoreyTip);
    }

    public void configureLayout(Stage stage) {
        // Set up layout
        HBox entryHbox = new HBox(10, entryPicker, entryHoursBox, entryMinsBox);
        entryHbox.setAlignment(Pos.CENTER);
        HBox exitHbox = new HBox(10, exitPicker, exitHoursBox, exitMinsBox);
        exitHbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20, instructions, entryLabel, entryHbox, exitLabel,
                exitHbox, shortStayButton, longStayButton, airportButton,
                multiStoreyButton, calculateButton, resultLabel);
        vbox.setAlignment(Pos.CENTER);

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(0, 50, 0, 50));
        border.setCenter(vbox);

        Scene scene = new Scene(border, 500, 500);

        stage.setTitle("Parking Lot Fee Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public void displayResult(String result) {
        resultLabel.setText(result);
    }

    public LocalDate getEntryDate() {
        return entryPicker.getValue();
    }

    public LocalDate getExitDate() {
        return exitPicker.getValue();
    }
    public String getEntryTime(){
        return entryHoursBox.getValue() + entryMinsBox.getValue();
    }

    public String getExitTime(){
        return exitHoursBox.getValue() + exitMinsBox.getValue();
    }
    public Button getCalculateButton() {
        return calculateButton;
    }

    public String getSelectedLotType() {
        RadioButton selectedRadioButton = (RadioButton) tg.getSelectedToggle();
        return selectedRadioButton.getText();
    }
}

