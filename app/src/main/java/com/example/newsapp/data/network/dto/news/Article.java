package com.example.newsapp.data.network.dto.news;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("description")
    private String description;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("source")
    private Source source;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source=" + source +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                '}';
    }
}
