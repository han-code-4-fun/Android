package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat
    implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_general);
        SharedPreferences sp =  getPreferenceScreen().getSharedPreferences();
        PreferenceScreen ps =getPreferenceScreen();
        int count = ps.getPreferenceCount();
        for (int i = 0; i < count; i++) {
            Preference p =ps.getPreference(i);
            if(!(p instanceof CheckBoxPreference)){
                String value = sp.getString(p.getKey(),"");
                setPreferenceSummary(p, value);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p = findPreference(key);
        if(p != null){
            if(!(p instanceof CheckBoxPreference)){
                setPreferenceSummary(p, sharedPreferences.getString(key,""));
            }
        }
    }

    private void setPreferenceSummary(Preference p, Object value){
        String stringValue = value.toString();
        String key = p.getKey();

        if(p instanceof ListPreference){
            ListPreference listPreference =  (ListPreference) p;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if(prefIndex>=0){
                p.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }else{
            p.setSummary(stringValue);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().
                getSharedPreferences().
                    registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().
                getSharedPreferences().
                    unregisterOnSharedPreferenceChangeListener(this);
    }
}
