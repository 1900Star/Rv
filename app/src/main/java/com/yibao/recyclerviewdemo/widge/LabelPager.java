package com.yibao.recyclerviewdemo.widge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @ Author: Luoshipeng
 * @ Name:   LabPager
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/16/ 9:19
 * @ Des:    TODO
 */
public class LabelPager extends ViewPager {
    public LabelPager(@NonNull Context context) {
        super(context);
    }

    public LabelPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
