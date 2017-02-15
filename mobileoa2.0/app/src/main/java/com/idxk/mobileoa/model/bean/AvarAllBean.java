package com.idxk.mobileoa.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/4/8.
 */
public class AvarAllBean implements Serializable{

    AvarBean data;
    int status;

    public AvarBean getData() {
        return data;
    }

    public void setData(AvarBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
