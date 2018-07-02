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
public class BannerViews extends LinearLayout {

    private ViewPager mVp;
    private int num = 1;
    private CompositeDisposable mDisposable;

    public BannerViews(Context context) {
        this(context, null);
    }

    public BannerViews(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vp, this, true);
        mVp = view.findViewById(R.id.vp);
        mDisposable = new CompositeDisposable();
        initListener();
    }

    private void initListener() {


    }

    @SuppressLint("CheckResult")
    public void setData(final List<String> list) {
        mDisposable.clear();
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getContext(), list);
        mVp.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
        mDisposable.add(Observable.interval(4, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                num++;
                if (num > list.size()) {
                    num = 1;
                }
                mVp.setCurrentItem(num);
            }
        }));

    }
}
