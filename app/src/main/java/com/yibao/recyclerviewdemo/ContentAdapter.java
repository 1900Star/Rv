package com.yibao.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.Holder> {
    private Context mContext;
    private List<String> mList;

    public ContentAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ContentAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.b_tiem, null);

        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(ContentAdapter.Holder holder, int position) {
        if (position % 2 == 0) {
            Glide.with(mContext).load(mList.get(0)).placeholder(R.mipmap.a).error(R.mipmap.a).into(holder.mIvHeader);
        } else {

            Glide.with(mContext).load(mList.get(1)).placeholder(R.mipmap.a).error(R.mipmap.a).into(holder.mBigIv);
        }

    }

    @Override
    public int getItemCount() {
        return mList != null ? 30 : 0;
    }

    static class Holder extends RecyclerView.ViewHolder {

        ImageView mBigIv;
        ImageView mIvHeader;

        public Holder(View itemView) {
            super(itemView);
            mBigIv = itemView.findViewById(R.id.iv_big);
            mIvHeader = itemView.findViewById(R.id.iv_header);
        }
    }
}
