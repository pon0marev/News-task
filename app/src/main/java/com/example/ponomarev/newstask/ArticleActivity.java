package com.example.ponomarev.newstask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ponomarev.newstask.ArticleHelper.Article;
import com.squareup.picasso.Picasso;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        TextView textTitle = (TextView) findViewById(R.id.titleTextView);
        TextView textContent = (TextView) findViewById(R.id.contentTextView);
        TextView textAuthor = (TextView) findViewById(R.id.authorTextView);
        TextView textDate = (TextView) findViewById(R.id.dateTextView);
        ImageView itemImage = (ImageView) findViewById(R.id.articleImage);

        Bundle arguments = getIntent().getExtras();
        final Article article ;
        if(arguments!=null) {
            article = (Article) arguments.getSerializable(Article.class.getSimpleName());

            textTitle.setText(String.valueOf(article.getTitle()));
            textContent.setText(String.valueOf(article.getContent()));
            textAuthor.setText(article.getAuthor());
            textDate.setText(article.getPublishedAt() + "");

            Picasso.get().load(article.getUrlToImage()).into(itemImage);
        }
    }
}
