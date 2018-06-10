package com.yibao.recyclerviewdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yibao.recyclerviewdemo.R;

import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   InfoView
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/4/ 23:27
 * @ Des:    //TODO
 */
public class InfoView extends LinearLayout implements View.OnClickListener {

    private ImageView mTopView, mLeftView, mRightView;

    public InfoView(Context context) {
        this(context, null);
    }


    public InfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.item_info_view, this);
        mTopView = view.findViewById(R.id.iv_top);
        mLeftView = view.findViewById(R.id.iv_left);
        mRightView = view.findViewById(R.id.iv_right);
        mTopView.setOnClickListener(this);
        mLeftView.setOnClickListener(this);
        mRightView.setOnClickListener(this);
    }

    public void setData(List<String> list) {
        Glide.with(getContext()).load(list.get(0)).asBitmap().into(mTopView);
        Glide.with(getContext()).load(list.get(1)).asBitmap().into(mLeftView);
        Glide.with(getContext()).load(list.get(2)).asBitmap().into(mRightView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_top:
                System.out.println("11111111111111");
                break;
            case R.id.iv_left:
                System.out.println("22222222222222");
                break;
            case R.id.iv_right:
                System.out.println("3333333333333");
                break;
        }

    }
}
