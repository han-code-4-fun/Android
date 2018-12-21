package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {


        public MyFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new NumbersFragment();
            } else if (position == 1){
                return new FamilyFragment();
            } else if (position == 2){
                return new ColorsFragment();
            }else {
                return new PhrasesFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Numbers";
            } else if (position == 1){
                return "Families";
            } else if (position == 2){
                return "Colors";
            }else {
                return "Phrases";
            }

        }

        @Override
        public int getCount() {
            return 4;
        }



}
