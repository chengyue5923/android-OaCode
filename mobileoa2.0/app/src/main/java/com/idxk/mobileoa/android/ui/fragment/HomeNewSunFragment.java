package com.idxk.mobileoa.android.ui.fragment;


import android.os.Bundle;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.android.ui.activity.HomeNewSunActivity;
import com.mogujie.tt.DB.entity.GroupEntity;
import com.mogujie.tt.TTIMManager;
import com.mogujie.tt.imservice.entity.RecentInfo;
import com.mogujie.tt.imservice.event.GroupEvent;
import com.mogujie.tt.imservice.event.ReconnectEvent;
import com.mogujie.tt.imservice.event.SessionEvent;
import com.mogujie.tt.imservice.event.SocketEvent;
import com.mogujie.tt.imservice.event.UnreadEvent;
import com.mogujie.tt.imservice.event.UserInfoEvent;
import com.mogujie.tt.imservice.manager.IMLoginManager;
import com.mogujie.tt.imservice.service.IMService;
import com.mogujie.tt.imservice.support.IMServiceConnector;
import com.mogujie.tt.utils.Logger;
import com.mogujie.tt.utils.NetworkUtil;

import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * Created by lenovo on 2015/3/4.
 */
public class HomeNewSunFragment extends HomeNewFragment implements HomeNewSunActivity.IFreshChatRecentInfo {

    protected static Logger logger = Logger.getLogger(HomeNewSunFragment.class);
    private static HomeNewSunFragment instance;
    private IMService imService;
    private IMServiceConnector imServiceConnector = new IMServiceConnector() {

        @Override
        public void onServiceDisconnected() {
            if (EventBus.getDefault().isRegistered(HomeNewSunFragment.this)) {
                EventBus.getDefault().unregister(HomeNewSunFragment.this);
            }
        }

        @Override
        public void onIMServiceConnected() {
            logger.d("chatfragment#recent#onIMServiceConnected");
            imService = imServiceConnector.getIMService();
            if (imService == null) {
                // why ,some reason
                return;
            }
            // 依赖联系人回话、未读消息、用户的信息三者的状态
            onRecentContactDataReady();
            EventBus.getDefault().registerSticky(HomeNewSunFragment.this);
        }
    };

    public HomeNewSunFragment() {
    }

    public static HomeNewSunFragment getInstance() {
        if (instance == null) {
            instance = new HomeNewSunFragment();
        }
        return instance;
    }

    private void onRecentContactDataReady() {
        boolean isUserData = imService.getContactManager().isUserDataReady();
        boolean isSessionData = imService.getSessionManager().isSessionListReady();
        boolean isGroupData = imService.getGroupManager().isGroupReady();

        if (!(isUserData && isSessionData && isGroupData)) {
            return;
        }

        List<RecentInfo> recentSessionList = imService.getSessionManager().getRecentListInfo();
        chatPersonAdapter.setData(recentSessionList);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imServiceConnector.connect(getActivity());
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(HomeNewSunFragment.this)) {
            EventBus.getDefault().unregister(HomeNewSunFragment.this);
        }
        imServiceConnector.disconnect(getActivity());
        super.onDestroy();
    }

    @Override
    public void itemCLick(RecentInfo recentInfo) {
        if (recentInfo == null) {
            return;
        }
        com.idxk.mobileoa.utils.common.android.Logger.e("getSessionKey" + recentInfo.getSessionKey());
        TTIMManager.getInstance().chatMessage(getActivity(), recentInfo.getSessionKey());
    }

    public void onEventMainThread(SessionEvent sessionEvent) {
        logger.d("chatfragment#SessionEvent# -> %s", sessionEvent);
        switch (sessionEvent) {
            case RECENT_SESSION_LIST_UPDATE:
            case RECENT_SESSION_LIST_SUCCESS:
            case SET_SESSION_TOP:
                onRecentContactDataReady();
                break;
        }
    }

    public void onEventMainThread(GroupEvent event) {
        switch (event.getEvent()) {
            case GROUP_INFO_OK:
            case CHANGE_GROUP_MEMBER_SUCCESS:
                onRecentContactDataReady();
                break;

            case GROUP_INFO_UPDATED:
                onRecentContactDataReady();
                break;
            case SHIELD_GROUP_OK:
                // 更新最下栏的未读计数、更新session
                onShieldSuccess(event.getGroupEntity());
                break;
            case SHIELD_GROUP_FAIL:
            case SHIELD_GROUP_TIMEOUT:
                onShieldFail();
                break;
        }
    }

    // 更新页面以及 下面的未读总计数
    private void onShieldSuccess(GroupEntity entity) {
        if (entity == null) {
            return;
        }
        // 更新某个sessionId
        chatPersonAdapter.updateRecentInfoByShield(entity);
    }

    private void onShieldFail() {
        com.idxk.mobileoa.utils.common.android.Logger.e(getResources().getString(R.string.req_msg_failed));
    }

    public void onEventMainThread(UnreadEvent event) {
        switch (event.event) {
            case UNREAD_MSG_RECEIVED:
            case UNREAD_MSG_LIST_OK:
            case SESSION_READED_UNREAD_MSG:
                onRecentContactDataReady();
                break;
        }
    }

    public void onEventMainThread(ReconnectEvent reconnectEvent) {
        switch (reconnectEvent) {
            case DISABLE: {
                handleServerDisconnected();
            }
            break;
        }
    }

    public void onEventMainThread(UserInfoEvent event) {
        switch (event) {
            case USER_INFO_UPDATE:
            case USER_INFO_OK:
                onRecentContactDataReady();
                break;
        }
    }

    private void handleServerDisconnected() {
        com.idxk.mobileoa.utils.common.android.Logger.e("Received reconnect message");
        if (NetworkUtil.isNetWorkAvalible(getActivity())) {
            com.idxk.mobileoa.utils.common.android.Logger.e(String.valueOf(MobileApplication.getInstance().talkHasLogin));
            IMLoginManager.instance().relogin();
        }
    }

    public void onEventMainThread(SocketEvent socketEvent) {
        switch (socketEvent) {
            case MSG_SERVER_DISCONNECTED:
                handleServerDisconnected();
                break;

            case CONNECT_MSG_SERVER_FAILED:
            case REQ_MSG_SERVER_ADDRS_FAILED:
                handleServerDisconnected();
                break;
        }
    }

    @Override
    public void freshChatRecentInfo(List<RecentInfo> lists) {
        chatPersonAdapter.setData(lists);
    }
}
