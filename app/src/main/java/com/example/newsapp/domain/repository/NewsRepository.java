package com.example.newsapp.domain.repository;

import com.example.newsapp.data.network.dto.news.NewsResponse;

import io.reactivex.rxjava3.core.Single;

public interface NewsRepository {

    Single<NewsResponse> getTopHeadlineArticles(String country, String category, int page);

}
