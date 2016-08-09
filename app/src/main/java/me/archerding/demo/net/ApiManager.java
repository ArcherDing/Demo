package me.archerding.demo.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by archerding on 16-8-9.
 */

public class ApiManager {
    private static final String BASE_URL = "http://apis.baidu.com/";
    private WeatherApi mWeatherApi;
    private Retrofit mRetrofit;

    private static class ApiManagerHolder {
        static final ApiManager INSTANCE = new ApiManager();
    }

    private ApiManager() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public WeatherApi getTestApi() {
        if (mWeatherApi == null) {
            mWeatherApi = mRetrofit.create(WeatherApi.class);
        }
        return mWeatherApi;
    }

    public static ApiManager getInstance() {
        return ApiManagerHolder.INSTANCE;
    }
}
