package com.yibao.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yibao.recyclerviewdemo.R;

import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   VpAdapter
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/5/31/ 1:47
 * @ Des:    //TODO
 */
public class VpAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mList;

    public VpAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
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
        ImageView imageView = view.findViewById(R.id.iv_vp);
        String s = mList.get(position);
        Glide.with(mContext).load(s).asBitmap().into(imageView);
//        imageView.setImageURI(s);
        view.setTag(position);
        container.addView(view);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
