package com.yibao.recyclerviewdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yibao.recyclerviewdemo.ContentAdapterTest;
import com.yibao.recyclerviewdemo.R;

import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   InfoView
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/4/ 23:27
 * @ Des:    //TODO
 */
public class FlagView extends LinearLayout {


    private TextView mTitle;

    public FlagView(Context context) {
        this(context, null);
    }


    public FlagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.flag_view, this);
        mTitle = view.findViewById(R.id.tv_title);
    }

    public void setData() {
        mTitle.setText("热闹标题");
    }


}
