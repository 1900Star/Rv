package com.yibao.recyclerviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * @ Author: Luoshipeng
 * @ Name:   MyGridView
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/9/ 15:43
 * @ Des:    //TODO
 */
public class MyGridview extends GridView {
    public MyGridview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridview(Context context) {
        super(context);
    }

    public MyGridview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //通过重写dispatchTouchEvent方法来禁止滑动
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        //禁止Gridview进行滑动
//        return ev.getAction() == MotionEvent.ACTION_MOVE || super.dispatchTouchEvent(ev);
//
//    }
}
