package com.yibao.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvB;
    private RecyclerView mRvA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        Adapters adapters = new Adapters(20);
        Adapters adaptersA = new Adapters(35);
        RecyclerView.LayoutManager managers = new LinearLayoutManager(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRvA.setLayoutManager(managers);
        mRvB.setLayoutManager(manager);
        mRvA.setAdapter(adapters);
        mRvB.setAdapter(adaptersA);
        mRvA.setNestedScrollingEnabled(false);
        mRvB.setNestedScrollingEnabled(false);
        adapters.notifyDataSetChanged();
        adaptersA.notifyDataSetChanged();

    }

    private void initView() {
        mRvB = findViewById(R.id.rv_b);
        mRvA = findViewById(R.id.rv_a);
    }
}
