package com.idxk.mobileoa.model.bean;

import java.util.List;

/**
 * Created by lenovo on 2015/3/25.
 */
public class UpLoadPictureResultModel {
    public int status;
    public List<UpLoadPictureResultData> data;

    @Override
    public String toString() {
        return "UpLoadPictureResultModel{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
