package com.scujcc.dahuo.user;

import java.util.List;

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public class FunctionItem {

    private String title;
    private String subTitle;

    private int imageId;

    public FunctionItem(String title, String subTitle, int imageId) {
        this.title = title;
        this.subTitle = subTitle;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private List<FunctionItem> mFunctionItems;
}
