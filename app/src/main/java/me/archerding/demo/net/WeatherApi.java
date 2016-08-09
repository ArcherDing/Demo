package me.archerding.demo.net;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by archerding on 16-8-9.
 */

public interface WeatherApi {

    @Headers({"apikey:234ca42dcb9f67901fb6d67d38aa2006"})
    @GET("apistore/weatherservice/citylist")
    Observable<Weather> getWeather(@Query("cityname") String cityname);
}
