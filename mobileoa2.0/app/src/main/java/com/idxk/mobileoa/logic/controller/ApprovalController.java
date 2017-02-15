package com.idxk.mobileoa.logic.controller;

import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.model.bean.ApprovalTypesBean;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.ConnectTool;

import java.util.HashMap;

/**
 *
 */
public class ApprovalController {

    static class SingleClearCach {
        static ApprovalController instance = new ApprovalController();
    }
    public static ApprovalController getInstance() {
        return SingleClearCach.instance;
    }

    public  void getApprovalType(ViewNetCallBack callBack){

        try {
            ConnectTool.httpRequest(
                    HttpConfig.approvalTypes,
                    new HashMap<String, Object>(), callBack, ApprovalTypesBean.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }


    }



}
