package com.idxk.mobileoa.model.bean;

/**
 * Created by lenovo on 2015/4/9.
 */
public class FeedBackModel extends BaseModel {
    private int type_id;

    private String type_name;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return "FeedBackModel{" +
                "type_id=" + type_id +
                ", type_name='" + type_name + '\'' +
                ", status=" + status +
                '}';
    }
}
