package com.example.newsapp.presentation.home.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.data.network.dto.news.Article;
import com.example.newsapp.databinding.ItemNewsBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class TopHeadlineAdapter extends RecyclerView.Adapter<TopHeadlineAdapter.TopHeadlineViewHolder> {

    private Context context;
    private List<Article> news;
    public TopHeadlineAdapter(List<Article> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public TopHeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new TopHeadlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopHeadlineViewHolder holder, int position) {
        holder.render(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNewsList(List<Article> list) {
        news = list;
        notifyDataSetChanged();
    }

    class TopHeadlineViewHolder extends RecyclerView.ViewHolder {

        public TopHeadlineViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void render(Article article) {
            ItemNewsBinding binding = ItemNewsBinding.bind(itemView);
            Glide.with(context)
                    .load(article.getUrlToImage())
                    .fitCenter()
                    .centerCrop()
                    .into(binding.ivNew);
            binding.tvTitle.setText(article.getTitle());
            binding.tvAuthor.setText(article.getAuthor());
            binding.getRoot().setOnClickListener( (view) ->
                Snackbar.make(itemView, article.getTitle(), Snackbar.LENGTH_SHORT).show()
            );
        }

    }
}
