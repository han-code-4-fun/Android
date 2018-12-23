package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EarthquakeListAdapter extends ArrayAdapter<EarthquakeInfo> {

   // private int resourceID;

    public EarthquakeListAdapter(@NonNull Context context, int resourceID, @NonNull List<EarthquakeInfo> inputList) {
        super(context, resourceID, inputList);
        //this.resourceID = resourceID;
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

        EarthquakeInfo thisInfo = getItem(position);

        //get the textview for mag into a variable
        TextView magView = (TextView)listItem.findViewById(R.id.magViewID);

        //set mag info from current EarthquakeInfo instance
        magView.setText(String.valueOf(thisInfo.getMag()));

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

        TextView timeView = (TextView)listItem.findViewById(R.id.timeViewID);

        timeView.setText(thisInfo.getTime());



        return listItem;
    }
}
