package com.yibao.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ Author: Luoshipeng
 * @ Name:   VpAdapter
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/5/31/ 1:47
 * @ Des:    //TODO
 */
public class VpAdapter extends PagerAdapter {
    private Context mContext;

    public VpAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vi_item, container, false);
        view.setTag(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
