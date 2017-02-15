package com.idxk.mobileoa.model.bean;

/**
 * Created by lenovo on 2015/3/24.
 */
public class WaitForDealWithNumModel extends BaseModel {
    public int worklog_num;
    public int order_rel_num;
    public int approval_rel_num;

    @Override
    public String toString() {
        return "WaitForDealWithNumModel{" +
                "worklog_num=" + worklog_num +
                ", order_rel_num=" + order_rel_num +
                ", approval_rel_num=" + approval_rel_num +
                '}';
    }
}
