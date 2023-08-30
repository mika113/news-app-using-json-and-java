package com.news.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class NewsDetailActivity extends AppCompatActivity {
 String title,desc,content,url,imageurl;
 private TextView titlen,subdec,dec;
 private ImageView imagenews;
 private Button readnews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news_detail );
        title=getIntent().getStringExtra( "title" );
        desc=getIntent().getStringExtra( "desc" );
        imageurl=getIntent().getStringExtra( "image" );
        content=getIntent().getStringExtra( "content" );
        url=getIntent().getStringExtra( "url" );

        titlen=findViewById( R.id.txtTitle );
        subdec=findViewById( R.id.txtsubdesc);
        dec=findViewById( R.id.decs);

        imagenews=findViewById( R.id.imagenews );
        readnews=findViewById( R.id.readnews );

        titlen.setText( title );
        subdec.setText( desc );
        dec.setText( content );
        Picasso.get().load( imageurl ).into(imagenews );
        readnews.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(Intent.ACTION_VIEW);
                        i.setData( Uri.parse( url ) );
                        startActivity( i );

                    }
                }
        );
    }
}