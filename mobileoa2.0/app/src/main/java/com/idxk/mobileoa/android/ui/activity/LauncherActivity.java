package com.idxk.mobileoa.android.ui.activity;

import android.app.Notification;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushManager;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.umeng.update.UmengUpdateAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/24.
 */
public class LauncherActivity extends BaseActivity {

    ImageView allLayout;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_launcher);
        allLayout = (ImageView) id2v(R.id.allLayout);

    }

    @Override
    protected void initData() {
        String token = PreferceManager.getInsance().getValueBYkey(LauncherActivity.this, "oauth_token");
        startFrist();
        if (token != null && !"".equals(token)) {
            IntentTool.homePage(LauncherActivity.this);
        }
        else {

            Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        LauncherActivity.this.finish();
    }

    private void startFrist() {
        Resources resource = this.getResources();
        String pkgName = this.getPackageName();
        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
                resource.getIdentifier(
                        "notification_custom_builder", "layout", pkgName),
                resource.getIdentifier("notification_icon", "id", pkgName),
                resource.getIdentifier("notification_title", "id", pkgName),
                resource.getIdentifier("notification_text", "id", pkgName));
        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
        cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
        cBuilder.setLayoutDrawable(resource.getIdentifier(
                "simple_notification_icon", "drawable", pkgName));
        cBuilder.setNotificationSound(Uri.withAppendedPath(
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
        // 推送高级设置，通知栏样式设置为下面的ID
        PushManager.setNotificationBuilder(this, 1, cBuilder);


        List<String> list = new ArrayList<String>();
        list.add("zmt");
        PushManager.setTags(this, list);
        PushManager.bindGroup(this, "");
        UmengUpdateAgent.update(this);
        UmengUpdateAgent.setUpdateOnlyWifi(false);
    }

}
