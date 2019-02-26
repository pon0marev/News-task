package com.example.ponomarev.newstask;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.ponomarev.newstask.Adapter.RecyclerAdapter;

import com.example.ponomarev.newstask.ArticleHelper.Article;
import com.example.ponomarev.newstask.ArticleHelper.ArticlesAPI;
import com.example.ponomarev.newstask.ArticleHelper.ResponseServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerAdapterListener{

    String category;
    String key;
    String country;

    final String url="https://newsapi.org/v2/";

    private ResponseServer jsonData=null;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category="business";
        key=getString(R.string.api_key);
        country="us";
        getJson();




    }

    private void getJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArticlesAPI articlesApi = retrofit.create(ArticlesAPI.class);

        Call<ResponseServer> responseServerCall = articlesApi.responceCall(country,category,key);


        responseServerCall.enqueue(new Callback<ResponseServer>() {

            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),""+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                    jsonData=response.body();
                    swap();
                } else {
                    Toast.makeText(getApplicationContext(),""+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void swap(){
        if (open()){
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            findViewById(R.id.pageRecycler).setVisibility(View.VISIBLE);
        }
    }
    public boolean open(){

        if(jsonData!=null){
            recyclerView=(RecyclerView)findViewById(R.id.pageRecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            RecyclerAdapter rAdapter=new RecyclerAdapter(getApplicationContext(),jsonData.getArticles());
            rAdapter.setOnItemClickListener(this);
            recyclerView.setAdapter(rAdapter);
            return true;
        }
        return false;
    }


    @Override
    public void onItemClick(int position, View v) {
        Intent intent=new Intent(this,ArticleActivity.class);
        intent.putExtra(Article.class.getSimpleName(),jsonData.getArticles().get(position));
        startActivity(intent);
    }

}
