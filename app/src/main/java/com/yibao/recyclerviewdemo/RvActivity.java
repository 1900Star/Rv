package com.yibao.recyclerviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yibao.recyclerviewdemo.adapter.MyAdapter;
import com.yibao.recyclerviewdemo.adapter.MyAdapterTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ Author: Luoshipeng
 * @ Name:   RvActivity
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/5/31/ 0:54
 * @ Des:    //TODO
 */
public class RvActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRv;
    private SwipeRefreshLayout mRefreshLayout;
    private CompositeDisposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_activty);
        mDisposable = new CompositeDisposable();
        initView();
        initData(1);


    }

    private void initData(int type) {
        List<Beans> list = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            Beans beans = new Beans();
            beans.setItemType(i);
            beans.setImageList(getImageList(type));
            list.add(beans);
        }
//        MyAdapter myAdapter = new MyAdapter(this, list);
        MyAdapterTest myAdapter = new MyAdapterTest(this, list);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }


    @Override
    public void onRefresh() {

        mDisposable.add(Observable.timer(2, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                initData(2);
            }
        }));
        mRefreshLayout.setRefreshing(false);

    }

    private List<String> getImageList(int dataType) {
        List<String> imageList = new ArrayList<>();
        if (dataType == 1) {
            String st = "http://v3img.osscdn.ifensi.com/xw_imgs/2016/09/16/ee546900ca3b1.jpg";
            String s = "http://img0.ph.126.net/JdYwZNnoCV40SP-j_tV8Xw==/6631927583791487265.jpg";
            String str = "http://img0.ph.126.net/deHNp2zCuTf4FZZZuVlLnw==/6631761557538249708.jpg";
            String ss = "http://imgcdn.guoku.com/images/c49f0a449a4697226feab67ee4ca623a.jpg";

            imageList.add(st);
            imageList.add(ss);
            imageList.add(str);
            imageList.add(s);

        } else {
            String dd = "http://imgsrc.baidu.com/baike/pic/item/a1ec08fa513d2697840d6ed557fbb2fb4216d858.jpg";
            String bb = "http://image.l99.com/04d/1442033464862_n1mb75.jpg";
            String cc = "http://img01.cztv.com/201604/25/9a1422e95ce38a716a2d969b2dad877e.jpg";
            String aa = "http://pic1.win4000.com/wallpaper/c/57a2a17a19a8f.jpg";

            imageList.add(aa);
            imageList.add(bb);
            imageList.add(cc);
            imageList.add(dd);
        }
        return imageList;

    }

    private void initView() {
        mRv = findViewById(R.id.rv);
        mRefreshLayout = findViewById(R.id.swiperefresh);
        mRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.clear();

    }
}
