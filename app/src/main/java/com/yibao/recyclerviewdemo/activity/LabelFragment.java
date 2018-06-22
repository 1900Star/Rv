package com.yibao.recyclerviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yibao.recyclerviewdemo.ContentAdapter;
import com.yibao.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   LabelFragment
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/6/16/ 10:15
 * @ Des:    TODO
 */
public class LabelFragment extends Fragment {
    private View view;
    private RecyclerView mLabelRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_label, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        if (getArguments() != null) {
            int label = getArguments().getInt("label");
            ContentAdapter adapter = new ContentAdapter(getActivity(), getContentList(label));
            GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
            mLabelRecyclerView.setLayoutManager(manager);
            mLabelRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


    }

    private List<String> getContentList(int type) {
        List<String> contentList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            if (type % 2 == 0) {
                String st = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=79d11a1f0224ab18f41be9775c8283a1/b64543a98226cffc35a0408bb2014a90f603ea05.jpg";
                String s = "http://img.zcool.cn/community/01d71e554bb56b000001bf7274a240.jpg";
                String str = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=91f762232334349b600b66c5a09270a2/30adcbef76094b36187598d4a8cc7cd98d109d20.jpg";
                String ss = "http://imgsrc.baidu.com/imgad/pic/item/8cb1cb13495409234ab8a9449958d109b3de4933.jpg";
                String dd = "http://pic.qiantucdn.com/58pic/26/22/21/58c8db915ebaa_1024.jpg";
                String bb = "http://pic28.photophoto.cn/20130823/0035035065390036_b.jpg";
                String cc = "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=55689fe7dd2a6059461de959405d5eee/242dd42a2834349b075c1dabc3ea15ce36d3be41.jpg";
                String aa = "http://www.quimg.com/via/user/5_20160729122014654.jpg";
                contentList.add(s);
                contentList.add(st);
                contentList.add(ss);
                contentList.add(str);
                contentList.add(ss);
                contentList.add(dd);
                contentList.add(bb);
                contentList.add(cc);
                contentList.add(ss);
                contentList.add(aa);
            } else {
                String tt = "http://5b0988e595225.cdn.sohucs.com/images/20180421/d0b35cb030fd4fcb905c1043349b2439.jpeg";
                String oo = "http://img3.imgtn.bdimg.com/it/u=2423163533,2396542910&fm=27&gp=0.jpg";
                String pp = "http://img5q.duitang.com/uploads/item/201502/10/20150210193233_Ccidc.thumb.700_0.jpeg";
                contentList.add(tt);
                contentList.add(oo);
                contentList.add(pp);
            }
        }

        return contentList;

    }

    private void initView(View view) {

        mLabelRecyclerView = view.findViewById(R.id.label_recycler_view);

    }

    public static LabelFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("label", type);
        LabelFragment labelFragment = new LabelFragment();
        labelFragment.setArguments(bundle);
        return labelFragment;
    }

}
