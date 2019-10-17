package com.ravinada.cryptocompare.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.News;
import com.ravinada.cryptocompare.NewsDetailsActivity;
import com.ravinada.cryptocompare.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private Context mContext;
    private List<News> newsList;

    public NewsListAdapter(Context mContext, List<News> newsList) {
        this.mContext = mContext;
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        final News news = newsList.get(position);
        holder.newsTitle.setText(news.getNewsTitle());
        holder.newsSource.setText(news.getNewsSource());
        Picasso.get().load(news.getNewsImageURL()).into(holder.newsImage);
        holder.news = news;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                intent.putExtra("NEWS_URL",news.getNewsUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView newsTitle, newsSource;
        public ImageView newsImage;

        public News news;

        public NewsViewHolder(View view) {
            super(view);
            newsTitle = view.findViewById(R.id.news_title);
            newsSource = view.findViewById(R.id.news_source);
            newsImage = view.findViewById(R.id.news_image);
        }
    }

}
