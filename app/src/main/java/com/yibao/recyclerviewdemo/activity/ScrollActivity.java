package com.yibao.recyclerviewdemo.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.adapter.MyGridViewAdapter;
import com.yibao.recyclerviewdemo.bean.UserInfo;
import com.yibao.recyclerviewdemo.util.LogUtil;
import com.yibao.recyclerviewdemo.view.BannerView;
import com.yibao.recyclerviewdemo.view.ContentView;
import com.yibao.recyclerviewdemo.view.InfoView;
import com.yibao.recyclerviewdemo.view.MyScrollView;

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
 * @ Name:   ScrollActivity
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/9/ 14:47
 * @ Des:    //TODO
 */
public class ScrollActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private BannerView mScrollBannerView;
    private InfoView mScrollInfoView;
    private GridView mGridView;
    private ContentView mSrollContentView;
    private LinearLayout.LayoutParams mGridViewParams;
    private MyScrollView mScrollView;
    private LinearLayout mLlTitle;
    private String TAG = this.getClass().getSimpleName();
    private SwipeRefreshLayout mRefreshLayout;
    private CompositeDisposable mCompositeDisposable;
    private MyGridViewAdapter mGridViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity);
        mCompositeDisposable = new CompositeDisposable();
        initView();
        initData(1, 2);
        initListener();


    }

    private void initListener() {
        mRefreshLayout.setOnRefreshListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    mLlTitle.setAlpha(scrollY >= 165 ? 1 : 0);
                    mLlTitle.setVisibility(scrollY >= 165 ? View.VISIBLE : View.GONE);

                    LogUtil.d(TAG, " scrollB==========  " + scrollY);
                    LogUtil.d(TAG, " titleHeight======  " + mLlTitle.getHeight());
                }
            });
        }
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LogUtil.d(TAG, "============     " + getUserInfo(1).get(position).getUserName());
                Toast.makeText(ScrollActivity.this, getUserInfo(1).get(position).getUserName(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onRefresh() {
        mCompositeDisposable.add(Observable.timer(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                initData(2, 1);

                mRefreshLayout.setRefreshing(false);
            }
        }));

    }

    private void initData(int a, int b) {
        int userSize = getUserInfo(a).size();
        if (userSize > 4) {
            if (getUserInfo(a).size() < 9) {
                mGridViewParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                mGridViewParams.height = dip2px(this, 110);
            } else if (userSize < 13) {
                mGridViewParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                mGridViewParams.height = dip2px(this, 160);
            } else if (userSize < 17) {
                mGridViewParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                mGridViewParams.height = dip2px(this, 210);
            } else if (userSize < 21) {
                mGridViewParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                mGridViewParams.height = dip2px(this, 260);
            }
            mGridView.setLayoutParams(mGridViewParams);
        }
        // Banner
        mScrollBannerView.setData(getImageList(a));
        // InfoView
        mScrollInfoView.setData(getImageList(b));
        // GridView
        mGridViewAdapter = new MyGridViewAdapter(this, getUserInfo(a));
        mGridView.setAdapter(mGridViewAdapter);
        // ContentView
        mSrollContentView.setData(getContentList());
        mRefreshLayout.setRefreshing(false);

        //避免自动滑动到底部
        mGridView.setFocusable(true);
        mGridView.setFocusableInTouchMode(true);
        mGridView.requestFocus();
        //2.set height

        mScrollView.resetHeight(mGridView, mSrollContentView, 165);

    }

    private List<UserInfo> getUserInfo(int ts) {
        ArrayList<UserInfo> mList = new ArrayList<>();

        for (int i = 1; i < 9; i++) {
            if (i % 2 == 0) {
                mList.add(new UserInfo("", ts == 1 ? "O " + i : "新垣结衣 " + i));
            } else {
                mList.add(new UserInfo("", ts == 1 ? "R1 " + i : "Find " + i));

            }
        }
        return mList;
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
        mRefreshLayout = findViewById(R.id.swiperefresh);
        mScrollBannerView = findViewById(R.id.scroll_banner_view);
        mScrollInfoView = findViewById(R.id.scroll_info_view);
        mGridView = findViewById(R.id.grid_view);
        mSrollContentView = findViewById(R.id.sroll_content_view);
        mScrollView = findViewById(R.id.sv);
        mLlTitle = findViewById(R.id.ll_title);
        mGridViewParams = (LinearLayout.LayoutParams) mGridView.getLayoutParams();
        mRefreshLayout.setRefreshing(true);


    }


    private List<String> getImageList(int dataType) {
        List<String> imageList = new ArrayList<>();


        if (dataType == 1) {
            String st = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=79d11a1f0224ab18f41be9775c8283a1/b64543a98226cffc35a0408bb2014a90f603ea05.jpg";
            String s = "http://img.zcool.cn/community/01d71e554bb56b000001bf7274a240.jpg";
            String str = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=91f762232334349b600b66c5a09270a2/30adcbef76094b36187598d4a8cc7cd98d109d20.jpg";
            String ss = "http://imgsrc.baidu.com/imgad/pic/item/8cb1cb13495409234ab8a9449958d109b3de4933.jpg";

            imageList.add(st);
            imageList.add(str);
            imageList.add(ss);
            imageList.add(str);
            imageList.add(s);

        } else if (dataType == 2) {
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

    /**
     * dp转为px
     *
     * @param context  上下文
     * @param dipValue dp值
     * @return d
     */
    private int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

}
