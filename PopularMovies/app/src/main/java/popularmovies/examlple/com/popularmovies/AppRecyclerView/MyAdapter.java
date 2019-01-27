package popularmovies.examlple.com.popularmovies.AppRecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import popularmovies.examlple.com.popularmovies.Data.Movie;
import popularmovies.examlple.com.popularmovies.R;
import popularmovies.examlple.com.popularmovies.Utils.Images;
import popularmovies.examlple.com.popularmovies.Utils.MyURL;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{

    private List<Movie> movieList;

    private CustomListItemClickListener myOnClickListener;

    public MyAdapter(List<Movie> inputList, CustomListItemClickListener listener) {
        this.movieList = inputList;
        myOnClickListener = listener;
    }


    public interface CustomListItemClickListener {
        void customOnListItemClick(int clickedItemIndex);
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poster_list_item, parent, false);

        MyViewHolder output = new MyViewHolder(view);

        return output;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie current =movieList.get(position);
        Images.loadImage(
                MyURL.createImageURL(current.getImagePath()).toString(),
                holder.imageView);
        holder.textView.setText(current.getOriginal_title());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_iv);

            textView = itemView.findViewById(R.id.list_item_tv);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            myOnClickListener.customOnListItemClick(position);

        }
    }
}
