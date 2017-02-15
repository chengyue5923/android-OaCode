package com.idxk.mobileoa.android.ui.views.dialog.factory;

import android.app.Dialog;
import android.content.Context;
import com.idxk.mobileoa.android.ui.views.dialog.RunProgressDialog;

/**
 * 对话框
 */
public class DialogFacory {

    public static DialogFacory getInstance() {
        return SingleClearCach.instance;
    }

    /**
     * 显示 dilog
     */
    public Dialog createRunDialog(Context context) {

        RunProgressDialog progressDialog = RunProgressDialog.createDialog(context);

        progressDialog.setMessage("数据加载中");
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    static class SingleClearCach {
        static DialogFacory instance = new DialogFacory();
    }

}
