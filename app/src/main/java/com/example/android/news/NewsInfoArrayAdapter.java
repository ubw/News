package com.example.android.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsInfoArrayAdapter extends ArrayAdapter<NewsInfo> {
    private NewsInfo newsInfo;

    private class ViewHolder {
        private TextView title;
        private TextView time;
        private TextView author;
    }

    public NewsInfoArrayAdapter(Context context, List<NewsInfo> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_items, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.time = (TextView) view.findViewById(R.id.time);
            view.setTag(viewHolder);
        } else {
            //复用convertView
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        newsInfo = getItem(position);
        //设置文本
        viewHolder.title.setText(newsInfo.getmTitle());
        viewHolder.time.setText(newsInfo.getmTime());

        return view;
    }
}
