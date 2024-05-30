package com.example.newsapp.di;

import com.example.newsapp.common.Constants;
import com.example.newsapp.data.network.api_client.NewsApiClient;
import com.example.newsapp.data.network.interceptor.CallInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    Retrofit getRetrofit(CallInterceptor interceptor) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    NewsApiClient getNewsApiClient(Retrofit retrofit) {
        return retrofit
                .create(NewsApiClient.class);
    }

}
