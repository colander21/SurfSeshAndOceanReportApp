package edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.view;

import edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.controller.Controller;
import edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.model.OceanReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddOceanReportScene extends Scene {

    private Scene previousScene;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 350;
    private Controller controller = Controller.getInstance();

    private TextField dateTF = new TextField();
    private Label dateErrorLabel = new Label("Date is required.");
    private TextField timeTF = new TextField();
    private Label timeErrorLabel = new Label("Time is required.");
    private TextField locationTF = new TextField();
    private Label locationErrorLabel = new Label("Location is required.");
    private TextField airTempTF =  new TextField(); // degrees in Fahrenheit
    private Label airErrorLabel = new Label("Air Temperature is required.");
    private ComboBox<String> waterClarityCB; // clear, slightly clear, no visibility;
    private Label waterClarityErrorLabel = new Label("Water Clarity is required.");
    private TextField waterTempTF = new TextField();
    private Label waterTempErrorLabel = new Label("Water Temperature is required.");
    private ComboBox<String> weatherCB; // sunny , partial clouds, cloudy
    private Label weatherErrorLabel = new Label("Weather is required.");
    private ComboBox<String> dolphinsCB;
    private Label dolphinsErrorLabel = new Label("Dolphins Sighting is required.");

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");
    public AddOceanReportScene(Scene previousScene) {
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;

        ObservableList<String> waterClarityChoicesList =
                FXCollections.observableArrayList("Clear", "Slightly Clear", "No Visibility");
        ObservableList<String> weatherChoicesList =
                FXCollections.observableArrayList("Sunny", "Partial Clouds", "Cloudy");
        ObservableList <String> dolphinsChoicesList = FXCollections.observableArrayList("Yes","No");

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Date (MM/DD/YY): "), 0, 0);
        pane.add(dateTF, 1, 0);
        pane.add(dateErrorLabel, 2, 0);
        dateErrorLabel.setTextFill(Color.RED);
        dateErrorLabel.setVisible(false);

        pane.add(new Label("Time (ex. 10 am): "), 0, 1);
        pane.add(timeTF, 1, 1);
        pane.add(timeErrorLabel, 2, 1);
        timeErrorLabel.setTextFill(Color.RED);
        timeErrorLabel.setVisible(false);

        pane.add(new Label("Location: "), 0, 2);
        pane.add(locationTF, 1, 2);
        pane.add(locationErrorLabel, 2, 2);
        locationErrorLabel.setTextFill(Color.RED);
        locationErrorLabel.setVisible(false);

        pane.add(new Label("Air Temperature: "), 0, 3);
        pane.add(airTempTF, 1, 3);
        pane.add(airErrorLabel, 2, 3);
        airErrorLabel.setTextFill(Color.RED);
        airErrorLabel.setVisible(false);

        pane.add(new Label("Water Temperature (in F): "), 0, 4);
        pane.add(waterTempTF, 1, 4);
        pane.add(waterTempErrorLabel, 2, 4);
        waterTempErrorLabel.setTextFill(Color.RED);
        waterTempErrorLabel.setVisible(false);

        waterClarityCB = new ComboBox<>(waterClarityChoicesList);
        pane.add(new Label("Water Clarity: "), 0, 5);
        pane.add(waterClarityCB, 1, 5);
        pane.add(waterClarityErrorLabel, 2, 5);
        waterClarityErrorLabel.setTextFill(Color.RED);
        waterClarityErrorLabel.setVisible(false);

        weatherCB = new ComboBox<>(weatherChoicesList);
        pane.add(new Label("Weather: "), 0, 6);
        pane.add(weatherCB, 1, 6);
        pane.add(weatherErrorLabel, 2, 6);
        weatherErrorLabel.setTextFill(Color.RED);
        weatherErrorLabel.setVisible(false);

        dolphinsCB = new ComboBox<>(dolphinsChoicesList);
        pane.add(new Label("Dolphins Sighting: "), 0, 7);
        pane.add(dolphinsCB, 1, 7);
        pane.add(dolphinsErrorLabel, 2, 7);
        dolphinsErrorLabel.setTextFill(Color.RED);
        dolphinsErrorLabel.setVisible(false);

        pane.add(cancelButton, 0, 8);
        pane.add(saveButton, 1, 8);

        saveButton.setOnAction(e -> save());
        cancelButton.setOnAction(e -> goBackToPrevScene());

        this.setRoot(pane);
    }

    private void save() {
        String date, time, location, waterClarity, weather, dolphins;
        int airTemp = 0; int waterTemp = 0;

        date = dateTF.getText();
        dateErrorLabel.setVisible(date.isEmpty());

        time = timeTF.getText();
        timeErrorLabel.setVisible(time.isEmpty());

        location = locationTF.getText();
        locationErrorLabel.setVisible(location.isEmpty());

        waterClarity = waterClarityCB.getValue();
        waterClarityErrorLabel.setVisible(waterClarity ==  null);

        weather = weatherCB.getValue();
        weatherErrorLabel.setVisible(weather == null);

        dolphins = dolphinsCB.getValue();
        dolphinsErrorLabel.setVisible(dolphins == null);

        try {
            airTemp = Integer.parseInt(airTempTF.getText());
            airErrorLabel.setVisible(airTemp == 0);
        }
        catch (NumberFormatException e) {  airErrorLabel.setVisible(true); }

        try {
            waterTemp = Integer.parseInt(waterTempTF.getText());
            waterTempErrorLabel.setVisible(waterTemp == 0);
        }
        catch (NumberFormatException e) {  waterTempErrorLabel.setVisible(true); }

        if (dateErrorLabel.isVisible() || timeErrorLabel.isVisible() || locationErrorLabel.isVisible()
            || waterClarityErrorLabel.isVisible() || weatherErrorLabel.isVisible() || dolphinsErrorLabel.isVisible()
            || airErrorLabel.isVisible() || waterTempErrorLabel.isVisible())
            return;

        Controller.getInstance().addBeachReport(new OceanReport(date,location, time, airTemp, waterClarity, waterTemp, weather, dolphins));

        goBackToPrevScene();
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Beach Reports", previousScene);
    }
}
