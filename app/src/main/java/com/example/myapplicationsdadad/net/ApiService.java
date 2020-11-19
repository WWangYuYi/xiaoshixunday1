package com.example.myapplicationsdadad.net;

import com.example.myapplicationsdadad.bean.TianQiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
    String mTianQiUrl = "https://jisutqybmf.market.alicloudapi.com/";
    @GET("query")
//    @Headers("Authorization: APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
    Observable<TianQiBean> getData(@Header("Authorization") String authorization, @Query("city") String city);

    @GET
//    @Headers("Authorization: APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
    Observable<TianQiBean> getData2(@Header("Authorization") String authorization, @Url String url);

}
