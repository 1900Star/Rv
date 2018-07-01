package com.yibao.recyclerviewdemo.widge;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.adapter.SwitchImageVPAdapter;
import com.yibao.recyclerviewdemo.bean.UserInfo;
import com.yibao.recyclerviewdemo.util.PagerScroller;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ywf on 2016/11/12.
 */
public class TabPagerListener implements ViewPager.OnPageChangeListener {


    private static final String TAG = "NewsCenterContentTabPager";
    private final Context context;

    private BannerPager mViewPager;
    private TextView tvTitle;
    private LinearLayout llPointContainer;
    public final View view;


    //处理轮播图的自动切换  （消息机制）
    private Handler mHandler = new Handler();
    //判断是否在切换
    private boolean hasSwitch;
    private List<UserInfo> mInfoList;
    private PagerScroller mPagerScroller;

    public TabPagerListener(Context context) {
        this.context = context;
        view = initView();
    }

    private View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.switch_imageview, null, false);
        mViewPager = view.findViewById(R.id.vp_switch_image);
        tvTitle = view.findViewById(R.id.tv_title);
        llPointContainer = view.findViewById(R.id.ll_point_container);

        return view;
    }


    //绑定数据
    public void setData(List<UserInfo> userInfos) {
        mPagerScroller = new PagerScroller(context);
        mInfoList = userInfos;
        //把数据绑定给对应的控件
        initSwitchImageView(userInfos);
        initPoint();
        //把当前的NewsCenterContentTabPager对象传递给SwitchImageViewViewPager
        mViewPager.setTabPager(this);
    }

    //加载图片，并绑定到Viewpager上
    private void initSwitchImageView(List<UserInfo> infos) {
        ArrayList<ImageView> imageViews = new ArrayList<>();
        int size = infos.size();
        for (int i = -1; i < size + 1; i++) {
            UserInfo userInfo;
            if (i == -1) {//
                //添加最后的一张图片
                userInfo = infos.get(size - 1);
            } else if (i == size) {
                //添加第一张图片
                userInfo = infos.get(0);
            } else {
                userInfo = infos.get(i);
            }
            ImageView iv = new ImageView(context);
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(userInfo.getImageUrl()).error(R.mipmap.cai).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
            imageViews.add(iv);
        }
        //创建轮播图适配器
        SwitchImageVPAdapter adapter = new SwitchImageVPAdapter(imageViews, infos);
        //绑定ViewPager
        mViewPager.setAdapter(adapter);
//        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0,false);
        //设置轮播图的文字显示
        tvTitle.setText(infos.get(0).getUserName());
        //给轮播图设置滑动监听
        mViewPager.addOnPageChangeListener(this);
//        adapter.notifyDataSetChanged();
    }

    //初始化点
    private void initPoint() {
        //清空容器里面的布局
        llPointContainer.removeAllViews();
        for (int i = 0; i < mInfoList.size(); i++) {
            //小圆点
            View view = new View(context);
            //设置背景颜色
            view.setBackgroundResource(R.drawable.point_gray_bg);
            //布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(24, 24);
            //右边距
            params.rightMargin = 10;
            //添加布局
            llPointContainer.addView(view, params);
        }
        //让第一个点的背景为红色
        llPointContainer.getChildAt(0).setBackgroundResource(R.drawable.point_red_bg);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        //修正下标
        int pageIndex;
        //正确数据的大小
        int size = mInfoList.size();

        if (position == 0) {
            pageIndex = size - 1;
            //切换到最后一个页面
            mViewPager.setCurrentItem(size);
        } else if (position == size + 1) {
            pageIndex = 0;
            //切换到第一个页面
            mViewPager.setCurrentItem(1);
        } else {
            pageIndex = position - 1;
        }


        //设置轮播图的文字显示
        tvTitle.setText(mInfoList.get(pageIndex).getUserName());

        //修改轮播图点的背景
        int childCount = llPointContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = llPointContainer.getChildAt(i);
            if (pageIndex == i) {
                //选中的页面
                child.setBackgroundResource(R.drawable.point_red_bg);
            } else {
                //未选中的页面
                child.setBackgroundResource(R.drawable.point_gray_bg);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //切换的任务
    private class SwitchTask implements Runnable {

        @Override
        public void run() {
            if (mViewPager != null) {
                //切换逻辑
                int currentItem = mViewPager.getCurrentItem();
                //判断是否在了最后一页
                if (currentItem == mInfoList.size() - 1) {
                    currentItem = 0;
                } else {
                    currentItem++;
                }
                mPagerScroller.initViewPagerScroll(mViewPager, 600);
                mViewPager.setCurrentItem(currentItem);
            }
            mHandler.postDelayed(this, 3000);
        }
    }

    //开始切换
    public void startSwitch() {
        //注意：如果轮播图未切换轮播，开始发送消失进行，否则反之
        if (!hasSwitch) {
            //往Handler里面的消息队列里面发送一个延时的消息
            mHandler.postDelayed(new SwitchTask(), 3000);
        }
    }

    //停止切换
    public void stopSwitch() {
        //清空消息队列
        mHandler.removeCallbacksAndMessages(null);
        hasSwitch = false;
        mPagerScroller.setDuration(600);
    }
}
