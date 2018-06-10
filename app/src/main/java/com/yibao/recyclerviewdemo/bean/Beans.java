package com.yibao.recyclerviewdemo.bean;

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
    private List<String> imageList;
    private List<String> contentList;

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }


    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
