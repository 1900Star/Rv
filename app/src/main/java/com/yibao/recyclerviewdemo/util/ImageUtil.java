package com.yibao.recyclerviewdemo.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yibao.recyclerviewdemo.view.ZoomImageView;

/**
 * @ Author: Luoshipeng
 * @ Name:   ImageUtil
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/5/ 1:16
 * @ Des:    //TODO
 */
public class ImageUtil {
    public static ZoomImageView creatZoomView(Context context) {
        ZoomImageView view = new ZoomImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(1920, 1080);
        view.setScaleType(ImageView.ScaleType.MATRIX);
        view.reSetState();
        view.setLayoutParams(params);
        return view;
    }
}
