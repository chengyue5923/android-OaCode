package com.idxk.mobileoa.logic.controller;

import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.model.bean.FeedBackModel;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.MapBuilder;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.ConnectTool;

import java.util.HashMap;

/**
 * Created by lenovo on 2015/4/9.
 */
public class FeedBackController {

    public static FeedBackController getInstance() {
        return SingleClearCach.instance;
    }

    public void getFeedBackType(ViewNetCallBack viewNetCallBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getFeedBackType,
                    new HashMap<String, Object>(), viewNetCallBack, FeedBackModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    public void sendFeedBackContent(ViewNetCallBack viewNetCallBack, int typeId, String feedBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.sendFeedBackContent,
                    new MapBuilder<String, Object>().add("type_id", typeId)
                            .add("feedback", feedBack)
                            .getUnmodifiableMap(), viewNetCallBack, FeedBackModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }


    static class SingleClearCach {
        static FeedBackController instance = new FeedBackController();
    }
}
