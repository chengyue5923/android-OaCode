package com.idxk.mobileoa.android.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;

import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.mogujie.tt.DB.sp.LoginSp;
import com.mogujie.tt.DB.sp.SystemConfigSp;
import com.mogujie.tt.config.UrlConstant;
import com.mogujie.tt.imservice.entity.RecentInfo;
import com.mogujie.tt.imservice.event.LoginEvent;
import com.mogujie.tt.imservice.event.SocketEvent;
import com.mogujie.tt.imservice.manager.IMLoginManager;
import com.mogujie.tt.imservice.service.IMService;
import com.mogujie.tt.imservice.support.IMServiceConnector;
import com.mogujie.tt.utils.IMUIHelper;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by lenovo on 2015/3/4.
 */
public class HomeNewSunActivity extends HomeNewActivity {
    String mail = "";
    private IMService imService;
    private boolean loginSuccess = false;
    private IMServiceConnector imServiceConnector = new IMServiceConnector() {
        @Override
        public void onServiceDisconnected() {
        }

        @Override
        public void onIMServiceConnected() {
            logger.d("login#onIMServiceConnected");
            imService = imServiceConnector.getIMService();
            if (imService == null) {
                return;
            }
            IMLoginManager loginManager = imService.getLoginManager();
            LoginSp loginSp = imService.getLoginSp();
            if (loginManager == null || loginSp == null) {
                // 无法获取登陆控制器
                return;
            }
            Logger.e(">>>>>>SpLoginIdentity" + mail);
            String[] strings=mail.split("@");
            mail=strings[0];

            Logger.e(">>>>>>SpLoginIdentity" + mail);
            if (mail.equals("dongjiming")){
                mail="A";
            }else{
                mail="liyuping";
            }
            LoginSp.SpLoginIdentity loginIdentity = loginSp.getLoginIdentity();
            if (loginIdentity == null) {
                // 之前没有保存任何登陆相关的，跳转到登陆页面
                MobileApplication.getInstance().talkHasLogin = true;
//                imService.getLoginManager().login(mail.replace("@", "$"), "zhongmintouoa");
                imService.getLoginManager().login(mail, "123456");
            } else {
//                MobileApplication.getInstance().talkHasLogin = true;
//                imService.getLoginManager().login(loginIdentity);
                Logger.e(">>>>>>SpLoginIdentity" + mail);
                MobileApplication.getInstance().talkHasLogin = true;
//                imService.getLoginManager().login(mail.replace("@", "$"), "zhongmintouoa");
                imService.getLoginManager().login(mail, "123456");
            }

//            onRecentContactDataReady();
        }
    };

    private void onRecentContactDataReady() {
        Logger.e("get recent list run");
        boolean isUserData = imService.getContactManager().isUserDataReady();
        boolean isSessionData = imService.getSessionManager().isSessionListReady();
        boolean isGroupData = imService.getGroupManager().isGroupReady();

        if (!(isUserData && isSessionData && isGroupData)) {
            return;
        }
        List<RecentInfo> recentSessionList = imService.getSessionManager().getRecentListInfo();
        iFreshChatRecentInfo.freshChatRecentInfo(recentSessionList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileApplication.getInstance().addActivity(this);
        mail = PreferceManager.getInsance().getValueBYkey(this, "mail");
        if (StringTools.isNullOrEmpty(mail)) {
            return;
        }
        startConnect();

    }

    private void startConnect() {
        Logger.e("startConnect is run");
        if (MobileApplication.getInstance().talkHasLogin) {
            return;
        }
        SystemConfigSp.instance().init(getApplicationContext());
        if (TextUtils.isEmpty(SystemConfigSp.instance().getStrConfig(SystemConfigSp.SysCfgDimension.LOGINSERVER))) {
            SystemConfigSp.instance().setStrConfig(SystemConfigSp.SysCfgDimension.LOGINSERVER, UrlConstant.ACCESS_MSG_ADDRESS);
        }
        Logger.e("startConnect is run connect");
        imServiceConnector.connect(HomeNewSunActivity.this);
        EventBus.getDefault().register(this);
    }


    public void startImLogin(String emil) {
        String m = PreferceManager.getInsance().getValueBYkey(this, "mail");
        if (StringTools.isNullOrEmpty(m)) {
            return;
        }
        mail = emil;
        startConnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imServiceConnector.disconnect(HomeNewSunActivity.this);
        EventBus.getDefault().unregister(this);
    }

    /**
     * ----------------------------event 事件驱动----------------------------
     */
    public void onEventMainThread(LoginEvent event) {
        switch (event) {
            case LOCAL_LOGIN_SUCCESS:
            case LOGIN_OK:
                onLoginSuccess();
                break;
            case LOGIN_AUTH_FAILED:
            case LOGIN_INNER_FAILED:
                if (!loginSuccess)
                    onLoginFailure(event);
                break;
        }
    }

    public void onEventMainThread(SocketEvent event) {
        switch (event) {
            case CONNECT_MSG_SERVER_FAILED:
            case REQ_MSG_SERVER_ADDRS_FAILED:
                onSocketFailure(event);
                break;
        }
    }

    private void onSocketFailure(SocketEvent event) {
        Logger.e("login#onLoginError -> errorCode:" + event.name());
        String errorTip = getString(IMUIHelper.getSocketErrorTip(event));
        Logger.d("login#errorTip:" + errorTip);
    }

    private void onLoginSuccess() {
        loginSuccess = true;
        com.idxk.mobileoa.utils.common.android.Logger.e("LoginSuccess");
//        onRecentContactDataReady();
    }

    private void onLoginFailure(LoginEvent event) {
        Logger.e("login#onLoginError -> errorCode:" + event.name());
        String errorTip = getString(IMUIHelper.getLoginErrorTip(event));
        Logger.d("login#errorTip:" + errorTip);
    }


}
