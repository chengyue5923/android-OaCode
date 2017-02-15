package com.idxk.mobileoa.logic.controller;

import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.model.bean.PraiseResultModel;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.ConnectTool;

import java.util.HashMap;

/**
 * Created by lenovo on 2015/4/14.
 */
public class JPushController {
    public static JPushController getInstance() {
        return SingleClearCach.instance;
    }

    public void bindBaiDuPush(ViewNetCallBack viewNetCallBack, HashMap<String, Object> hashMap) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.bindBaiDuPush,
                    hashMap, viewNetCallBack, PraiseResultModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    public void unBindBaiDuPush(ViewNetCallBack viewNetCallBack, HashMap<String, Object> hashMap) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.unBindBaiDuPush,
                    hashMap, viewNetCallBack, PraiseResultModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    static class SingleClearCach {
        static JPushController instance = new JPushController();
    }
}
