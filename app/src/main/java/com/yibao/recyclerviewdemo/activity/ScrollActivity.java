package com.yibao.recyclerviewdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.adapter.BaseRvAdapter;
import com.yibao.recyclerviewdemo.adapter.LabelPagerAdapter;
import com.yibao.recyclerviewdemo.adapter.LabelRvAdapter;
import com.yibao.recyclerviewdemo.adapter.MyGridViewAdapter;
import com.yibao.recyclerviewdemo.bean.UserInfo;
import com.yibao.recyclerviewdemo.view.BannerView;
import com.yibao.recyclerviewdemo.view.ContentView;
import com.yibao.recyclerviewdemo.view.InfoView;
import com.yibao.recyclerviewdemo.view.MyScrollView;
import com.yibao.recyclerviewdemo.widge.LabelPager;
import com.yibao.recyclerviewdemo.widge.TabPagerListener;

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
    private TextView mTvTitle;
    private LabelPager mLabelPager;
    private RecyclerView mRvLabel;
    private LabelRvAdapter mLabelRvAdapter;
    private LinearLayout mBannerRootView;
    private boolean isChangeData = true;
    public String[] picUrlArr = {
            "http://img1.imgtn.bdimg.com/it/u=2791440251,3676180997&fm=214&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=697743233,72559105&fm=214&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1414787322,1119406030&fm=214&gp=0.jpg",
            "http://img.ph.126.net/JDzBlk86dk5RNbEYM5UWQQ==/1531223873306738973.jpg",
            "http://img2.imgtn.bdimg.com/it/u=848244421,2660255089&fm=214&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1126409661,2553252841&fm=214&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3263430655,576348720&fm=214&gp=0.jpg",
            "http://img313.ph.126.net/OjdxxrIeB-R0qcPJOR6rkQ==/3675218770911537293.jpg",
            "http://img.ph.126.net/Jdb5-HJWcg8NsxN4bO3rgw==/3369255471227898647.jpg",
            "http://img0.ph.126.net/MUS2Yz_NSGzK34R6d-fUeQ==/2856971013713215152.jpg",
            "http://tupian.enterdesk.com/2012/0818/cyf/03/enter%20%2810%29.jpg"};
    private String[] arr2 = {
            "http://cdn.duitang.com/uploads/item/201506/24/20150624181420_Tsx5v.jpeg",
            "http://ww2.sinaimg.cn/large/6cf3d1b5jw1et8911x2y4j20pk12cn7i.jpg",
            "http://wx1.sinaimg.cn/mw1024/9ec19de8ly1fbjut77oz7j21kw12oagk.jpg",
            "http://cdn.duitang.com/uploads/item/201510/29/20151029025711_JNRPa.thumb.700_0.jpeg",
            "http://img1.imgtn.bdimg.com/it/u=3673481042,1436601449&fm=214&gp=0.jpg",
            "http://img3.duitang.com/uploads/item/201306/13/20130613091407_RGXSF.thumb.700_0.jpeg",
            "http://img3.duitang.com/uploads/item/201608/14/20160814113720_CSkfc.jpeg",
            "http://wx1.sinaimg.cn/large/9ec19de8ly1fdx0ocde2mj20tt18gqcm.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1138940585,392305322&fm=214&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=135876527,302649005&fm=214&gp=0.jpg",
//            "http://easyread.ph.126.net/ujCavd-AskxBYnwwz5d0tQ==/7916509008757939546.jpg",
//            "http://img3.duitang.com/uploads/item/201609/06/20160906203951_ZdemH.jpeg",
//            "http://ww2.sinaimg.cn/large/6cf3d1b5jw1ernzbkv5d0j20pk12cn0j.jpg",
//            "http://www.sinaimg.cn/dy/slidenews/4_img/2016_48/704_2081109_607584.jpg",
//            "http://wx3.sinaimg.cn/mw1024/9ec19de8ly1fbjut5cmpvj21kw12r79z.jpg"
    };

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
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                initData(position, position);
                mLabelPager.setCurrentItem(position, false);
                Toast.makeText(ScrollActivity.this, getUserInfo(position).get(position).getUserName(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ScrollActivity.this, LoopPagerActivity.class));
            }
        });
//        mLabelRvAdapter.setItemListener(new BaseRvAdapter.OnItemListener<UserInfo>() {
//            @Override
//            public void showDetailsView(UserInfo bean) {
//                Toast.makeText(ScrollActivity.this, bean.getUserName()
//                        , Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(ScrollActivity.this, LoopPagerActivity.class));
//
//            }
//        });

    }

    @Override
    public void onRefresh() {
        mCompositeDisposable.clear();
        mCompositeDisposable.add(Observable.timer(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                initData(1, 1);
                mRefreshLayout.setRefreshing(false);
                isChangeData = !isChangeData;
            }
        }));

    }

    private void initData(int a, int b) {
        int userSize = getUserInfo(a).size();
        if (userSize > 4) {
            if (getUserInfo(a).size()-3 < 9) {
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
//        mScrollBannerView.setData(getImageList(a));
        TabPagerListener tabPagerListener = new TabPagerListener(this);
        tabPagerListener.setData(getUserInfo(a));
        tabPagerListener.startSwitch();
        mBannerRootView.addView(tabPagerListener.view);
        // InfoView
        mScrollInfoView.setData(getImageList(b));
        // GridView
        mGridViewAdapter = new MyGridViewAdapter(this, getUserInfo(a));
        mGridView.setAdapter(mGridViewAdapter);
        // 用RecyclerView显示栏目
//        mLabelRvAdapter = new LabelRvAdapter(this, getUserInfo(a));
//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4,
//                StaggeredGridLayoutManager.VERTICAL);
//        manager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
//        mRvLabel.setLayoutManager(manager);
//        mRvLabel.setHasFixedSize(true);
//        mRvLabel.setAdapter(mLabelRvAdapter);
//        mLabelRvAdapter.notifyDataSetChanged();
        // ContentView
//        mSrollContentView.setData(getContentList(a));
//        mRefreshLayout.setRefreshing(false);
        mLabelPager.setAdapter(new LabelPagerAdapter(getSupportFragmentManager(), getUserInfo(1)));

        //避免自动滑动到底部
        mGridView.setFocusable(true);
        mGridView.setFocusableInTouchMode(true);
        mGridView.requestFocus();
        //2.set height

        mScrollView.resetHeight(mGridView, mLabelPager, 165);

    }

    private List<UserInfo> getUserInfo(int ts) {
        ArrayList<UserInfo> mList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            mList.add(new UserInfo(isChangeData ? arr2[i] : picUrlArr[i], "新垣结衣 " + i));
        }
        return mList;
    }


    private List<String> getContentList(int type) {
        List<String> contentList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            if (type % 2 == 0) {
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
            } else {
                String tt = "http://5b0988e595225.cdn.sohucs.com/images/20180421/d0b35cb030fd4fcb905c1043349b2439.jpeg";
                String oo = "http://img3.imgtn.bdimg.com/it/u=2423163533,2396542910&fm=27&gp=0.jpg";
                String pp = "http://img5q.duitang.com/uploads/item/201502/10/20150210193233_Ccidc.thumb.700_0.jpeg";
                contentList.add(tt);
                contentList.add(oo);
                contentList.add(pp);
            }
        }

        return contentList;

    }

    private void initView() {
        mRefreshLayout = findViewById(R.id.swiperefresh);
        mScrollBannerView = findViewById(R.id.scroll_banner_view);
        mTvTitle = findViewById(R.id.tv_title);
        mScrollInfoView = findViewById(R.id.scroll_info_view);
        mGridView = findViewById(R.id.grid_view);
        mSrollContentView = findViewById(R.id.sroll_content_view);
        mScrollView = findViewById(R.id.sv);
        mLlTitle = findViewById(R.id.ll_title);
        mLabelPager = findViewById(R.id.label_pager);
        mScrollView.setTitleAndHead(mLlTitle, mTvTitle);
        mGridViewParams = (LinearLayout.LayoutParams) mGridView.getLayoutParams();
        mRefreshLayout.setRefreshing(false);
        mRvLabel = findViewById(R.id.rv_label);
        mBannerRootView = findViewById(R.id.ll_banner_root);
    }


    private List<String> getImageList(int dataType) {
        List<String> imageList = new ArrayList<>();


        if (dataType % 2 == 0) {
            String st = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=79d11a1f0224ab18f41be9775c8283a1/b64543a98226cffc35a0408bb2014a90f603ea05.jpg";
            String s = "http://img.zcool.cn/community/01d71e554bb56b000001bf7274a240.jpg";
            String str = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=91f762232334349b600b66c5a09270a2/30adcbef76094b36187598d4a8cc7cd98d109d20.jpg";
            String ss = "http://imgsrc.baidu.com/imgad/pic/item/8cb1cb13495409234ab8a9449958d109b3de4933.jpg";

            imageList.add(st);
            imageList.add(str);
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
