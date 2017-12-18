package com.scujcc.dahuo.content;

import java.util.List;

/**
 * Created by  范朝波 on 2017/12/17.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public class ContentItem {

    private int photoId;
    private String name;
    private int vip;
    private String startTime;
    private String topic;
    private String content;
    private int nowNum;
    private int tolNum;


    public ContentItem(int photoId, String name, int vip, String startTime, String topic, String content, int nowNum, int tolNum) {
        this.photoId = photoId;
        this.name = name;
        this.vip = vip;
        this.startTime = startTime;
        this.topic = topic;
        this.content = content;
        this.nowNum = nowNum;
        this.tolNum = tolNum;
    }


    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNowNum() {
        return nowNum;
    }

    public void setNowNum(int nowNum) {
        this.nowNum = nowNum;
    }

    public int getTolNum() {
        return tolNum;
    }

    public void setTolNum(int tolNum) {
        this.tolNum = tolNum;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    private List<ContentItem> mContentItems;
}
