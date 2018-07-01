package com.yibao.recyclerviewdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.adapter.MyPagerAdapter;
import com.yibao.recyclerviewdemo.adapter.SwitchImageVPAdapter;
import com.yibao.recyclerviewdemo.bean.UserInfo;
import com.yibao.recyclerviewdemo.widge.BannerPager;
import com.yibao.recyclerviewdemo.widge.TabPagerListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ Author: Luoshipeng
 * @ Name:   BannerView
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/4/ 22:21
 * @ Des:    //TODO
 */
public class BannerLoopingView extends LinearLayout {

    private BannerPager mVp;
    private int num = 1;
    private CompositeDisposable mDisposable;

    public BannerLoopingView(Context context) {
        this(context, null);
    }

    public BannerLoopingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.loop_vp, this, true);
        mVp = view.findViewById(R.id.banner_pager);
        mDisposable = new CompositeDisposable();
        initListener();
    }

    private void initListener() {


    }

    public void setData(final List<UserInfo> list) {
        TabPagerListener tabPagerListener = new TabPagerListener(getContext());
        tabPagerListener.setData(list);
        mVp.setTabPager(tabPagerListener);

    }
}
