package examlple.com.testrandomdarkcolor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyViewHolder> {
    private Random rnd;
    private Context mContext;
    private List<Integer> colors;

    public ColorAdapter(Context c) {
        rnd = new Random();
        mContext = c;
        colors = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
           /* colors.add(Color.argb(255,
                    (rnd.nextInt(100) + 100),
                    (rnd.nextInt(100) + 100),
                    (rnd.nextInt(100) + 100)));*/

            colors.add(Color.argb(255,
                    (rnd.nextInt(50) + 200),
                    (rnd.nextInt(50) + 200),
                    (rnd.nextInt(50) + 200)));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_recycler_view, parent, false);

        return new MyViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.text.setText(String.valueOf(colors.get(position)));
        //test light color
//        holder.text.setTextColor(Color.WHITE);
        holder.text.setTextColor(Color.BLACK);
        holder.text.setBackgroundColor(colors.get(position));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.list_item_textView);
        }
    }
}
