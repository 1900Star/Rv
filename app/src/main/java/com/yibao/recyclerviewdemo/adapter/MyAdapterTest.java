package com.yibao.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yibao.recyclerviewdemo.Beans;
import com.yibao.recyclerviewdemo.R;
import com.yibao.recyclerviewdemo.view.BannerView;
import com.yibao.recyclerviewdemo.view.ContentView;
import com.yibao.recyclerviewdemo.view.InfoView;

import java.util.List;


/**
 * @项目名： RecyclerViewDemo
 * @包名： com.yibao.recyclerviewdemo
 * @文件名: Adapters
 * @author: Stran
 * @Email: www.strangermy@outlook.com / www.strangermy98@gmail.com
 * @创建时间: 2018/3/26 17:20
 * @描述： {TODO}
 */

public class MyAdapterTest extends BaseRvAdapter<Beans> {
    private RecyclerView.ViewHolder holder = null;

    public MyAdapterTest(Context context, List<Beans> list) {
        super(context, list);
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view, Beans beans) {
        if (beans.getItemType() == 1) {
            holder = new HolderC(view);

        } else if (beans.getItemType() == 2) {
            holder = new HolderA(view);

        } else if (beans.getItemType() == 3) {
            holder = new HolderB(view);

        }
        return holder;
    }


    @Override
    protected void bindView(RecyclerView.ViewHolder holder, Beans beans) {
        int position = holder.getAdapterPosition();
        if (holder instanceof HolderC) {
            HolderC holderC = (HolderC) holder;
            BannerView bannerView = holderC.mBannerView;
            if (bannerView != null) {
                bannerView.setData(mList.get(position).getImageList());
            }
        } else if (holder instanceof HolderA) {
            HolderA holderA = (HolderA) holder;
            holderA.mInfoView.setData(mList.get(position).getImageList());
        } else if (holder instanceof HolderB) {
            HolderB holderB = (HolderB) holder;
            holderB.mContentView.setData(mList.get(position).getImageList());


        }
    }


    @Override
    protected int getLayoutId(Beans songType) {
        int layoutId = 0;
        for (int i = 1; i < mList.size(); i++) {
        }
        if (songType.getItemType() == 1) {
            layoutId = R.layout.a_view;

        } else if (songType.getItemType() == 2) {
            layoutId = R.layout.b_view;

        } else if (songType.getItemType() == 3) {

            layoutId = R.layout.c_view;
        }
        return layoutId;
    }


    static class HolderC extends RecyclerView.ViewHolder {
        BannerView mBannerView;

        public HolderC(View itemView) {
            super(itemView);
            mBannerView = itemView.findViewById(R.id.banner_view);
        }
    }

    static class HolderA extends RecyclerView.ViewHolder {

        InfoView mInfoView;

        public HolderA(View itemView) {
            super(itemView);
            mInfoView = itemView.findViewById(R.id.info_view);
        }
    }

    static class HolderB extends RecyclerView.ViewHolder {

        ContentView mContentView;

        public HolderB(View itemView) {
            super(itemView);
            mContentView = itemView.findViewById(R.id.content_view);

        }
    }
}
