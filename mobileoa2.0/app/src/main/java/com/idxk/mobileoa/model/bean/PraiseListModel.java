package com.idxk.mobileoa.model.bean;

/**
 * Created by lenovo on 2015/4/1.
 */
public class PraiseListModel extends BaseModel {
    private int id;
    private int uid;
    private int feed_id;
    private String cTime;
    private PraiseListUserInfo userinfo;

    private String message;

    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(int feed_id) {
        this.feed_id = feed_id;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public PraiseListUserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(PraiseListUserInfo userinfo) {
        this.userinfo = userinfo;
    }
}
