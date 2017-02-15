package com.idxk.mobileoa.logic.controller;

import android.content.Context;
import android.graphics.Bitmap;

import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.file.UpdateTask;

/**
 * Created by lenovo on 2015/3/25.
 */
public class CommonActionController {

    public static CommonActionController getInstance() {
        return SingleClearCach.instance;
    }

    public void upLoadPicture(ViewNetCallBack listener, String picturePath, Context context) {
        UpdateTask task = new UpdateTask(context, false, HttpConfig.upLoadPicture);
        task.setListener(listener);
        task.execute(picturePath);
    }
    public void upLoadPicture(ViewNetCallBack listener, String picturePath, Context context,Bitmap bm) {
        UpdateTask task = new UpdateTask(context, false, HttpConfig.upLoadPicture);
        task.setListener(listener);
        task.execute(picturePath,bm);
    }
    public void upLoadUserPicture(ViewNetCallBack listener, String picturePath, Context context) {
        Logger.e("===");
        UpdateTask task = new UpdateTask(context, false, HttpConfig.upLoadUserPicture);
        task.setListener(listener);
        task.execute(picturePath);
    }

    static class SingleClearCach {
        static CommonActionController instance = new CommonActionController();
    }
}
