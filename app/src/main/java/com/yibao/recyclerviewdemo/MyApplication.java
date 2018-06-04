package com.yibao.recyclerviewdemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @ Author: Luoshipeng
 * @ Name:   MyApplication
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/5/31/ 23:45
 * @ Des:    //TODO
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
