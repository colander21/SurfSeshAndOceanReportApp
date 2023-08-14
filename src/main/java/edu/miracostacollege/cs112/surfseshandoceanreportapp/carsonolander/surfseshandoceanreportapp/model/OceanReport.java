package edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.model;

import java.io.Serializable;

public class OceanReport extends Beach implements Serializable, Comparable<Beach> {

    private int mAirTemp; // degrees in Fahrenheit
    private String mWaterClarity; // clear, slightly clear, no visibility;
    private int mWaterTemp;
    private String mWeather; // sunny , partial clouds, cloudy
    private String mDolphins;

    public OceanReport(String date, String location, String time, int airTemp, String waterClarity, int waterTemp, String weather, String dolphins) {
        super(date, location, time);
        mAirTemp = airTemp;
        mWaterClarity = waterClarity;
        mWaterTemp = waterTemp;
        mWeather = weather;
        mDolphins = dolphins;
    }

    public int getAirTemp() {
        return mAirTemp;
    }

    public void setAirTemp(int airTemp) {
        mAirTemp = airTemp;
    }

    public String getWaterClarity() {
        return mWaterClarity;
    }

    public void setWaterClarity(String waterClarity) {
        mWaterClarity = waterClarity;
    }

    public int getWaterTemp() {
        return mWaterTemp;
    }

    public void setWaterTemp(int waterTemp) {
        mWaterTemp = waterTemp;
    }

    public String getWeather() {
        return mWeather;
    }

    public void setWeather(String weather) {
        mWeather = weather;
    }

    public String getDolphins() {
        return mDolphins;
    }

    public void setDolphins(String dolphins) {
        mDolphins = dolphins;
    }

    @Override
    public String toString() {
        String dolphinsLabel;
        if (mDolphins.equals("Yes"))
            dolphinsLabel = "Dolphins Sightings";
        else
            dolphinsLabel = "No Dolphins";
        return "Ocean Report [" +
                "Date: " + mDate +
                ", Location: " + mLocation +
                ", Time: " + mTime +
                ", Water Clarity: " + mWaterClarity +
                ", Water Temperature: " + mWaterTemp + " degrees F" +
                ", Air Temperature: " + mAirTemp + " degrees F " +
                ", Weather: " + mWeather +
                ", " + dolphinsLabel +
                " ]";
    }

    @Override
    public int compareTo(Beach o) {
        if (o instanceof OceanReport) {
            OceanReport other = (OceanReport) o;
            int dateComp = other.mDate.compareTo(this.mDate);
            if (dateComp != 0)
                return dateComp;
            int locationComp = this.mLocation.compareTo(other.mLocation);
            if (locationComp != 0)
                return locationComp;
            int clarityComp = this.mWaterClarity.compareTo(other.mWaterClarity);
            if (clarityComp != 0)
                return clarityComp;
            int timeComp = this.mTime.compareTo(other.mTime);
            if (timeComp != 0)
                return timeComp;
        }
        return 0;
    }
}
