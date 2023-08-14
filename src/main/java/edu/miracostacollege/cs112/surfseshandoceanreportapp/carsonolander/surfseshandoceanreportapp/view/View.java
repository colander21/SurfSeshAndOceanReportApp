package edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.view;

import edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.controller.Controller;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class View extends Application {

    /**
     * Starts the application by setting the stage (window) with a custom icon, then navigating
     * to the first scene, which happens to be the MainScene for this application.
     *
     * @param primaryStage The primary stage (window)
     * @throws Exception if one occurs during startup.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Changing the icon (top left of stage) to custom icon (saved in resources folder)
        primaryStage.getIcons().add(new Image("wave.jpg"));
        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Beach Reports", new MainScene());
    }

    @Override
    public void stop() throws Exception {
        Controller.getInstance().saveData();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
