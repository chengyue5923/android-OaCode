package com.idxk.mobileoa.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/3/26.
 */
public class UpLoadFileDataModel implements Serializable {

    String uid;
    String name;
    String save_path;
    String src;
    String attach_id;

    public String getAttach_id() {
        return attach_id;
    }

    public void setAttach_id(String attach_id) {
        this.attach_id = attach_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSave_path() {
        return save_path;
    }

    public void setSave_path(String save_path) {
        this.save_path = save_path;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
