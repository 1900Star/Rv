package com.yibao.recyclerviewdemo;

import java.util.List;

/**
 * @ Author: Luoshipeng
 * @ Name:   Beans
 * @ Email:  strangermy98@gmail.com
 * @ Time:   2018/5/31/ 1:01
 * @ Des:    //TODO
 */
public class Beans {
    private int itemType;

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    private List<String> imageList;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
