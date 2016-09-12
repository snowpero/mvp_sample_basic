package com.ninis.basemvp_sample.network;

import com.ninis.basemvp_sample.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gypark on 2016. 9. 8..
 */
public class RetrofitManager {
    private static final int DEF_TIMEOUT = 20;
    private static final int DEF_RETRY_COUNT = 3;

    // http://jsonplaceholder.typicode.com/posts
    private static final String baseUrl = "http://jsonplaceholder.typicode.com/";

    private static ApiInterface apiInterface = null;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    private static RetrofitManager ourInstance = new RetrofitManager();

    public static RetrofitManager getInstance() {
        return ourInstance;
    }

    private RetrofitManager() {
        init();
    }

    private void init() {
        initOkHttp();
        initRetrofit();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        builder.connectTimeout(DEF_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEF_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEF_TIMEOUT, TimeUnit.SECONDS);

        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }
}
