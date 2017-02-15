package com.idxk.mobileoa.model.bean;

import com.idxk.mobileoa.config.parse.UrlRes;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/6/15.
 */
public class ApprovalTypesBean implements Serializable {
    int id;
    String name;

    String input_url;
    String view_url;
    String print_url;

    List<ApprovalTypesBean> sub;

    public String getInput_url() {
        return UrlRes.getInstance().getUrlSever() + input_url;
    }

    public void setInput_url(String input_url) {
        this.input_url = input_url;
    }

    public String getView_url() {
        return view_url;
    }

    public void setView_url(String view_url) {
        this.view_url = view_url;
    }

    public String getPrint_url() {
        return print_url;
    }

    public void setPrint_url(String print_url) {
        this.print_url = print_url;
    }

    //
    public List<ApprovalTypesBean> getList() {
        return sub;
    }

    public void setList(List<ApprovalTypesBean> list) {
        this.sub = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
