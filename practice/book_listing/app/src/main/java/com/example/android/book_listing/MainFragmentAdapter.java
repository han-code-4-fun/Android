package com.example.android.book_listing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentAdapter extends FragmentPagerAdapter {

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i == 0){
            return new MainFragment();
        }else
        {
            return new SearchFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
