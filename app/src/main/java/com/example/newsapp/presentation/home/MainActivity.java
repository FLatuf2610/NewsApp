package com.example.newsapp.presentation.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.data.network.dto.news.Article;
import com.example.newsapp.databinding.ActivityMainBinding;
import com.example.newsapp.presentation.detail.DetailActivity;
import com.example.newsapp.presentation.home.recyclerview.NewsOnClickListener;
import com.example.newsapp.presentation.home.recyclerview.TopHeadlineAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements NewsOnClickListener {

    private HomeViewModel viewModel;
    ActivityMainBinding binding;
    private RecyclerView rvBusiness;
    private TopHeadlineAdapter businessAdapter;

    private RecyclerView rvSports;
    private TopHeadlineAdapter sportsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewModel.onCreateViewModel();
        initRVs();
        initObservables();
    }

    private void initObservables() {
        viewModel.businessNews.observe(this, (newsResponse) -> businessAdapter.setNewsList(newsResponse.getArticles()));
        viewModel.sportsNews.observe(this, (newsResponse -> sportsAdapter.setNewsList(newsResponse.getArticles())));
    }

    private void initRVs() {
        businessAdapter = new TopHeadlineAdapter(new ArrayList<>(), this);
        rvBusiness = binding.rvBusiness;
        rvBusiness.setAdapter(businessAdapter);
        rvBusiness.setLayoutManager(new LinearLayoutManager(this));

        sportsAdapter = new TopHeadlineAdapter(new ArrayList<>(), this);
        rvSports = binding.rvSports;
        rvSports.setAdapter(sportsAdapter);
        rvSports.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.clearDisposables();
    }

    @Override
    public void onClick(Article article) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("url", article.getUrl());
        startActivity(intent);
    }
}