package com.yibao.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.util.ImageUtil;
import com.yibao.recyclerviewdemo.view.ZoomImageView;

import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   MyPagerAdapter
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/4/ 23:05
 * @ Des:    //TODO
 */
public class MyPagerAdapter extends PagerAdapter {
    private List<String> mList;
    private Context mContext;

    public MyPagerAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ZoomImageView zoomImageView = ImageUtil.creatZoomView(mContext);
        Glide.with(mContext).load(mList.get(position)).placeholder(R.mipmap.a).error(R.mipmap.a).into(zoomImageView);
        container.addView(zoomImageView);
        return zoomImageView;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
