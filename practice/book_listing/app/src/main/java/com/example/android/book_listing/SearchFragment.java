package com.example.android.book_listing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SearchFragment extends Fragment
{
    static private int maxResult;
    static private String bookName;
    TextView quantity;
    TextView searchBox;



    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search, container,false);

        quantity = rootView.findViewById(R.id.quantityID);

        searchBox = rootView.findViewById(R.id.searchView);

        return rootView;

    }

    public void display()
    {
        quantity.setText(String.valueOf(maxResult));
    }

    public void increment()
    {
        maxResult = maxResult + 1;
        display();
    }
    public void decrement()
    {
        maxResult = maxResult - 1;
        display();
    }

    public void search()
    {
        String temp = searchBox.getText().toString();

    }




}
