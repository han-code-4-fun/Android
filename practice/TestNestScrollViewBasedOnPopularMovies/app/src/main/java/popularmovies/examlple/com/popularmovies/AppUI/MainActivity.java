package popularmovies.examlple.com.popularmovies.AppUI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import popularmovies.examlple.com.popularmovies.Data.Movie;
import popularmovies.examlple.com.popularmovies.AppRecyclerView.MyAdapter;
import popularmovies.examlple.com.popularmovies.MyAsyncTask.MovieLoadAsyncTask;
import popularmovies.examlple.com.popularmovies.R;
import popularmovies.examlple.com.popularmovies.Utils.MyURL;

public class MainActivity extends AppCompatActivity
        implements  MyAdapter.CustomListItemClickListener {

    private RecyclerView recyclerView;

    private MyAdapter myAdapter;

    private ProgressBar progressBar;

    private List<Movie> movieList;

    private GridLayoutManager myGridlayout;

    private TextView errorTX;

    private ConnectivityManager cm;

    private NetworkInfo myNetInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initilizeLayout();

        if(myNetInfo != null && myNetInfo.isConnected())
        {
            MovieLoadAsyncTask loadMovies = new MovieLoadAsyncTask(movieList, myAdapter, progressBar);
            //default is to fetch movies that is sort by their popularities
            loadMovies.execute(MyURL.getPopFilter());
        }else
        {
            //handle completely no internet situation
            progressBar.setVisibility(View.VISIBLE);
//            errorTX.setText("No internet connection");
//            errorTX.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuID = item.getItemId();
        MovieLoadAsyncTask loadMovies;
        switch(menuID){
            case R.id.submenu_popular:
                //sort by popularity
                loadMovies = new MovieLoadAsyncTask(movieList, myAdapter, progressBar);
                loadMovies.execute(MyURL.getPopFilter());
                return true;

            case R.id.submenu_rating:
                //sort by rating
                loadMovies = new MovieLoadAsyncTask(movieList, myAdapter, progressBar);
                loadMovies.execute(MyURL.getRatingFilter());
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void customOnListItemClick(int clickedItemIndex) {

        Movie clickedMovie =movieList.get(clickedItemIndex);

        Intent openDetail = new Intent(MainActivity.this, DetailActivity.class);
            openDetail.putExtra("title", clickedMovie.getOriginal_title());
            openDetail.putExtra("overview", clickedMovie.getOverview());
            openDetail.putExtra("rating", String.valueOf(clickedMovie.getVote_average()));
            openDetail.putExtra("date", clickedMovie.getRelease_date());
            openDetail.putExtra("imagePath", clickedMovie.getImagePath());

            startActivity(openDetail);
    }

    private void initilizeLayout()
    {

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        errorTX = findViewById(R.id.tv_main_error);

        recyclerView = findViewById(R.id.recyclerView);

        myGridlayout = new GridLayoutManager(
                MainActivity.this, 2, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(myGridlayout);

        movieList = new ArrayList<>();

        myAdapter = new MyAdapter(movieList, this);

        recyclerView.setAdapter(myAdapter);

        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //get current network info
        myNetInfo = cm.getActiveNetworkInfo();

    }

}
