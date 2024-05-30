package com.example.newsapp.domain.use_cases;

import com.example.newsapp.data.network.dto.news.NewsResponse;
import com.example.newsapp.data.repository.NewsRepositoryImpl;
import com.example.newsapp.domain.repository.NewsRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetTopHeadlinesArticlesUseCase {

    private final NewsRepositoryImpl repository;
    @Inject
    public GetTopHeadlinesArticlesUseCase(NewsRepositoryImpl newsRepository) {
        this.repository = newsRepository;
    }

    public Single<NewsResponse> invoke(String country, String category, int page) {
        return repository.getTopHeadlineArticles(country, category, page);
    }
}
