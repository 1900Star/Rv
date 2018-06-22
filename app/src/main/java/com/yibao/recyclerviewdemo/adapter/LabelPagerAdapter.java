package com.yibao.recyclerviewdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.yibao.recyclerviewdemo.activity.LabelFragment;
import com.yibao.recyclerviewdemo.bean.UserInfo;

import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   LabelPagerAdapter
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/16/ 10:11
 * @ Des:    TODO
 */
public class LabelPagerAdapter extends FragmentPagerAdapter {
    private List<UserInfo> mList;

    public LabelPagerAdapter(FragmentManager fm, List<UserInfo> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return LabelFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getUserName();

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
