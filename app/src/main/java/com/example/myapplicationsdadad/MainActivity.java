package com.example.myapplicationsdadad;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationsdadad.adapter.RcyAdapter;
import com.example.myapplicationsdadad.bean.TianQiBean;
import com.example.myapplicationsdadad.net.ApiService;
import com.example.myapplicationsdadad.presenter.MainPresenter;
import com.example.myapplicationsdadad.view.MainView;
import com.mob.MobSDK;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "WSK";
    private RecyclerView rcy_weather;
    private MainPresenter mainPresenter;
    private String city = "北京";
    String authorization = "APPCODE 964e16aa1ae944e9828e87b8b9fbd30a";
    String mUrl = "https://jisutqybmf.market.alicloudapi.com/weather/query?city="+city;
    private ArrayList<TianQiBean.ResultBean.DailyBean> dailyBeans;
    private RcyAdapter rcyAdapter;
    private ArrayList<TianQiBean.ResultBean.DailyBean> daily;
    private ArrayList<TianQiBean.ResultBean.DailyBean> daily1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        initView();
        initData();
    }

    private void initData() {
        mainPresenter.requestData(authorization,mUrl);
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(httpLoggingInterceptor)
//                .build();
//
//        new Retrofit.Builder()
//                .baseUrl(ApiService.mTianQiUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(client)
//                .build()
//                .create(ApiService.class)
//                .getData2(authorization,mUrl)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<TianQiBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(TianQiBean tianQiBean) {
//                        daily = (ArrayList<TianQiBean.ResultBean.DailyBean>) tianQiBean.getResult().getDaily();
//
//                        String json = daily.toString();
//                        Log.d(TAG, "onNext: "+json);
//
//                        dailyBeans.addAll(daily);
//                        rcyAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: "+e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    private void initView() {
        rcy_weather = (RecyclerView) findViewById(R.id.rcy_weather);
        rcy_weather.setLayoutManager(new LinearLayoutManager(this));
        dailyBeans = new ArrayList<>();
        rcyAdapter = new RcyAdapter(this, dailyBeans);
        rcy_weather.setAdapter(rcyAdapter);
        rcy_weather.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void success(TianQiBean tianQiBean) {
        daily1 = (ArrayList<TianQiBean.ResultBean.DailyBean>) tianQiBean.getResult().getDaily();
        String json = daily1.toString();
        Log.d("WSK", "success: "+json);
        dailyBeans.addAll(daily1);
        rcyAdapter.notifyDataSetChanged();

    }

    @Override
    public void fail(String msg) {
        Log.d(TAG, "fail: "+msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_city,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.city1:
                String title = (String) item.getTitle();
                city = "";
                city += title;
                Toast.makeText(this, "您点击了："+city, Toast.LENGTH_SHORT).show();
                dailyBeans.removeAll(daily1);
                initData();
            break;
            case R.id.city2:
                String title2 = (String) item.getTitle();
                city = "";
                city += title2;
                Toast.makeText(this, "您点击了：" + city, Toast.LENGTH_SHORT).show();
                dailyBeans.removeAll(daily1);
                initData();
                break;
            case R.id.city3:
                String title3 = (String) item.getTitle();
                city = "";
                city += title3;
                Toast.makeText(this, "您点击了："+city, Toast.LENGTH_SHORT).show();
                dailyBeans.removeAll(daily1);
                initData();
                break;
            case R.id.city4:
//                MobSDK.submitPolicyGrantResult(granted, null);
                
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}