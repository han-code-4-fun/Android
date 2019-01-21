package asynctask;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import Data.City;
import utils.JSONUtils;

public class CityAsyncTask extends AsyncTask<Void, Void, ArrayList<City>> {
    private String countryCode;
    private ArrayAdapter<String> citiesAdapter;

    public CityAsyncTask(String inputCountryCode,  ArrayAdapter<String> citiesAdapter){
        countryCode = inputCountryCode;
        this.citiesAdapter = citiesAdapter;
    }

    @Override
    protected ArrayList<City> doInBackground(Void... input) {
        return JSONUtils.getCities(countryCode);
    }

    @Override
    protected void onPostExecute(ArrayList<City> cities) {
        super.onPostExecute(cities);
        if(cities.size()>0)
        {
            refreshCitiesList(cities);
        }
    }

    private void refreshCitiesList(ArrayList<City> cities)
    {
        citiesAdapter.clear();
        for (City c :
                cities) {
            citiesAdapter.add(c.getCity());
        }
        citiesAdapter.notifyDataSetChanged();
    }
}
