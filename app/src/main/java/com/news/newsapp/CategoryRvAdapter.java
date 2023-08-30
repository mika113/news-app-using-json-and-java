package com.news.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRvAdapter extends RecyclerView.Adapter<CategoryRvAdapter.ViewHolder> {
    @NonNull
    private ArrayList<CategoryRvModel> categoryArralist;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryRvAdapter(@NonNull ArrayList<CategoryRvModel> categoryArralist, Context context,
                             CategoryClickInterface categoryClickInterface) {
        this.categoryArralist = categoryArralist;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }


    @Override
    public CategoryRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.categories,parent,false );
        return new CategoryRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRvAdapter.ViewHolder holder, int position) {
        CategoryRvModel categoryRvModel=categoryArralist.get( position );
        holder.txtcategories.setText( categoryRvModel.getCategory()  );
        Picasso.get().load( categoryRvModel.getCategoryimageurl() ).into( holder.ivCategories );
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick( position );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return categoryArralist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtcategories;
        private ImageView ivCategories;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            txtcategories=itemView.findViewById( R.id.txtcatego );
            ivCategories=itemView.findViewById( R.id.ivCategories );
        }
    }

    interface CategoryClickInterface {
        void onCategoryClick(int position);
    }
}
