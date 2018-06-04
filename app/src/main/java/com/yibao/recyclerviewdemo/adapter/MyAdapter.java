package com.yibao.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Beans> mList;
    private Context mContext;
    private RecyclerView.ViewHolder holder = null;
    private View mView;

    public MyAdapter(Context context, List<Beans> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        System.out.println("=============onCreateViewHolder");
        switch (viewType) {
            case 1:
                mView = LayoutInflater.from(mContext).inflate(R.layout.a_view, parent, false);
                holder = new HolderC(mView);
                break;
            case 2:
                mView = LayoutInflater.from(mContext).inflate(R.layout.b_view, parent, false);
                holder = new HolderA(mView);
                break;
            case 3:
                mView = LayoutInflater.from(mContext).inflate(R.layout.c_view, parent, false);
                holder = new HolderB(mView);
                break;
            default:
                break;
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        System.out.println("=============onBindViewHolder");
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
    public int getItemViewType(int position) {
//        System.out.println("=============getItemViewType");
        return mList.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
//        System.out.println("=============getItemCount");

        return 3;
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
