package com.yibao.recyclerviewdemo.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.yibao.recyclerviewdemo.util.LogUtil;

/**
 * @ Author: Luoshipeng
 * @ Name:   TanslationView
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/11/ 0:33
 * @ Des:    TODO
 */
public class TanslationView extends NestedScrollView {
    public static final String TAG = "AlphaTitleScrollView";
    private int mSlop;
    private Toolbar toolbar;
    private ImageView headView;

    public TanslationView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TanslationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TanslationView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        // mSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
        mSlop = 10;
        LogUtil.i(TAG, mSlop + "");
    }

    /**
     *            头部布局
     * @param headView
     * @param toolbar
     */
    public void setTitleAndHead(Toolbar toolbar, ImageView headView) {
        this.toolbar = toolbar;
        this.headView = headView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        float headHeight = headView.getMeasuredHeight()
                - toolbar.getMeasuredHeight();
        int alpha = (int) (((float) t / headHeight) * 255);
        if (alpha >= 255)
            alpha = 255;
        if (alpha <= mSlop)
            alpha = 0;
        toolbar.setBackgroundColor(Color.argb(alpha,109,90,123));

        super.onScrollChanged(l, t, oldl, oldt);
    }
}
