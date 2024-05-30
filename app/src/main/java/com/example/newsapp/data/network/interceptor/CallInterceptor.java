package com.example.newsapp.data.network.interceptor;

import androidx.annotation.NonNull;


import com.example.newsapp.common.Constants;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class CallInterceptor implements Interceptor {

    @Inject
    public CallInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String apiKey = Constants.API_KEY;
        Request newRequest = originalRequest.newBuilder()
                .header("X-Api-Key", apiKey)
                .build();
        return chain.proceed(newRequest);
    }
}
