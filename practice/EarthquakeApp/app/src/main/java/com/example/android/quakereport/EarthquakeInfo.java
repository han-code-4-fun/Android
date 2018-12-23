package com.example.android.quakereport;

import java.text.DecimalFormat;

public class EarthquakeInfo
{
    private double mag;
    private String city;
    private String time;
    private double distance;
    private String direction;
    private DecimalFormat magFormat;


    public EarthquakeInfo(double mag,
                          String distance, String time)
    {
        magFormat = new DecimalFormat("00.0");
        this.mag = Double.parseDouble(magFormat.format(mag));

        String[] temp = extractFromDistanceInfo(distance);
        this.distance = Double.valueOf(temp[0]);
        this.direction = temp[1];
        this.city = temp[2];
        this.time = time;
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

    public String getDirection() {
        return direction;
    }

    private String[] extractFromDistanceInfo(String distance)
    {
        //1st String is distance, 2nd is direction, 3rd is the city name
        String[] output = new String[3];

        int index = distance.indexOf("km");
        int indexEnd = distance.indexOf("of ");
        //if the distance String has km information,
        //output both km, direction and city, otherwise
        //output 'Near the' which will be concanate before city later on
        if(index == -1)
        {
            output[0] = "-1";
            output[1] = "Near the";
            output[2] = distance;

        }else
        {
            //save the number
            output[0] = distance.substring(0,index);

            //save the direction after number before city name
            output[1] = distance.substring(index,indexEnd+3);

            //save city name
            output[2] = distance.substring(indexEnd+3);
        }

        return output;
    }


}
