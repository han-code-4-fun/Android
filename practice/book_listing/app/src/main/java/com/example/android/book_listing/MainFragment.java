package com.example.android.book_listing;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;
import android.net.Uri;
import android.widget.ProgressBar;


import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements android.app.LoaderManager.LoaderCallbacks<List<Book>>
{
    private ListView listView;

    private BookListAdapter myAdapter;

    private ProgressBar progressBar;



    private final static String url =
            "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=40";

    public MainFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //todo Loaders is deprecated in API 28, need to learn and
        //todo  replace this method with  ViewModel, LiveData and Observer
        //todo do it after completion future advanced courses
        getLoaderManager().initLoader(0,null, ).forceLoad();


    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listview,container,false);

        progressBar = rootView.findViewById(R.id.progressView);


        listView = rootView.findViewById(R.id.listView);

        myAdapter = new BookListAdapter(getContext(), 0, new ArrayList<Book>());


        //listView.setEmptyView(emptyView);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book thisBook = myAdapter.getItem(position);

                Intent openUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(thisBook.getUrl()));
                startActivity(openUrl);

            }
        });


        return rootView;
    }

    @Override
    public android.content.Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        //Log.i(test,"gets oncreateloader");
        return new BookLoader(this.getContext(), url);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<Book>> loader, List<Book> data) {
        progressBar.setVisibility(View.GONE);

        //Log.i(test, "Gets onloadfinished");
        PopulateDataToListView(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<Book>> loader) {
        myAdapter.clear();
    }



    public void PopulateDataToListView(List<Book> data)
    {
        // Clear the adapter of previous earthquake data
        myAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            myAdapter.addAll(data);
            myAdapter.notifyDataSetChanged();
        }
    }
}
