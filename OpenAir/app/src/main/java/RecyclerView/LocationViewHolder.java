package RecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import popularmovies.examlple.com.openair.R;

public class LocationViewHolder extends RecyclerView.ViewHolder {
    TextView name, date;
    public LocationViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_location_name);
        date = itemView.findViewById(R.id.tv_time);
    }
}
