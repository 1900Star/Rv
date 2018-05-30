package com.yibao.recyclerviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   RvActivity
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/5/31/ 0:54
 * @ Des:    //TODO
 */
public class RvActivity extends AppCompatActivity {
    private RecyclerView mRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_activty);
        initView();
        initData();


    }

    private void initData() {
        List<Beans> list = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Beans beans = new Beans();
            beans.setItemType(i);
            list.add(beans);
        }
        AdapterRv adapterRv = new AdapterRv(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(layoutManager);
        mRv.setAdapter(adapterRv);
        adapterRv.notifyDataSetChanged();

    }

    private void initView() {
        mRv = findViewById(R.id.rv);

    }
}
