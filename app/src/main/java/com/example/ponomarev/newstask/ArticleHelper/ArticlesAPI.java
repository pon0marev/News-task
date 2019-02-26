package com.example.ponomarev.newstask.ArticleHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesAPI {

    @GET("top-headlines")
    Call<ResponseServer> responceCall(@Query("country" )String country,@Query("category") String category,@Query("apiKey") String apiKey);
}
