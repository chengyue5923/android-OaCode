package com.idxk.mobileoa.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/4/1.
 */
public class SendRangesBean implements Serializable {
    List<SendRangebean> list;

    public List<SendRangebean> getList() {
        return list;
    }

    public void setList(List<SendRangebean> list) {
        this.list = list;
    }
}
