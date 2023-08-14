package edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.model;

import java.io.Serializable;

public class SurfSesh extends Beach implements Serializable, Comparable<Beach> {

    private String mSwellDirection;  // N S E W or combination ex. SW
    private int mWaveSize;  // in feet
    private int mWindSpeed; // in knots
    private String mWindDirection;  // N S E W or combination ex. SW
    private String mTide;  // Low, Mid or High tide
    private String mCrowded; // yes or no
    private int mRating;  // Out of 10 stars

    public SurfSesh(String date, String location, String time, String swellDirection, int waveSize, int windSpeed, String windDirection, String tide, String crowded, int rating) {
        super(date, location, time);
        mSwellDirection = swellDirection;
        mWaveSize = waveSize;
        mWindSpeed = windSpeed;
        mWindDirection = windDirection;
        mTide = tide;
        mCrowded = crowded;
        mRating = rating;
    }

    public String getSwellDirection() {
        return mSwellDirection;
    }

    public void setSwellDirection(String swellDirection) {
        mSwellDirection = swellDirection;
    }

    public int getWaveSize() {
        return mWaveSize;
    }

    public void setWaveSize(int waveSize) {
        mWaveSize = waveSize;
    }

    public int getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        mWindSpeed = windSpeed;
    }

    public String getWindDirection() {
        return mWindDirection;
    }

    public void setWindDirection(String windDirection) {
        mWindDirection = windDirection;
    }

    public String getTide() {
        return mTide;
    }

    public void setTide(String tide) {
        mTide = tide;
    }

    public String crowded() {
        return mCrowded;
    }

    public void setCrowded(String crowded) {
        mCrowded = crowded;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        mRating = rating;
    }

    @Override
    public String toString() {
        String crowdedLabel;
        if (mCrowded.equals("Yes"))
            crowdedLabel = "Crowded";
        else
            crowdedLabel = "Not Crowded";
        return "Surf Session [" +
                "Date: " + mDate +
                ", Location: " + mLocation +
                ", Time: " + mTime +
                ", Swell Direction: " + mSwellDirection +
                ", Wave Size: " + mWaveSize +
                ", Wind Speed: " + mWindSpeed +
                ", Wind Direction: " + mWindDirection +
                ", Tide: " + mTide +
                ", " + crowdedLabel +
                ", Rating: " + mRating +
                " ]";
    }

    @Override
    public int compareTo(Beach o) {
        if (o instanceof SurfSesh) {
            SurfSesh other = (SurfSesh) o;
            int dateComp = other.mDate.compareTo(this.mDate);
            if (dateComp != 0)
                return dateComp;
            int locationComp = this.mLocation.compareTo(other.mLocation);
            if (locationComp != 0)
                return locationComp;
            int ratingComp = this.mRating - other.mRating;
            if (ratingComp != 0)
                return ratingComp;
            int waveSizeComp = this.mWaveSize - other.mWaveSize;
            if (waveSizeComp != 0)
                return waveSizeComp;
            int timeComp = this.mTime.compareTo(other.mTime);
            if (timeComp != 0)
                return timeComp;
        }
        return 0;
    }
}
