package popularmovies.examlple.com.popularmovies.MyAsyncTask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import popularmovies.examlple.com.popularmovies.Data.Movie;
import popularmovies.examlple.com.popularmovies.AppRecyclerView.MyAdapter;
import popularmovies.examlple.com.popularmovies.Utils.JSONUtils;

public class MovieLoadAsyncTask extends AsyncTask<String,Void, ArrayList<Movie>> {

    private List<Movie> movieList;
    private MyAdapter myAdapter;
    private ProgressBar progressBar;

    public MovieLoadAsyncTask(
            List<Movie> movieList, MyAdapter myAdapter, ProgressBar progressBar) {
        this.movieList = movieList;
        this.myAdapter = myAdapter;
        this.progressBar = progressBar;
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... strings) {

        if(strings.length<1 || strings[0] == null)
        {
            return null;
        }

        return JSONUtils.getMovies(strings[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        progressBar.setVisibility(View.INVISIBLE);
        movieList.clear();
        movieList.addAll(movies);
        myAdapter.notifyDataSetChanged();
    }
}
