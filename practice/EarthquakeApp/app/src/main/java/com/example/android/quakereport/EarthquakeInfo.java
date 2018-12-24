package com.example.android.quakereport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EarthquakeInfo
{
    private double mag;
    private double distance;
    private String direction;
    private String city;

    private String time;
    private String date;



    private String url;

    private DecimalFormat magFormat;
    private Date dateObj;
    static private SimpleDateFormat timeFormat  = new SimpleDateFormat("h:mm a");
    static private SimpleDateFormat dateFormat = new SimpleDateFormat("LLL DD,yyyy ");


    public EarthquakeInfo(double mag, String distance, long time, String inputURL)
    {
        //save to mag
        processMagnitude(mag);

        //save to distance, direction and city
        processDistanceInfo(distance);

        //save to time and date
        processUNIXTime(time);

        this.url = inputURL;
    }




    public double getMag() {
        return mag;
    }

    public double getDistance() {
        return distance;
    }

    public String getCity() {
        return city;
    }


    public String getTime() {
        return time;
    }

    public String getDate(){return date;}

    public String getDirection() {
        return direction;
    }

    public String getUrl() {
        return url;
    }

    //format magnitude
    private void processMagnitude(double magInput)
    {
        magFormat = new DecimalFormat("00.0");
        this.mag = Double.parseDouble(magFormat.format(magInput));
    }

    //extract distance, direction and city info from input
    private void processDistanceInfo(String distance)
    {

        String[] output = new String[3];

        int index = distance.indexOf("km");
        int indexEnd = distance.indexOf("of ");
        //if the distance String has km information,
        //save km, direction and city, otherwise
        //save 'Near the' which will be concanate before city later on
        if(index == -1)
        {
            this.distance = -1.0;
            this.direction = "Near the";
            this.city = distance;

        }else
        {
            //save to distance
            this.distance = Double.valueOf(distance.substring(0,index));

            //save the direction after number before city name
            this.direction = distance.substring(index,indexEnd+3);

            //save city name
            this.city = distance.substring(indexEnd+3);
        }

    }

    //convert from UNIX time to date
    private void processUNIXTime(long inputTime)
    {

        try
        {
            dateObj = new Date(inputTime);
            this.time = dateFormat.format(dateObj);
            this.date = timeFormat.format(dateObj);

        }catch(Exception e)
        {
            this.time = "Error parsing time";
            this.date = "Error parsing date"; ;
        }

    }



}
