package com.joesoft.gads2020leaderboard.services;

import android.os.Build;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    public static final String BASE_URL = "https://gadsapi.herokuapp.com";
    // setting up the logger
    private static HttpLoggingInterceptor sLogger
            = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    // setting up okHttp client
    private static OkHttpClient.Builder okHttp
            = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request request = chain.request();

                    request = request.newBuilder()
                            .addHeader("x-device-type", Build.DEVICE)
                            .addHeader("Accept-Language", Locale.getDefault().getLanguage())
                            .build();

                    return chain.proceed(request);
                }
            })
            .addInterceptor(sLogger);


    private static Retrofit.Builder sBuilder
            = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit sRetrofit = sBuilder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return sRetrofit.create(serviceType);
    }
}
