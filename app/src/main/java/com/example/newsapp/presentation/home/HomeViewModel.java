package com.example.newsapp.presentation.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.newsapp.common.Constants;
import com.example.newsapp.data.network.dto.news.NewsResponse;
import com.example.newsapp.domain.use_cases.GetTopHeadlinesArticlesUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    @Inject
    public HomeViewModel(GetTopHeadlinesArticlesUseCase getTopHeadlinesArticlesUseCase) {
        this.getTopHeadlinesArticlesUseCase = getTopHeadlinesArticlesUseCase;
    }
    private final GetTopHeadlinesArticlesUseCase getTopHeadlinesArticlesUseCase;

    private final CompositeDisposable cm = new CompositeDisposable();

    private final MutableLiveData<NewsResponse> _businessNews = new MutableLiveData<>();
    public final LiveData<NewsResponse> businessNews = _businessNews;

    private final MutableLiveData<NewsResponse> _sportsNews = new MutableLiveData<>();
    public final LiveData<NewsResponse> sportsNews = _sportsNews;



    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _isLoading;

    public void getBusinessNews() {
        _isLoading.postValue(true);
        Disposable disposable = getTopHeadlinesArticlesUseCase.invoke(
                "us",
                        Constants.CATEGORY_BUSINESS,
                        1
                )
                .subscribe(
                        newsResponse -> {
                            _businessNews.postValue(newsResponse);
                            _isLoading.postValue(false);
                            Log.i("BUSINESS", newsResponse.toString());
                        },
                        throwable -> {
                            _isLoading.postValue(false);
                            Log.e("ERROR BUSINESS", throwable.toString());
                        }
                );
        cm.add(disposable);
    }

    public void getSportsNews() {
        _isLoading.postValue(true);
        Disposable disposable = getTopHeadlinesArticlesUseCase.invoke(
                        "us",
                        Constants.CATEGORY_SPORTS,
                        1
                )
                .subscribe(
                        newsResponse -> {
                            _sportsNews.postValue(newsResponse);
                            _isLoading.postValue(false);
                            Log.i("SPORTS", newsResponse.toString());
                        },
                        throwable -> {
                            _isLoading.postValue(false);
                            Log.e("ERROR SPORTS", throwable.toString());
                        }
                );
        cm.add(disposable);
    }

    public void onCreateViewModel() {
        getBusinessNews();
        getSportsNews();
    }

    public void clearDisposables() {
        cm.dispose();
    }
}
