package com.yibao.recyclerviewdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.adapter.MyPagerAdapter;
import com.yibao.recyclerviewdemo.adapter.VpAdapter;

import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   BannerView
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/4/ 22:21
 * @ Des:    //TODO
 */
public class BannerView extends LinearLayout {

    private ViewPager mVp;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vp, this, true);
        mVp = view.findViewById(R.id.vp);
    }

    public void setData(List<String> list) {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getContext(), list);
        mVp.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();

    }
}
