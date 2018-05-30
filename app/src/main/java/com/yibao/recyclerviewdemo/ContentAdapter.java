package com.yibao.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
    private int count;

    public ContentAdapter(int count) {
        this.count = count;
    }

    @Override
    public ContentAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.b_tiem, null);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(ContentAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return count;
    }

    static class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
