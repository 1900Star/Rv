package com.yibao.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yibao.recyclerviewdemo.adapter.BaseRvAdapter;

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

public class ContentAdapterTest extends BaseRvAdapter<String> {

    public ContentAdapterTest(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    protected void bindView(RecyclerView.ViewHolder h, String s) {
        Holder holder = (Holder) h;
        Glide.with(mContext).load(s).placeholder(R.mipmap.a).error(R.mipmap.a).into(holder.mIvHeader);
        Glide.with(mContext).load(s).placeholder(R.mipmap.a).error(R.mipmap.a).into(holder.mBigIv);


    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view, String type) {
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    @Override
    protected int getLayoutId(String songType) {
        return R.layout.b_tiem;
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
