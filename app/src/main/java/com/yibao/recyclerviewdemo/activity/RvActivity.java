package com.yibao.recyclerviewdemo.activity;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.yibao.recyclerviewdemo.bean.Beans;
import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.adapter.MyAdapter;

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

        for (int i = 1; i < 5; i++) {
            Beans beans = new Beans();
            beans.setItemType(i);
            beans.setImageList(getImageList(type));
            beans.setContentList(getContentList());
            list.add(beans);
        }
        MyAdapter myAdapter = new MyAdapter(this, list);
//        MyAdapterTest myAdapter = new MyAdapterTest(this, list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        SnappingLinearLayoutManager manager = new SnappingLinearLayoutManager(this, LinearLayout.VERTICAL, false);
        mRv.setLayoutManager(manager);
        mRv.smoothScrollToPosition(2);
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
            String st = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=79d11a1f0224ab18f41be9775c8283a1/b64543a98226cffc35a0408bb2014a90f603ea05.jpg";
            String s = "http://img.zcool.cn/community/01d71e554bb56b000001bf7274a240.jpg";
            String str = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=91f762232334349b600b66c5a09270a2/30adcbef76094b36187598d4a8cc7cd98d109d20.jpg";
            String ss = "http://imgsrc.baidu.com/imgad/pic/item/8cb1cb13495409234ab8a9449958d109b3de4933.jpg";

            imageList.add(st);
            imageList.add(ss);
            imageList.add(str);
            imageList.add(s);

        } else {
            String st = "http://v3img.osscdn.ifensi.com/xw_imgs/2016/09/16/ee546900ca3b1.jpg";
            String s = "http://img0.ph.126.net/JdYwZNnoCV40SP-j_tV8Xw==/6631927583791487265.jpg";
            String str = "http://img0.ph.126.net/deHNp2zCuTf4FZZZuVlLnw==/6631761557538249708.jpg";
            String ss = "http://imgcdn.guoku.com/images/c49f0a449a4697226feab67ee4ca623a.jpg";

            imageList.add(st);
            imageList.add(ss);
            imageList.add(str);
            imageList.add(s);
        }
        return imageList;

    }

    private List<String> getContentList() {
        List<String> contentList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            String st = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=79d11a1f0224ab18f41be9775c8283a1/b64543a98226cffc35a0408bb2014a90f603ea05.jpg";
            String s = "http://img.zcool.cn/community/01d71e554bb56b000001bf7274a240.jpg";
            String str = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=91f762232334349b600b66c5a09270a2/30adcbef76094b36187598d4a8cc7cd98d109d20.jpg";
            String ss = "http://imgsrc.baidu.com/imgad/pic/item/8cb1cb13495409234ab8a9449958d109b3de4933.jpg";
            String dd = "http://pic.qiantucdn.com/58pic/26/22/21/58c8db915ebaa_1024.jpg";
            String bb = "http://pic28.photophoto.cn/20130823/0035035065390036_b.jpg";
            String cc = "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=55689fe7dd2a6059461de959405d5eee/242dd42a2834349b075c1dabc3ea15ce36d3be41.jpg";
            String aa = "http://www.quimg.com/via/user/5_20160729122014654.jpg";
            contentList.add(s);
            contentList.add(st);
            contentList.add(ss);
            contentList.add(str);
            contentList.add(ss);
            contentList.add(dd);
            contentList.add(bb);
            contentList.add(cc);
            contentList.add(ss);
            contentList.add(aa);
        }

        return contentList;

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

    public class SnappingLinearLayoutManager extends LinearLayoutManager {

        public SnappingLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state,
                                           int position) {
            RecyclerView.SmoothScroller smoothScroller = new TopSnappedSmoothScroller(recyclerView.getContext());
            smoothScroller.setTargetPosition(position);
            startSmoothScroll(smoothScroller);
        }

        private class TopSnappedSmoothScroller extends LinearSmoothScroller {
            public TopSnappedSmoothScroller(Context context) {
                super(context);

            }

            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return SnappingLinearLayoutManager.this
                        .computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected int getVerticalSnapPreference() {
                return SNAP_TO_START;
            }
        }
    }
}
