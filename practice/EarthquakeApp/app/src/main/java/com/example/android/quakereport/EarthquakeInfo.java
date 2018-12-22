package com.example.android.quakereport;

public class EarthquakeInfo
{
    private double mag;
    private String city;
    private String time;

    public EarthquakeInfo(double mag, String city, String time)
    {
        this.mag = mag;
        this.city = city;
        this.time = time;
    }

    public double getMag() {
        return mag;
    }


    public String getCity() {
        return city;
    }


    public String getTime() {
        return time;
    }


}
