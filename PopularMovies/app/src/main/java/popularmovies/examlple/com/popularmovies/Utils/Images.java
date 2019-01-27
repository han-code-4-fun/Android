package popularmovies.examlple.com.popularmovies.Utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import popularmovies.examlple.com.popularmovies.R;

//image class that fetching image and load to an imageview
public final class Images {
    public static void loadImage(String uri, ImageView inputView)
    {
        Picasso.get()
                .load(uri)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)

                .into(inputView);


    }
}
