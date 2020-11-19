package com.example.myapplicationsdadad.model;

import android.util.Log;

import com.example.myapplicationsdadad.bean.TianQiBean;
import com.example.myapplicationsdadad.net.ApiService;
import com.example.myapplicationsdadad.net.ResultCallBack;

import java.util.ArrayList;
import java.util.Scanner;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel {
    public void getTianQiData(String authorization, String mUrl, final ResultCallBack<TianQiBean> callBack) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        new Retrofit.Builder()
                .baseUrl(ApiService.mTianQiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(ApiService.class)
                .getData2(authorization,mUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TianQiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TianQiBean tianQiBean) {
//                        ArrayList<TianQiBean.ResultBean.DailyBean> daily = (ArrayList<TianQiBean.ResultBean.DailyBean>) tianQiBean.getResult().getDaily();
                        callBack.resultSuccess(tianQiBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.resultFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
