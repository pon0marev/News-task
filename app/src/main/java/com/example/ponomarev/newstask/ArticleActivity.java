package com.example.ponomarev.newstask;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ponomarev.newstask.ArticleHelper.Article;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ArticleActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextView textTitle = (TextView) findViewById(R.id.titleTextView);
        TextView textContent = (TextView) findViewById(R.id.contentTextView);
        TextView textAuthor = (TextView) findViewById(R.id.authorTextView);
        TextView textDate = (TextView) findViewById(R.id.dateTextView);
        ImageView itemImage = (ImageView) findViewById(R.id.articleImage);

        Bundle arguments = getIntent().getExtras();
        final Article article ;
        if(arguments!=null) {
            article = (Article) arguments.getSerializable(Article.class.getSimpleName());

            textTitle.setText(article.getTitle());
            this.setTitle(article.getTitle());
            if(article.getContent()!=null)
                textContent.setText(article.getContent());
            else
                textContent.setText(article.getDescription());
            textAuthor.setText(article.getAuthor());
            textDate.setText(DateConvertor.convert(article.getPublishedAt()) + "");

            Picasso.get().load(article.getUrlToImage()).into(itemImage);
        }

             toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
