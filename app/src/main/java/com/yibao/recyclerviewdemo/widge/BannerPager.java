package com.yibao.recyclerviewdemo.widge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import com.yibao.recyclerviewdemo.util.LogUtil;

/**
 * @ Author: Luoshipeng
 * @ Name:   LabPager
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/16/ 9:19
 * @ Des:    TODO
 */
public class BannerPager extends ViewPager {
    String TAG = this.getClass().getSimpleName();
    //控制轮播图开始和停止的对象
    private TabPagerListener tabPager;

    //在哪里调用？ 在加载数据成功之后，就可以把该对象设置过来
    public void setTabPager(TabPagerListener tabPager) {
        this.tabPager = tabPager;
    }

    public BannerPager(@NonNull Context context) {
        super(context);
    }

    public BannerPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int startX;
    private int startY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.i(TAG, "按下");
                //停止轮播图的切换
                tabPager.stopSwitch();
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i(TAG, "移动");
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();
                //移动距离
                int disX = moveX - startX;
                int dixY = moveY - startY;
                //处理的是水平的滑动   并且是向右的
                if (Math.abs(disX) > Math.abs(dixY) && moveX > startX) {
                    //请求外层控件不要拦截事件
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i(TAG, "弹起");
                //处理点击事件
                int upX = (int) ev.getX();
                int upY = (int) ev.getY();
                if (startX == upX && startY == upY) {
                    //点击事件
                    Toast.makeText(getContext(), "点击事件", Toast.LENGTH_SHORT).show();
                }
                //开始切换
                tabPager.startSwitch();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
