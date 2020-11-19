package com.example.myapplicationsdadad.presenter;

import com.example.myapplicationsdadad.MainActivity;
import com.example.myapplicationsdadad.bean.TianQiBean;
import com.example.myapplicationsdadad.model.MainModel;
import com.example.myapplicationsdadad.net.ResultCallBack;
import com.example.myapplicationsdadad.view.MainView;

public class MainPresenter {
    private final MainView view;
    private final MainModel model;
    public MainPresenter(MainView mainView) {
        view = mainView;
        model = new MainModel();
    }

    public void requestData(String authorization,String mUrl) {
        model.getTianQiData(authorization,mUrl, new ResultCallBack<TianQiBean>() {
            @Override
            public void resultSuccess(TianQiBean tianQiBean) {
                view.success(tianQiBean);
            }

            @Override
            public void resultFail(String msg) {
                view.fail(msg);
            }
        });
    }
}
