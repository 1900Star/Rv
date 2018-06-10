package com.yibao.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

public class FlagViewAdapter extends RecyclerView.Adapter<FlagViewAdapter.Holder> {
    private Context mContext;
    private List<String> mList;

    public FlagViewAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public FlagViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_view, null);

        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FlagViewAdapter.Holder holder, int position) {
        Glide.with(mContext).load(mList.get(position)).placeholder(R.mipmap.a).error(R.mipmap.a).into(holder.mGridItemIv);

    }

    @Override
    public int getItemCount() {
        return mList != null ? 8 : 0;
    }

    static class Holder extends RecyclerView.ViewHolder {

        ImageView mGridItemIv;
        TextView mGridItemTv;

        public Holder(View itemView) {
            super(itemView);
            mGridItemIv = itemView.findViewById(R.id.grid_iv);
            mGridItemTv = itemView.findViewById(R.id.grid_tv);
        }
    }
}
