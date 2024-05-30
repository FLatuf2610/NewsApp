package com.example.newsapp.data.repository;

import com.example.newsapp.data.network.api_client.NewsApiClient;
import com.example.newsapp.data.network.dto.news.NewsResponse;
import com.example.newsapp.domain.repository.NewsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class NewsRepositoryImpl implements NewsRepository {

    private final NewsApiClient apiClient;
    @Inject
    public NewsRepositoryImpl(NewsApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Single<NewsResponse> getTopHeadlineArticles(String country, String category, int page) {
        return apiClient.getTopHeadlineArticles(country, category, page)
                .observeOn(Schedulers.io())
                .map(
                        newsResponse -> {
                            return newsResponse;
                        }
                )
                .subscribeOn(Schedulers.io());
    }
}
