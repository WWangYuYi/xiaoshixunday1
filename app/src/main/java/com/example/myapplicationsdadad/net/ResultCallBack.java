package com.example.myapplicationsdadad.net;

public interface ResultCallBack<T> {
    void resultSuccess(T t);
    void resultFail(String msg);
}
