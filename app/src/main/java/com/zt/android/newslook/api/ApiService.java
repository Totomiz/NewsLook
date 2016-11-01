package com.zt.android.newslook.api;

import com.zt.android.newslook.mode.MeituGson;
import com.zt.android.newslook.mode.NewsGson;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tony on 16/10/28.
 */

public interface ApiService {

    public static String baseUrl = "http://api.tianapi.com/";
    public static String key = "20217f8594e0029b716dffbfad4bd7d5";

    @GET("social/")
    Observable<NewsGson> getNewsData(@Query("key") String key, @Query("num") String num, @Query("page") int page);


    @GET("meinv/")
    Observable<MeituGson> getPicData(@Query("key") String key, @Query("num") String num, @Query("page") int page);

    @GET("meinv/")
    Observable<MeituGson> getPicData(@Query("key") String key, @Query("num") String num, @Query("word") String word, @Query("page") int page);
}
