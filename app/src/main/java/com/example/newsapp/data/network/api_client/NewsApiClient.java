package com.example.newsapp.data.network.api_client;

import com.example.newsapp.data.network.dto.news.NewsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiClient {

    @GET("top-headlines")
    Single<NewsResponse> getTopHeadlineArticles(
            @Query("country") String country,
            @Query("category") String category,
            @Query("page") int page
    );

    @GET("everything")
    Single<NewsResponse> searchArticles(
            @Query("q") String query,
            @Query("sortBy") String sortBy
    );

}
