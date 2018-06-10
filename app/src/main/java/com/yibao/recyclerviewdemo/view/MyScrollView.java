package com.yibao.recyclerviewdemo.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * @ Author: Luoshipeng
 * @ Name:   MyScrollView
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/9/ 16:04
 * @ Des:     {ScrollView嵌套RecyclerView+悬浮标签}
 */
public class MyScrollView extends NestedScrollView {

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 当悬浮标签到达屏幕顶端悬浮时，将RecyclerView顶部的位置固定。
     *
     * @param headView      悬浮标签布局的高度
     * @param nestedContent 内容布局，如：RecyclerView 瀑布流 、ViewPager等；
     */
    public void resetHeight(final View headView, final View nestedContent, final int headerViewHeight) {
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                MyScrollView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // 固定RecyclerView顶部的位置，  height = 屏幕的高度 - 悬浮标签的高度 - 标题栏的高度
                nestedContent.getLayoutParams().height = MyScrollView.this.getHeight() - headView.getHeight() - headerViewHeight;
                nestedContent.requestLayout();
            }
        });
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // 判断是否需要优先嵌套滑动,解决触摸recyclerview 无法关闭swiperefreshlayout的bug
        if (!dispatchNestedPreScroll(dx, dy, consumed, null)) {
            // 嵌套滑动代码
            boolean hiddenTop = dy > 0 && getScrollY() < getChildAt(0).getHeight() - getHeight();
            boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

            if (hiddenTop || showTop) {
                scrollBy(0, dy);
                consumed[1] = dy;
            }
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (getScrollY() >= getChildAt(0).getHeight() - getHeight())
            return false;
        fling((int) velocityY);
        return true;
    }
}
