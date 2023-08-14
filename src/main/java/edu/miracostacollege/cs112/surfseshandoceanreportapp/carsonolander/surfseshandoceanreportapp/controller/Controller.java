package edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.controller;

import edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.model.Beach;
import edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

    private static Controller theInstance;
    private ObservableList<Beach> mAllBeachReportsList;
    private ObservableList<Beach> mFilteredBeachReportsList;
    private ObservableList<String> mDistinctLocation;

    public static Controller getInstance() {
        if (theInstance == null) {
            theInstance = new Controller();

            theInstance.mAllBeachReportsList = Model.populateListFromBinaryFile();

            FXCollections.sort(theInstance.mAllBeachReportsList);

            theInstance.mDistinctLocation = theInstance.initializeDistinctLocation();
            // FXCollections.sort(theInstance.mDistinctLocation);

            theInstance.mFilteredBeachReportsList = FXCollections.observableArrayList();
        }
        return theInstance;
    }

    public ObservableList<Beach> getAllBeachReports() {
        return mAllBeachReportsList;
    }

    public void addBeachReport(Beach beachReport)
    {
        mAllBeachReportsList.add(beachReport);
        FXCollections.sort(mAllBeachReportsList);
        addLocation(beachReport.getLocation());
        FXCollections.sort(mDistinctLocation);
    }

    private void addLocation(String location)
    {
        if (!mDistinctLocation.contains(location))
            mDistinctLocation.add(location);
    }

    public void saveData() {
        Model.writeDataToBinaryFile(mAllBeachReportsList);
    }

    public ObservableList<String> getDistinctLocation() {
        return mDistinctLocation;
    }

    private ObservableList<String> initializeDistinctLocation()
    {
        ObservableList<String> distinctLocation = FXCollections.observableArrayList();
        distinctLocation.add("");

        for (Beach v : mAllBeachReportsList)
        {
            if (!distinctLocation.contains(v.getLocation()))
                distinctLocation.add(v.getLocation());
        }

        return distinctLocation;
    }

    public ObservableList<Beach> filter(String location) {
        mFilteredBeachReportsList.clear();
        for (Beach v : mAllBeachReportsList)
        {
            if ("".equals(location) || v.getLocation().equals(location))
                mFilteredBeachReportsList.add(v);
        }
        return mFilteredBeachReportsList;
    }

}
