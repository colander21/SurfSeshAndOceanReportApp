package edu.miracostacollege.cs112.surfseshandoceanreportapp.carsonolander.surfseshandoceanreportapp.model;

import java.io.Serializable;

public abstract class Beach implements Serializable, Comparable<Beach>{

    protected String mDate;
    protected String mLocation;
    protected String mTime;

    protected Beach(String date, String location, String time)
    {
        mDate = date;
        mLocation = location;
        mTime = time;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    @Override
    public String toString() {
        return "Beach[" +
                "Date=" + mDate +
                ", Location=" + mLocation +
                ", Time=" + mTime +
                ']';
    }

    @Override
    public int compareTo(Beach other) {
        int locationComp = this.mLocation.compareTo(other.mLocation);
        if (locationComp != 0)
            return locationComp;
        int timeComp = this.mTime.compareTo(other.mTime);
        if (timeComp != 0)
            return timeComp;
        return other.mDate.compareTo(this.mDate);
    }
}
