package com.yibao.recyclerviewdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yibao.recyclerviewdemo.ContentAdapter;
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
public class ContentView extends LinearLayout {


    private TextView mTitle;
    private RecyclerView mRecyclerView;

    public ContentView(Context context) {
        this(context, null);
    }


    public ContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.content_view, this);
        mTitle = view.findViewById(R.id.tv_title);
        mRecyclerView = view.findViewById(R.id.item_rv);
    }

    public void setData(List<String> list) {
        mTitle.setText("热闹标题");
//        ContentAdapter contentAdapter = new ContentAdapter(getContext(), list);
        ContentAdapterTest contentAdapter = new ContentAdapterTest(getContext(), list);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(contentAdapter);
        contentAdapter.notifyDataSetChanged();
    }


}
