package com.example.ponomarev.newstask.ArticleHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticlesAPI {

    @GET("top-headlines?country=ua&category=business&apiKey=19600dcc9c574edba8fe45cd1407572d")
    Call<ResponseServer> responceCall();
}
