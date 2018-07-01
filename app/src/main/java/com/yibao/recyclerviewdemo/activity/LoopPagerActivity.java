package com.yibao.recyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.adapter.SwitchImageVPAdapter;
import com.yibao.recyclerviewdemo.bean.UserInfo;
import com.yibao.recyclerviewdemo.widge.BannerPager;
import com.yibao.recyclerviewdemo.widge.TabPagerListener;

import java.util.ArrayList;
import java.util.List;

public class LoopPagerActivity extends AppCompatActivity {

    private int loadType = 1;
    private LinearLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_pager);
        initView();
        initData();
    }

    private void initData() {
        TabPagerListener tabPagerListener = new TabPagerListener(this);
        tabPagerListener.setData(getUserInfo(loadType));
        tabPagerListener.startSwitch();
        mRootView.addView(tabPagerListener.view);

    }

    private List<UserInfo> getUserInfo(int ts) {
        ArrayList<UserInfo> mList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            if (i % 2 == 0) {
                mList.add(new UserInfo("http://img1.imgtn.bdimg.com/it/u=794856472,1703433831&fm=27&gp=0.jpg", "新垣结衣 " + i));
            } else {
                mList.add(new UserInfo("http://img5.imgtn.bdimg.com/it/u=829783089,1697386622&fm=27&gp=0.jpg", "新垣结衣 " + i));

            }
        }
        return mList;
    }

    private void initView() {
        mRootView = findViewById(R.id.root_view);
        loadType = 2;
    }
}
