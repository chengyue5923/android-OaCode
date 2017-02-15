package com.idxk.mobileoa.logic.controller;

import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.model.bean.AppListBea;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.JsonParseAsynctask;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/6/4.
 */
public class AppListController {
    public static AppListController getInstance() {
        return SingleClearCach.instance;
    }

    public void appList(ViewNetCallBack viewNetCallBack, HashMap<String, Object> hashMap) {
//        try {
//            ConnectTool.httpRequest(
//                    HttpConfig.appList,
//                    hashMap, viewNetCallBack, AppListBea.class);
//        } catch (Exception e) {
//            Logger.e(e.getLocalizedMessage(), e);
//        }
        JsonParseAsynctask jsonParseAsynctask = new JsonParseAsynctask(HttpConfig.appList,
                hashMap, viewNetCallBack, AppListBea.class);
        jsonParseAsynctask.execute();
    }

    static class SingleClearCach {
        static AppListController instance = new AppListController();
    }


}
