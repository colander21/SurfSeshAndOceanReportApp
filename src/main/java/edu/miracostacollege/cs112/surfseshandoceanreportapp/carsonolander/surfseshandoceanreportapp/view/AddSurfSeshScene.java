package edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.view;

import edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.controller.Controller;
import edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.model.SurfSesh;
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

public class AddSurfSeshScene extends Scene {

    private Scene previousScene;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private Controller controller = Controller.getInstance();

    private TextField dateTF = new TextField();
    private Label dateErrorLabel = new Label("Date is required.");
    private TextField timeTF = new TextField();
    private Label timeErrorLabel = new Label("Time is required.");
    private TextField locationTF = new TextField();
    private Label locationErrorLabel = new Label("Location is required.");
    private TextField waveSizeTF = new TextField();
    private Label waveSizeErrorLabel = new Label("Wave Size is required.");
    private TextField swellTF = new TextField();
    private Label swellErrorLabel = new Label("Swell is required.");
    private ComboBox<String> tideCB;
    private Label tideErrorLabel = new Label("Tide is required.");
    private TextField windSpeedTF = new TextField();
    private Label windSpeedErrorLabel = new Label("Wind speed is required.");
    private TextField windDirectionTF = new TextField();
    private Label windDirectionErrorLabel = new Label("Wind direction is required.");
    private ComboBox<String> crowdedCB;
    private Label crowdedErrorLabel = new Label("Crowded is a required input.");
    private ComboBox<Integer> ratingCB;
    private Label ratingErrorLabel = new Label("Rating is required.");
    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

    public AddSurfSeshScene(Scene previousScene) {
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;

        ObservableList<String> tideChoicesList =
                FXCollections.observableArrayList("High tide", "Mid tide", "Low tide");
        ObservableList <String> crowdedChoicesList = FXCollections.observableArrayList("Yes","No");
        ObservableList <Integer> ratingChoicesList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);


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

        pane.add(new Label("Wave Size (in ft): "), 0, 3);
        pane.add(waveSizeTF, 1, 3);
        pane.add(waveSizeErrorLabel, 2, 3);
        waveSizeErrorLabel.setTextFill(Color.RED);
        waveSizeErrorLabel.setVisible(false);

        pane.add(new Label("Swell Direction: "), 0, 4);
        pane.add(swellTF, 1, 4);
        pane.add(swellErrorLabel, 2, 4);
        swellErrorLabel.setTextFill(Color.RED);
        swellErrorLabel.setVisible(false);

        pane.add(new Label("Wind Speed (in kts): "), 0, 5);
        pane.add(windSpeedTF, 1, 5);
        pane.add(windSpeedErrorLabel, 2, 5);
        windSpeedErrorLabel.setTextFill(Color.RED);
        windSpeedErrorLabel.setVisible(false);

        pane.add(new Label("Wind Direction: "), 0, 6);
        pane.add(windDirectionTF, 1, 6);
        pane.add(windDirectionErrorLabel, 2, 6);
        windDirectionErrorLabel.setTextFill(Color.RED);
        windDirectionErrorLabel.setVisible(false);

        tideCB= new ComboBox<>(tideChoicesList);
        pane.add(new Label("Tide: "), 0, 7);
        pane.add(tideCB, 1, 7);
        pane.add(tideErrorLabel, 2, 7);
        tideErrorLabel.setTextFill(Color.RED);
        tideErrorLabel.setVisible(false);

        crowdedCB = new ComboBox<>(crowdedChoicesList);
        pane.add(new Label("Crowded: "), 0, 8);
        pane.add(crowdedCB, 1, 8);
        pane.add(crowdedErrorLabel, 2, 8);
        crowdedErrorLabel.setTextFill(Color.RED);
        crowdedErrorLabel.setVisible(false);

        ratingCB = new ComboBox<>(ratingChoicesList);
        pane.add(new Label("Rating: "), 0, 9);
        pane.add(ratingCB, 1, 9);
        pane.add(ratingErrorLabel, 2, 9);
        ratingErrorLabel.setTextFill(Color.RED);
        ratingErrorLabel.setVisible(false);

        pane.add(cancelButton, 0, 10);
        pane.add(saveButton, 1, 10);

        saveButton.setOnAction(e -> save());
        cancelButton.setOnAction(e -> goBackToPrevScene());
        this.setRoot(pane);
    }

    private void save() {

        String date, time, location, swell, tide, crowded, windDirection;
        int windSpeed = -1 , rating = -1, waveSize = -1;

        date = dateTF.getText();
        dateErrorLabel.setVisible(date.isEmpty());

        time = timeTF.getText();
        timeErrorLabel.setVisible(time.isEmpty());

        location = locationTF.getText();
        locationErrorLabel.setVisible(location.isEmpty());

        swell = swellTF.getText();
        swellErrorLabel.setVisible(swell.isEmpty());

        tide = tideCB.getValue();
        tideErrorLabel.setVisible(tide == null);

        crowded = crowdedCB.getValue();
        crowdedErrorLabel.setVisible(crowded == null);

        try {
            waveSize = Integer.parseInt(waveSizeTF.getText());
            waveSizeErrorLabel.setVisible(waveSize < 0);
        }
        catch (NumberFormatException e) {  waveSizeErrorLabel.setVisible(true); }

        try {
            windSpeed = Integer.parseInt(windSpeedTF.getText());
            windSpeedErrorLabel.setVisible(windSpeed < 0);
        }
        catch (NumberFormatException e) {  windSpeedErrorLabel.setVisible(true); }

        windDirection = windDirectionTF.getText();
        windDirectionErrorLabel.setVisible(windDirection.isEmpty());

        if (ratingCB.getSelectionModel().getSelectedIndex() == -1)
            ratingErrorLabel.setVisible(true);
        else {
            ratingErrorLabel.setVisible(false);
            rating = ratingCB.getValue();
        }


        if (dateErrorLabel.isVisible() || timeErrorLabel.isVisible() || locationErrorLabel.isVisible()
                || swellErrorLabel.isVisible() || windSpeedErrorLabel.isVisible()
                || windDirectionErrorLabel.isVisible() || tideErrorLabel.isVisible()
                || crowdedErrorLabel.isVisible() || ratingErrorLabel.isVisible() || waveSizeErrorLabel.isVisible())
            return;

        Controller.getInstance().addBeachReport(new SurfSesh(date, location, time, swell, waveSize, windSpeed, windDirection, tide, crowded, rating));

        goBackToPrevScene();
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Beach Reports", previousScene);
    }

}
