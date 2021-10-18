package com.example.onnews;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Find the earthquake at the given position in the list of earthquakes
        News currentNew = getItem(position);

        TextView address = (TextView) listItemView.findViewById(R.id.address);
        address.setText(currentNew.getSectionId());

        TextView title = (TextView) listItemView.findViewById(R.id.des);
        title.setText(currentNew.getWebTitle());


        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentNew.getDate());

        TextView author = (TextView) listItemView.findViewById(R.id.article);
        author.setText(currentNew.getAuthor());

        ImageView thumbnail = (ImageView) listItemView.findViewById(R.id.image);
        Picasso.get().load(currentNew.getThumbnail()).into(thumbnail);


        return listItemView;
    }
}
