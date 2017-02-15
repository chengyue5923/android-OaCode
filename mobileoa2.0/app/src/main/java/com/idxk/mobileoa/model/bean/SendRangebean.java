package com.idxk.mobileoa.model.bean;

import com.idxk.mobileoa.config.enums.DPType;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/4/1.
 */
public class SendRangebean implements Serializable {
    String name;
    DPType type;
    String uid;
    boolean showTitle;

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

    public DPType getType() {
        return type;
    }

    public void setType(DPType type) {
        this.type = type;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }
}
