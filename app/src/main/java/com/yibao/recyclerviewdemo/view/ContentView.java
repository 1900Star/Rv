package com.yibao.recyclerviewdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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


    private RecyclerView mRecyclerView;

    public ContentView(Context context) {
        this(context, null);
    }


    public ContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.content_view, this);
        mRecyclerView = view.findViewById(R.id.item_rv);
        initListener();
    }

    private void initListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Glide.with(getContext()).resumeRequests();
                        break;
                    // 加载图片
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Glide.with(getContext()).pauseRequests();
                        break;

                    default:
                        break;
                }


            }


        });

    }

    public void setData(List<String> list) {
//        ContentAdapter contentAdapter = new ContentAdapter(getContext(), list);
        ContentAdapterTest contentAdapter = new ContentAdapterTest(getContext(), list);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(contentAdapter);
        contentAdapter.notifyDataSetChanged();
    }


}
