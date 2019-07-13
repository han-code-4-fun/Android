package popularmovies.examlple.com.popularmovies.AppUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import popularmovies.examlple.com.popularmovies.R;
import popularmovies.examlple.com.popularmovies.Utils.Images;
import popularmovies.examlple.com.popularmovies.Utils.MyFormater;
import popularmovies.examlple.com.popularmovies.Utils.MyURL;

public class DetailActivity extends AppCompatActivity {

    //this activity shows detailed movie information after user click a movie poster from main activity

    public static final String LOG_TAG = DetailActivity.class.getSimpleName();

    private ImageView imageView;

    private TextView titleTV, overviewTV, ratingTV, dateTV;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initilizeLayout();

        Intent intent = getIntent();
        loadData(intent);

    }

    private void loadData(Intent intent)
    {
        if(intent.hasExtra("title")
                && intent.hasExtra("overview")
                && intent.hasExtra("rating")
                && intent.hasExtra("date")
                && intent.hasExtra("imagePath"))
        {
            titleTV.setText(intent.getStringExtra("title"));

            String tempOverView = MyFormater.createOverviewText(intent.getStringExtra("overview"));
            overviewTV.setText(Html.fromHtml(tempOverView));

            String tempRating = MyFormater.createRatingText(intent.getStringExtra("rating"));
            ratingTV.setText(Html.fromHtml(tempRating));

            String tempDate =MyFormater.createReleaseDate(intent.getStringExtra("date"));
            dateTV.setText(Html.fromHtml(tempDate));

            String imagePath =  intent.getStringExtra("imagePath");

            Images.loadImage(MyURL.createImageURL(imagePath).toString(), imageView);


        }else{
            Log.e(LOG_TAG, "error passing Intent data from locationActivity");
        }

    }

    private void initilizeLayout()
    {
        imageView = findViewById(R.id.iv_detail);

        titleTV = findViewById(R.id.tv_detail_title);

        overviewTV = findViewById(R.id.tv_detail_overview);

        ratingTV = findViewById(R.id.tv_detail_rating);

        dateTV = findViewById(R.id.tv_detail_date);
    }
}
