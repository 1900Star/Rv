package com.yibao.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class AdapterRv extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Beans> mList;
    private Context mContext;
    private RecyclerView.ViewHolder holder = null;

    public AdapterRv(Context context, List<Beans> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                View vp = LayoutInflater.from(mContext).inflate(R.layout.item_vp, parent, false);
                holder = new HolderC(vp);
                break;
            case 2:
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_rv, parent, false);
                holder = new HolderA(inflate);
                break;
            case 3:
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_b, parent, false);
                holder = new HolderB(view);
                break;
            default:
                break;
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderC) {
            HolderC holderC = (HolderC) holder;
            VpAdapter vpAdapter = new VpAdapter(mContext);
            holderC.vp.setAdapter(vpAdapter);

        } else if (holder instanceof HolderA) {
            HolderA holderA = (HolderA) holder;
        } else if (holder instanceof HolderB) {
            HolderB holderB = (HolderB) holder;
            holderB.mTv.setText("热闹标题");
            ContentAdapter contentAdapter = new ContentAdapter(30);
            GridLayoutManager manager = new GridLayoutManager(mContext, 2);
            holderB.mRv.setLayoutManager(manager);
            holderB.mRv.setAdapter(contentAdapter);
            contentAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class HolderC extends RecyclerView.ViewHolder {
        ViewPager vp;

        public HolderC(View itemView) {
            super(itemView);
            vp = itemView.findViewById(R.id.vp);
        }
    }

    static class HolderA extends RecyclerView.ViewHolder {

        public HolderA(View itemView) {
            super(itemView);
        }
    }

    static class HolderB extends RecyclerView.ViewHolder {

        RecyclerView mRv;
        TextView mTv;

        public HolderB(View itemView) {
            super(itemView);
            mRv = itemView.findViewById(R.id.item_rv);
            mTv = itemView.findViewById(R.id.tv_title);
        }
    }
}
