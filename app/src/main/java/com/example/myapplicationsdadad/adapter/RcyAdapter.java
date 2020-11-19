package com.example.myapplicationsdadad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplicationsdadad.R;
import com.example.myapplicationsdadad.bean.TianQiBean;

import java.util.ArrayList;

public class RcyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<TianQiBean.ResultBean.DailyBean> mData;

    public RcyAdapter(Context mContext, ArrayList<TianQiBean.ResultBean.DailyBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TianQiBean.ResultBean.DailyBean dailyBean = mData.get(position);
        ViewHolder vh = (ViewHolder) holder;
        vh.tv_date.setText(dailyBean.getDate());
        vh.tv_city.setText(dailyBean.getWeek());
        vh.tv_weather.setText(dailyBean.getNight().getWeather());
        Glide.with(mContext).load(R.mipmap.duoyun).into(vh.img_weather);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView tv_date;
        public TextView tv_city;
        public TextView tv_weather;
        public ImageView img_weather;
        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
            this.tv_city = (TextView) rootView.findViewById(R.id.tv_week);
            this.tv_weather = (TextView) rootView.findViewById(R.id.tv_weather);
            this.img_weather = (ImageView) rootView.findViewById(R.id.img_weather);

        }
    }
}
