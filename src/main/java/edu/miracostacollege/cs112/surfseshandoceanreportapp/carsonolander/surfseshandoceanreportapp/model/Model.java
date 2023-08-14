package edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Model {

    // Creates binary file to store data of Surf Sessions and Ocean Reports
    public static final String BINARY_FILE = "BeachReports.dat";


    // Method to check if the binary has any date (is there data being persisted)
    public static boolean binaryFileHasData()
    {
        File binaryFile = new File(BINARY_FILE);
        return (binaryFile.exists() && binaryFile.length() > 5L);
    }

    // Puts the data from the binary file created above into an ObservableList
    public static ObservableList<Beach> populateListFromBinaryFile()
    {
        ObservableList<Beach> allBeachReports = FXCollections.observableArrayList();
        File binaryFile = new File(BINARY_FILE);
        if (binaryFileHasData()) {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                Beach[] tempArray = (Beach[]) fileReader.readObject();
                allBeachReports.addAll(tempArray);
                fileReader.close();
            } catch (Exception e) {
                System.err.println("Error opening file: " + BINARY_FILE + " for reading.\nCaused by: " + e.getMessage());
            }
        }
        return allBeachReports;
    }


    // Writes new data to the binary file
    public static boolean writeDataToBinaryFile(ObservableList<Beach> allBeachReportsList)
    {
        File binaryFile = new File(BINARY_FILE);
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            Beach[] tempArray = new Beach[allBeachReportsList.size()];
            allBeachReportsList.toArray(tempArray);
            fileWriter.writeObject(tempArray);
            fileWriter.close();
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Error writing binary file: " + BINARY_FILE + "\n" + e.getMessage());
            return false;
        }
    }


}
