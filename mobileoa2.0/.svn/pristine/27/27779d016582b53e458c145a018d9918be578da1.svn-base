package com.idxk.mobileoa.logic.controller;

import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.model.bean.AppToastBean;
import com.idxk.mobileoa.model.bean.BaseModel;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.ConnectTool;

import java.util.HashMap;

/**
 * 应用提醒的控制器
 */
public class NoticeController {
    public static NoticeController getInstance() {
        return SingleClearCach.instance;
    }

    public void getAppToasList(ViewNetCallBack netCallback, int staues) {
        HashMap<String, Object> hm = new HashMap<>();
        if (staues >= 0 && staues <= 1) {
            hm.put("status", staues);
        }else{
            hm.put("status","all");
        }
        try {
            ConnectTool.httpRequest(
                    HttpConfig.outNotice,
                    hm, netCallback, AppToastBean.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    public void readedToast(ViewNetCallBack netCallback, String id) {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("id", id);
        hm.put("status", 1);
        try {
            ConnectTool.httpRequest(
                    HttpConfig.noticeRead,
                    hm, netCallback, BaseModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    static class SingleClearCach {
        static NoticeController instance = new NoticeController();
    }

}
