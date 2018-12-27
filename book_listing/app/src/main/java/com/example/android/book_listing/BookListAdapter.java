package com.example.android.book_listing;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class BookListAdapter extends ArrayAdapter<Book> {



    public BookListAdapter(Context context, int resource, ArrayList<Book> books) {
        super(context, resource, books);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.listitem, parent,false);
        }

        Book thisBook = getItem(position);

        TextView bookName= (TextView)listItemView.findViewById(R.id.bookNameView);
        bookName.setText(thisBook.getBookName());

        TextView author = (TextView)listItemView.findViewById(R.id.authorNameView);
        author.setText(thisBook.getAuthor());

        ImageButton googlePlay = listItemView.findViewById(R.id.imageButton);
        googlePlay.setImageResource(R.drawable.google_play_books);

        return super.getView(position, convertView, parent);


    }
}
