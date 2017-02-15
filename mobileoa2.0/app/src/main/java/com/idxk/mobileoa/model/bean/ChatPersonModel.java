package com.idxk.mobileoa.model.bean;

/**
 * Created by lenovo on 2015/5/29.
 */
public class ChatPersonModel extends BaseModel {
    private String picUrl;
    private String personName;
    private String content;
    private String Time;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
