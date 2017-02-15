package com.idxk.mobileoa.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.android.ui.activity.LoginActivity;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;

/**
 * Created by Administrator on 2015/3/31.
 */
public class NeedLoginReceiver  extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
    String action = context.getResources().getString(R.string.needLoginAction);
        if (intent.getAction().equals(action)){
            //登陆
            PreferceManager.getInsance().saveValueBYkey("oauth_token", "", context);
            PreferceManager.getInsance().saveValueBYkey("oauth_token_secret", "", context);
            PreferceManager.getInsance().saveValueBYkey("uid", "", context);
            MobileApplication.getInstance().clearActivities();
            Intent intent1 = new Intent(context, LoginActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);

        }

    }
}
