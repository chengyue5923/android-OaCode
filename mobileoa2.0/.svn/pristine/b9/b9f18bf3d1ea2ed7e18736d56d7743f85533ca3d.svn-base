package com.idxk.mobileoa.android.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.android.pushservice.PushManager;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.android.ui.activity.HomeNewSunActivity;
import com.idxk.mobileoa.android.ui.activity.LoginActivity;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.BaiDuPushController;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.PersonBean;
import com.idxk.mobileoa.model.bean.PraiseResultModel;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.file.ImageLoaderManager;
import com.mogujie.tt.imservice.event.UserInfoEvent;
import com.mogujie.tt.imservice.service.IMService;
import com.mogujie.tt.imservice.support.IMServiceConnector;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;

import de.greenrobot.event.EventBus;

/**
 * Created by lenovo on 2015/3/4.
 */
public class MyFragment extends Fragment implements ViewNetCallBack, View.OnClickListener {

    private static MyFragment instance;
    ImageView personImage;
    TextView nickName, inforS;
    TextView department;
    View editeView;
    PersonBean info;
    View view, signatureLayout;
    private TextView logout;

    private IMService imService;
    private IMServiceConnector imServiceConnector = new IMServiceConnector() {
        @Override
        public void onServiceDisconnected() {
            if (EventBus.getDefault().isRegistered(MyFragment.this)) {
                EventBus.getDefault().unregister(MyFragment.this);
            }
        }

        @Override
        public void onIMServiceConnected() {
            imService = imServiceConnector.getIMService();
        }
    };

    public static MyFragment getInstance() {
        if (instance == null) {
            instance = new MyFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person, null);
        ToastTool.showDev("MyFragment");
        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imServiceConnector.connect(getActivity());
        EventBus.getDefault().register(this);

    }

    @Override
    public void onResume() {
        super.onResume();
//        imServiceConnector.connect(getActivity());
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    void initView() {
        personImage = (ImageView) view.findViewById(R.id.avatar);
        signatureLayout = view.findViewById(R.id.signatureLayout);
        personImage.setOnClickListener(this);
        nickName = (TextView) view.findViewById(R.id.NickName);
        inforS = (TextView) view.findViewById(R.id.inforS);
        department = (TextView) view.findViewById(R.id.department);
        editeView = view.findViewById(R.id.editLayout);
        editeView.setOnClickListener(this);
        logout = (TextView) view.findViewById(R.id.logout);
        logout.setOnClickListener(this);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        String userId = PreferceManager.getInsance().getValueBYkey(getActivity(), "uid");
        UserController.getInstance().getPersonInfor(this, Integer.valueOf(userId));
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onData(Serializable result, boolean b, Object o) {
        info = (PersonBean) result;
        Logger.e("头像=" + info.getAvatar_middle());
        Logger.e("部门=" + info.getAvatar_original());
        Logger.e("账号=" + info.getLogin());
        Logger.e("职位=" + info.getProfile().getIntro().getValue());
        inforS.setText(StringTools.toTrim(info.getIntro()));
        signatureLayout.setTag(StringTools.toTrim(info.getIntro()));
        String loginInfo = info.getLogin();
        PreferceManager.getInsance().saveValueBYkey("login_email", loginInfo.substring(0, loginInfo.indexOf("@")), getActivity());
        Logger.e(loginInfo.substring(0, loginInfo.indexOf("@")));
        Logger.e("--");
//        Picasso.with(getActivity()).load(info.getAvatar_middle()).into(personImage);
        ImageLoaderManager.getInstance().disPlayImage(info.getAvatar_middle(), personImage);
        nickName.setText(info.getUname());
        if (ListUtil.isNullOrEmpty(info.getUser_department())) {
            department.setVisibility(View.GONE);
        } else {
            department.setVisibility(View.VISIBLE);
            department.setText(info.getUser_department().get(0));
        }

        if (ListUtil.isNullOrEmpty(info.getUser_department()) || ListUtil.isNullOrEmpty(info.getUser_department_id())) {
            Logger.e("id 和name 两者有一个为空");
        } else {
            PreferceManager.getInsance().saveValueBYkey("dId", "d_" + info.getUser_department_id().get(0), getActivity());
            PreferceManager.getInsance().saveValueBYkey("dName", info.getUser_department().get(0), getActivity());
        }
        String mail = PreferceManager.getInsance().getValueBYkey(getActivity(), "mail");
        if (StringTools.isNullOrEmpty(mail)) {
            PreferceManager.getInsance().saveValueBYkey("mail", info.getLogin(), getActivity());
            if (MobileApplication.getInstance().talkHasLogin) {
                return;
            }
            ((HomeNewSunActivity) getActivity()).startImLogin(info.getLogin());
        }
    }

    @Override
    public void onClick(View v) {
//        if (info != null) {
//            IntentTool.startEditeActivity(getActivity(), info);
//        }

        switch (v.getId()) {
            case R.id.logout:

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                unBind();
                PushManager.stopWork(getActivity());
                /**
                 * teamTalk
                 */
                imService.getLoginManager().setKickout(false);
                imService.getLoginManager().logOut();
                MobileApplication.getInstance().talkHasLogin = false;
                MobileApplication.getInstance().clearActivities();
                getActivity().finish();
                startActivity(intent);
                break;
            case R.id.avatar:
                setTargetFragment(this, IConstant.CutRescode);
                if (info != null) {
                    IntentTool.startSelectImageActivity(getActivity(), true, info.getAvatar_original());
                }
                break;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
//        imServiceConnector.disconnect(getActivity());
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        imServiceConnector.disconnect(getActivity());
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    public void freshIcon(String path) {
        Picasso.with(getActivity()).load(path).into(personImage);
    }

    public void infor(String content) {
        inforS.setText(StringTools.toTrim(content));
        signatureLayout.setTag(StringTools.toTrim(content));
    }


    public void unBind() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("user_id", PreferceManager.getInsance().getValueBYkey(getActivity(), "baiDuPushUserId"));
        hashMap.put("channel_id", PreferceManager.getInsance().getValueBYkey(getActivity(), "baiDuPushChannelId"));


        BaiDuPushController.getInstance().unBindBaiDuPush(new ViewNetCallBack() {
            @Override
            public void onConnectStart() {

            }

            @Override
            public void onConnectEnd() {
                PreferceManager.getInsance().saveValueBYkey("oauth_token", "", getActivity());
                PreferceManager.getInsance().saveValueBYkey("oauth_token_secret", "", getActivity());
                PreferceManager.getInsance().saveValueBYkey("uid", "", getActivity());
                PreferceManager.getInsance().saveValueBYkey("mail", "", getActivity());
                PreferceManager.getInsance().saveValueBYkey("login_email", "", getActivity());
            }

            @Override
            public void onFail(Exception e) {

            }

            @Override
            public void onData(Serializable result, boolean fromNet, Object o) {
                PraiseResultModel praiseResultModel = (PraiseResultModel) result;
                Logger.e("++++++++++++++++++++++++++++++++++++++" + praiseResultModel.getSuccess());
            }
        }, hashMap);
    }

    public void onEventMainThread(UserInfoEvent event) {
        switch (event) {
            case USER_INFO_OK:
                Logger.e("user info load ok");
        }
    }


}
