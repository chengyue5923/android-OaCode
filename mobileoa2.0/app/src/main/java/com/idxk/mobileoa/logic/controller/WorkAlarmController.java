package com.idxk.mobileoa.logic.controller;

import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.model.bean.MessageAlarmModel;
import com.idxk.mobileoa.model.bean.MessageReceivedListItemBean;
import com.idxk.mobileoa.model.bean.WaitForDealWithNumModel;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.ConnectTool;
import com.idxk.mobileoa.utils.net.connect.http.JsonParseAsynctask;

import java.util.HashMap;

/**
 * Created by lenovo on 2015/3/24.
 */
public class WorkAlarmController {
    public static WorkAlarmController getInstance() {
        return SingleClearCach.instance;
    }

    public void getWaitForDealWithNumber(ViewNetCallBack listener) {
//        try {
//            ConnectTool.httpRequest(
//                    HttpConfig.getWaitForDealWithNumber,
//                    new HashMap<String, Object>(), listener, WaitForDealWithNumModel.class);
//
//        } catch (Exception e) {
//
//        }

        JsonParseAsynctask jsonParseAsynctask = new JsonParseAsynctask(HttpConfig.getWaitForDealWithNumber,
                new HashMap<String, Object>(), listener, WaitForDealWithNumModel.class);
        jsonParseAsynctask.execute();
    }

    public void getMessageReceived(ViewNetCallBack netCallBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getMessageReceived,
                    new HashMap<String, Object>(), netCallBack, MessageReceivedListItemBean.class);

        } catch (Exception e) {

        }
    }

    public void getMessageSend(ViewNetCallBack netCallBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getMessageSend,
                    new HashMap<String, Object>(), netCallBack, MessageReceivedListItemBean.class);

        } catch (Exception e) {

        }
    }

    public void getMessageAlarmNumber(ViewNetCallBack netCallBack) {
//        try {
//            ConnectTool.httpRequest(
//                    HttpConfig.getMessageAlarmNumber,
//                    new HashMap<String, Object>(), netCallBack, MessageAlarmModel.class);
//        } catch (Exception e) {
//
//        }

        JsonParseAsynctask jsonParseAsynctask = new JsonParseAsynctask(HttpConfig.getMessageAlarmNumber,
                new HashMap<String, Object>(), netCallBack, MessageAlarmModel.class);
        jsonParseAsynctask.execute();
    }


    static class SingleClearCach {
        static WorkAlarmController instance = new WorkAlarmController();
    }
}
