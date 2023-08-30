package com.news.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRvAdapter.CategoryClickInterface  {
    //21c3dcccae034f85948885d18b7311f8

    private RecyclerView newsRv,categoryRv;
    private ProgressBar loadingRB;
    private ArrayList<Articles> articlesArraylist;
    private ArrayList<CategoryRvModel> categoryRvAdapterArrayList;
    private CategoryRvAdapter categoryRvAdapter;
    private NewsRvAdapter newsRvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        newsRv=findViewById( R.id.rcnews );
        categoryRv=findViewById( R.id.rccategories );
        loadingRB=findViewById( R.id.ldProgress );
        articlesArraylist=new ArrayList<>();
        categoryRvAdapterArrayList=new ArrayList<>();
        newsRvAdapter=new NewsRvAdapter( this, articlesArraylist );
        categoryRvAdapter=new CategoryRvAdapter( categoryRvAdapterArrayList,
                this,this::onCategoryClick );
        newsRv.setLayoutManager( new LinearLayoutManager( this ) );
        newsRv.setAdapter( newsRvAdapter );
        categoryRv.setAdapter( categoryRvAdapter );
        getcategories();
        getNews( "All" );
        newsRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategoryClick(int position) {
        String category=categoryRvAdapterArrayList.get( position ).getCategory();
        getNews( category );


    }
    public void getcategories(){
        categoryRvAdapterArrayList.add( new CategoryRvModel( "All",
                "https://images.unsplash.com/photo-1588681664899-f142ff2dc9b1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1074&q=80" ) );
        categoryRvAdapterArrayList.add( new CategoryRvModel( "Technology", "https://images.unsplash.com/photo-1550751827-4bd374c3f58b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80" ) );
        categoryRvAdapterArrayList.add( new CategoryRvModel( "Science", "https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1172&q=80" ) );
        categoryRvAdapterArrayList.add( new CategoryRvModel( "Sports", "https://images.unsplash.com/photo-1589487391730-58f20eb2c308?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1174&q=80" ) );
        categoryRvAdapterArrayList.add( new CategoryRvModel( "General", "https://images.unsplash.com/photo-1494059980473-813e73ee784b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1169&q=80" ) );
        categoryRvAdapterArrayList.add( new CategoryRvModel( "Business", "https://images.unsplash.com/39/lIZrwvbeRuuzqOoWJUEn_Photoaday_CSD%20%281%20of%201%29-5.jpg?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80" ) );
        categoryRvAdapterArrayList.add( new CategoryRvModel( "Entertainment", "https://images.unsplash.com/photo-1567593810070-7a3d471af022?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1171&q=80" ) );
        categoryRvAdapterArrayList.add( new CategoryRvModel( "Health", "https://images.unsplash.com/photo-1532938911079-1b06ac7ceec7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1332&q=80" ) );
        categoryRvAdapter.notifyDataSetChanged();
    }
    //21c3dcccae034f85948885d18b7311f8
    //"https://newsapi.org/v2/sources?&country?=us&category="+category+"&apiKey=21c3dcccae034f85948885d18b7311f8"
    public void getNews(String category){
        loadingRB.setVisibility( View.VISIBLE );
        articlesArraylist.clear();
        String categoryURL="https://newsapi.org/v2/top-headlines?country=us&category="+category+"&apiKey=21c3dcccae034f85948885d18b7311f8";
        String url="https://newsapi.org/v2/top-headlines?country=us&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=21c3dcccae034f85948885d18b7311f8";
        String BASE_URL="https://newsapi.org/";
        Retrofit retrofit=new Retrofit.Builder().
                baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        RetrofitApi retrofitApi =retrofit.create( RetrofitApi.class );
        Call<NewsModel> call;
        if(category.equals( "All" )){
            call =retrofitApi.getAllNews( url );
        }else{
            call=retrofitApi.getNewsByCategory( categoryURL );
        }
        call.enqueue( new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel=response.body();
                loadingRB.setVisibility( View.GONE );
                ArrayList<Articles> articles=newsModel.getArticles();
                for (int i=0;i<articles.size();i++){
                    articlesArraylist.add( new Articles( articles.get( i ).getTitle(),
                            articles.get( i ).getDescription(),
                            articles.get(i).getUrlToImage(),articles.get( i ).getUrl(),
                            articles.get( i ).getContent() ) );
                }
                newsRvAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText( MainActivity.this, "Fail to get news", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}