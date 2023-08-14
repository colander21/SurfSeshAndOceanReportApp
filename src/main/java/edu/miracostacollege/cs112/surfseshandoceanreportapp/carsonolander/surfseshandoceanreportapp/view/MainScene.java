package edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.view;

import edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.controller.Controller;
import edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.model.Beach;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class MainScene extends Scene {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private ImageView BeachReportsIV = new ImageView();
    private ComboBox<String> locationCB;
    private ListView<Beach> BeachReportsLV = new ListView<>();
    private Button addSurfSeshButton = new Button("+ Add Surf Session");
    private Button addOceanReportButton = new Button("+ Add Ocean Report");
    private Button removeButton = new Button("- Remove Beach Report");
    private Controller controller = Controller.getInstance();
    private ObservableList<Beach> BeachReportsList;
    private Beach selectedBeachReport;


    public MainScene() {
        super(new GridPane(), WIDTH, HEIGHT);

        BeachReportsIV.setImage(new Image("crashing_wave.jpg")); // TODO: causing issue image doesn't show up
        BeachReportsIV.setFitWidth(400);
        BeachReportsIV.setFitHeight(266.5);
        BeachReportsList = controller.getAllBeachReports();
        BeachReportsLV.setItems(BeachReportsList);
        BeachReportsLV.setPrefWidth(WIDTH);

        BeachReportsLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelectedBeachReport(newVal));

        locationCB = new ComboBox<>(controller.getDistinctLocation());
        locationCB.getSelectionModel().select(0);
        locationCB.setOnAction(e -> filter());

        addSurfSeshButton.setOnAction(e -> addSurfSesh());
        addOceanReportButton.setOnAction(e -> addOceanReport());
        removeButton.setDisable(true);
        removeButton.setOnAction(e -> removeBeachReport());

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));
        pane.add(BeachReportsIV, 0, 0, 2, 1);

        HBox hBox2 = new HBox();
        pane.add(hBox2,0,1);
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.getChildren().add(new Label("Location:"));
        hBox2.getChildren().add(locationCB);
        //pane.add(new Label("Location:"), 0, 1);
       //pane.add(locationCB, 1, 1);

        HBox hBox = new HBox();
        pane.add(hBox,0,2);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().add(addSurfSeshButton);
        hBox.getChildren().add(addOceanReportButton);

        pane.add(BeachReportsLV, 0, 4, 2, 1);
        pane.add(removeButton, 0, 5, 2, 1);

        this.setRoot(pane);
    }

    private void updateSelectedBeachReport(Beach newVal) {
        selectedBeachReport = newVal;
        removeButton.setDisable(selectedBeachReport == null);
    }

    private void removeBeachReport() {
        if (selectedBeachReport == null)
            return;
        BeachReportsList.remove(selectedBeachReport);
        controller.saveData();
        BeachReportsLV.getSelectionModel().select(-1);
    }

    private void addOceanReport() {
        ViewNavigator.loadScene("Add Ocean Report Scene", new AddOceanReportScene(this));
    }

    private void addSurfSesh() {
        ViewNavigator.loadScene("Add Surf Session Scene", new AddSurfSeshScene(this));
    }

    private void filter() {
        String location = locationCB.getSelectionModel().getSelectedItem();

        BeachReportsList = controller.filter(location);
        BeachReportsLV.setItems(BeachReportsList);
    }
}
