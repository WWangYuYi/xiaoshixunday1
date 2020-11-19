package com.example.myapplicationsdadad.view;

import com.example.myapplicationsdadad.bean.TianQiBean;

public interface MainView {
    void success(TianQiBean tianQiBean);

    void fail(String msg);
}
