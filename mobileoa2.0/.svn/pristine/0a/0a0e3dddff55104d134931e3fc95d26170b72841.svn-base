package com.mogujie.tt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mogujie.tt.imservice.service.IMService;
import com.mogujie.tt.utils.IMUIHelper;
import com.mogujie.tt.utils.ImageLoaderUtil;

/**
 * Created by lenovo on 2015/8/11.
 */
public class TTIMManager {
    public static boolean gifRunning = true;//gif是否运行
    private static TTIMManager instance;

    public static TTIMManager getInstance() {
        if (instance == null) {
            instance = new TTIMManager();
        }
        return instance;
    }

    private void startIMService(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, IMService.class);
        context.startService(intent);
    }

    public void init(Context context) {
        startIMService(context);
        ImageLoaderUtil.initImageLoaderConfig(context);
    }


    public void chatMessage(Activity ac, String sessionKey) {
        IMUIHelper.openChatActivity(ac, sessionKey);
    }
}
