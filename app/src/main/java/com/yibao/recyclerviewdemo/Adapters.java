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

public class Adapters extends RecyclerView.Adapter<Adapters.Holder> {
    private int count;

    public Adapters(int count) {
        this.count = count;
    }

    @Override
    public Adapters.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.b_view, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(Adapters.Holder holder, int position) {

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
