package com.yibao.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.bean.UserInfo;

import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   MyGridViewAdapter
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/9/ 15:13
 * @ Des:    //TODO
 */
public class MyGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<UserInfo> mList;

    public MyGridViewAdapter(Context context, List<UserInfo> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public int getCount() {


        return mList != null ? mList.size() : 0;

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_grid_view, null);
            holder = new Holder();
            holder.item_tex = view.findViewById(R.id.grid_tv);
            holder.mRootView = view.findViewById(R.id.grid_view_item);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.item_tex.setText(mList.get(position).getUserName());
        return view;
    }

    private class Holder {

        TextView item_tex;
        LinearLayout mRootView;

        public TextView getItem_tex() {
            return item_tex;
        }

        public void setItem_tex(TextView item_tex) {
            this.item_tex = item_tex;
        }


    }


}



