package com.dev.arif.api;

import android.support.annotation.NonNull;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohdarif on 12/04/18.
 */

public class ApiCallingService {

    static Retrofit retrofit;
    private final static String apiKey="dh8t5d2rqj7wkbuf9wn8fzsg";
    private final static String baseURL="https://api.gettyimages.com";
    private static OkHttpClient okHttpClientWithApiKey;
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static ApiCalls getService()
    {
        return getRetrofitService().create(ApiCalls.class);
    }

    public static Retrofit getRetrofitService()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClientWithApiKey(apiKey))
                .build();
        return retrofit;
    }
    @NonNull
    private static OkHttpClient getOkHttpClientWithApiKey(@NonNull final String authKey) {
        if (okHttpClientWithApiKey == null) {
            okHttpClientWithApiKey = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("Api-Key", authKey)
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .addInterceptor(logging)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClientWithApiKey;
    }
}
