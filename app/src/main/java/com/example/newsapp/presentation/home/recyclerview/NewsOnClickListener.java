package com.example.newsapp.presentation.home.recyclerview;

import com.example.newsapp.data.network.dto.news.Article;

public interface NewsOnClickListener {
    void onClick(Article article);
}
