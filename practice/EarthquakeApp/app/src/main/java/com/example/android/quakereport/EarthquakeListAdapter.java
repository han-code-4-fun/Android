package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import com.example.android.quakereport.data.EarthquakeInfo;

import java.util.List;


public class EarthquakeListAdapter extends ArrayAdapter<EarthquakeInfo> {


    public EarthquakeListAdapter(@NonNull Context context, int resourceID, @NonNull List<EarthquakeInfo> inputList) {
        super(context, resourceID, inputList);

    }

    //return different color based on earthquake value
    private int getMagnitudeColor(double magnitude)
    {
        int outputColorID = 0;
        int magFloor = (int)Math.floor(magnitude);

        switch (magFloor)
        {
            case 0:
            case 1:
                outputColorID = R.color.magnitude1;
                break;
            case 2:
                outputColorID = R.color.magnitude2;
                break;
            case 3:
                outputColorID = R.color.magnitude3;
                break;
            case 4:
                outputColorID = R.color.magnitude4;
                break;
            case 5:
                outputColorID = R.color.magnitude5;
                break;
            case 6:
                outputColorID = R.color.magnitude6;
                break;
            case 7:
                outputColorID = R.color.magnitude7;
                break;
            case 8:
                outputColorID = R.color.magnitude8;
                break;
            case 9:
                outputColorID = R.color.magnitude9;
                break;
            default:
                outputColorID = R.color.magnitude10plus;
                break;



        }

        return   ContextCompat.getColor(getContext(), outputColorID);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
        {
            // **** important, I should not name the resource file in any Capital letter anymore
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }


        //get current EarthquakeInfo object
        EarthquakeInfo thisInfo = getItem(position);

        TextView magView = (TextView)listItem.findViewById(R.id.magViewID);

        magView.setText(String.valueOf(thisInfo.getMag()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();

        // save the background color in a variable, based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(thisInfo.getMag());

        magnitudeCircle.setColor(magnitudeColor);


        TextView distanceView = (TextView)listItem.findViewById(R.id.distanceViewID);

        double distance = thisInfo.getDistance();
        if(distance == -1)
        {
            //if there is no distance, distance TextView will display 'Near the'
            distanceView.setText(thisInfo.getDirection());
        }else {

            distanceView.setText(distance+ String.valueOf(thisInfo.getDirection()));
        }


        TextView cityView = (TextView)listItem.findViewById(R.id.cityViewID);

        cityView.setText(thisInfo.getCity());

        TextView timeView = (TextView)listItem.findViewById(R.id.timeViewID);

        timeView.setText(thisInfo.getTime());

        TextView dateView = (TextView)listItem.findViewById(R.id.dateViewID);

        dateView.setText(thisInfo.getDate());

        //return current item in the list after setting all the property
        return listItem;
    }
}
