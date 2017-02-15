package com.idxk.mobileoa.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/3/26.
 */
public class UploadFileModel implements Serializable {


    int status;
    UpLoadFileDataModel data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UpLoadFileDataModel getData() {
        return data;
    }

    public void setData(UpLoadFileDataModel data) {
        this.data = data;
    }
}
