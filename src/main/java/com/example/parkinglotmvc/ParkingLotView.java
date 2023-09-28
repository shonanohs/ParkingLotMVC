package com.example.parkinglotmvc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ParkingLotView {
    private TextField entryField;
    private TextField exitField;
    private Button calculateButton;
    private RadioButton longStayButton;
    private RadioButton shortStayButton;
    private RadioButton airportButton;
    private RadioButton multiStoreyButton;
    private ToggleGroup tg;
    private Label entryLabel;
    private Label exitLabel;
    private Label resultLabel;
    private Text instructions;
    private Tooltip longStayTip;
    private Tooltip shortStayTip;
    private Tooltip airportTip;
    private Tooltip multiStoreyTip;

    public ParkingLotView() {

        initialiseUIComponents();
    }

    private void initialiseUIComponents() {
        // Create UI Elements
        entryField = new TextField();
        exitField = new TextField();

        calculateButton = new Button("Calculate Fee");
        longStayButton = new RadioButton("Long stay parking");
        shortStayButton = new RadioButton("Short stay parking");
        airportButton = new RadioButton("Airport parking");
        multiStoreyButton = new RadioButton("Multi-storey parking");
        tg = new ToggleGroup();

        entryLabel = new Label("Entry time (dd-MM-yyyy HH:mm): ");
        exitLabel = new Label("Exit time (dd-MM-yyyy HH:mm): ");
        resultLabel = new Label();

        instructions = new Text("Please enter the date/time you entered and exited\nand select the parking lot type.");

        longStayTip = new Tooltip("""
                Price: £2 per hour
                Max Daily Charge: £15
                No charge for first 30 minutes
                Minutes round up (i.e. 2 hours 1 minute = £6 (3 hour charge))""");
        shortStayTip = new Tooltip("""
                Price: £2 per hour
                No charge for first 30 minutes
                4 hour stay limit - £50 per day fine + standard charge for >4 hours
                Minutes round up (i.e. 2 hours 1 minute = £6 (3 hour charge))""");
        airportTip = new Tooltip("""
                Price: £7 per hour up to 7 hours
                £50 for >7 hours (per day)
                Minutes round up (i.e. 2 hours 1 minute = £21 (3 hour charge))""");
        multiStoreyTip = new Tooltip("Price: £5 flat fee + £2 per hour add-on\n" +
                "Minutes round up (i.e. 2 hours 1 minute = £6 (3 hour charge))");
    }

    private void configureButtons() {
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
        configureButtons();

        // Set up layout
        VBox vbox = new VBox(10, instructions, entryLabel, entryField, exitLabel,
                exitField, shortStayButton, longStayButton, airportButton,
                multiStoreyButton, calculateButton, resultLabel);
        vbox.setAlignment(Pos.CENTER);

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(0, 50, 0, 50));
        border.setCenter(vbox);

        Scene scene = new Scene(border, 400, 400);

        stage.setTitle("Parking Lot Fee Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public void displayResult(String result) {
        resultLabel.setText(result);
    }

    public String getEntryField() {
        return entryField.getText();
    }

    public String getExitField() {
        return exitField.getText();
    }

    public Button getCalculateButton() {
        return calculateButton;
    }

    public String getSelectedLotType() {
        RadioButton selectedRadioButton = (RadioButton) tg.getSelectedToggle();
        return selectedRadioButton.getText();
    }
}

