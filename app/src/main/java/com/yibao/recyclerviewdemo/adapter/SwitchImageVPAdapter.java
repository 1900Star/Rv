package com.yibao.recyclerviewdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yibao.recyclerviewdemo.bean.UserInfo;

import java.util.List;

/**
 * Created by ywf on 2016/11/12.
 */
public class SwitchImageVPAdapter extends PagerAdapter {
    private List<UserInfo> mList;
    private List<ImageView> mImageViews;

    public SwitchImageVPAdapter(List<ImageView> imageViews, List<UserInfo> list) {
        this.mList = list;
        this.mImageViews = imageViews;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        ImageView imageView = mImageViews.get(position);
//        imageView.setImageResource(imgIds[position % imgIds.length]);
        ImageView imageView = mImageViews.get(position % mImageViews.size());
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        int size = mList.size();
        return size > 1 ? size + 2 : size;
//        return mList != null && mList.size() > 0 ? Integer.MAX_VALUE : 0;
    }


}
