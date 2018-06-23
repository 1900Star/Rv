package com.yibao.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.bean.UserInfo;

import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   LabelRvAdapter
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/22/ 22:50
 * @ Des:    TODO
 */
public class LabelRvAdapter extends BaseRvAdapter<UserInfo> {
    public LabelRvAdapter(Context context, List<UserInfo> list) {
        super(context, list);
    }

    @Override
    protected void bindView(RecyclerView.ViewHolder holder, final UserInfo userInfo) {
        if (holder instanceof Holder) {
            Holder h = (Holder) holder;
            h.item_tex.setText(userInfo.getUserName());
            h.mRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDetails(userInfo);
                }
            });
        }
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view, UserInfo viewType) {
        return new Holder(view);
    }

    @Override
    protected int getLayoutId(UserInfo songType) {
        return R.layout.item_grid_view;
    }

    private class Holder extends RecyclerView.ViewHolder {

        TextView item_tex;
        LinearLayout mRootView;

        public Holder(View itemView) {
            super(itemView);
            item_tex = itemView.findViewById(R.id.grid_tv);
            mRootView = itemView.findViewById(R.id.grid_view_item);

        }


    }
}
