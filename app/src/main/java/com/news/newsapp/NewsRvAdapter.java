package com.news.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRvAdapter extends RecyclerView.Adapter<NewsRvAdapter.ViewHolder> {
    @NonNull
    private Context context;

    private ArrayList<Articles> articlesArrayList;

    public NewsRvAdapter(@NonNull Context context, ArrayList<Articles> articlesArrayList) {
        this.context = context;
        this.articlesArrayList = articlesArrayList;
    }

    @Override
    public NewsRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.news,parent,false );
       return new NewsRvAdapter.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRvAdapter.ViewHolder holder, int position) {
        Articles articles=articlesArrayList.get( position );
        holder.subTitleTv.setText( articles.getDescription() );
        holder.titleTv.setText( articles.getTitle() );
        Picasso.get().load( articles.getUrlToImage() ).into( holder.newsIv );

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,NewsDetailActivity.class);
                i.putExtra( "title",articles.getTitle() );
                i.putExtra( "desc",articles.getDescription() );
                i.putExtra( "content",articles.getContent() );
                i.putExtra( "image",articles.getUrlToImage() );
                i.putExtra( "url",articles.getUrl() );
                context.startActivity( i );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv,subTitleTv;
        private ImageView newsIv;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            titleTv=itemView.findViewById( R.id.newsheading );
            subTitleTv=itemView.findViewById( R.id.newsdesc );
            newsIv=itemView.findViewById( R.id.ivNews );
        }
    }
}
