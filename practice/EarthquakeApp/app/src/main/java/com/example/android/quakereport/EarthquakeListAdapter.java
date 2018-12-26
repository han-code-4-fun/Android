package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.util.List;


public class EarthquakeListAdapter extends ArrayAdapter<EarthquakeInfo> {

   // private int resourceID;

    public EarthquakeListAdapter(@NonNull Context context, int resourceID, @NonNull List<EarthquakeInfo> inputList) {
        super(context, resourceID, inputList);
        //this.resourceID = resourceID;
    }

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


        // **** IMPORTANT, need to convert the color
        // resource ID into a color integer value
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



        final EarthquakeInfo thisInfo = getItem(position);

        //get the textview for mag into a variable
        TextView magView = (TextView)listItem.findViewById(R.id.magViewID);

        //set mag info from current EarthquakeInfo instance
        magView.setText(String.valueOf(thisInfo.getMag()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(thisInfo.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //get the textview for distance into a variable
        TextView distanceView = (TextView)listItem.findViewById(R.id.distanceViewID);
        //set distance info from current EarthquakeInfo instance
        double distance = thisInfo.getDistance();
        if(distance == -1)
        {
            //if there is no distance, distanView will display 'Near the'
            distanceView.setText(thisInfo.getDirection());
        }else {

            distanceView.setText(distance+ String.valueOf(thisInfo.getDirection()));
        }


        //get the textview for city into a variable
        TextView cityView = (TextView)listItem.findViewById(R.id.cityViewID);

        //set city info from current EarthquakeInfo instance
        cityView.setText(thisInfo.getCity());


        //get the textview for time into a variable
        TextView timeView = (TextView)listItem.findViewById(R.id.timeViewID);

        //set time info from current EarthquakeInfo instance
        timeView.setText(thisInfo.getTime());

        //get the textview for time into a variable
        TextView dateView = (TextView)listItem.findViewById(R.id.dateViewID);

        //set time info from current EarthquakeInfo instance
        dateView.setText(thisInfo.getDate());



        //TODO, background colors of different magnitude cannot be shown, will come back to fix it( line 102)

        return listItem;
    }
}
