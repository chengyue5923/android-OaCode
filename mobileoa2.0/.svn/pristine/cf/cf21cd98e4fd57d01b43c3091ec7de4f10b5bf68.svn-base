package com.idxk.mobileoa.model.bean;

/**
 * Created by lenovo on 2015/3/30.
 */
public class SourceInfo extends BaseModel {
    public int feed_id;
    public int uid;
    public String publish_time;
    public int comment_count = 0;
    public int digg_count = 0;
    public int is_digg = 0;
    public String channel_id = "";
    String uname;
    String feed_content;

    public String getChannelName() {
        if (channel_id.equals("1")) {
            return "分享";
        }

        if (channel_id.equals("2")) {
            return "日志";
        }
        if (channel_id.equals("3")) {
            return "指令";
        }
        if (channel_id.equals("4")) {
            return "审批";
        }
        return "";
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(int feed_id) {
        this.feed_id = feed_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(int digg_count) {
        this.digg_count = digg_count;
    }

    public int getIs_digg() {
        return is_digg;
    }

    public void setIs_digg(int is_digg) {
        this.is_digg = is_digg;
    }

    public String getFeed_content() {
        return feed_content;
    }

    public void setFeed_content(String feed_content) {
        this.feed_content = feed_content;
    }
}
