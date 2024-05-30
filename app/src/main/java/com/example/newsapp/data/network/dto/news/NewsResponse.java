package com.example.newsapp.data.network.dto.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("articles")
    private List<Article> articles;
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private int totalResults;

    public List<Article> getArticles() {
        return articles;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public String toString() {
        return "NewsResponse{" +
                "articles=" + articles +
                ", status='" + status + '\'' +
                ", totalResults=" + totalResults +
                '}';
    }
}
