package com.idxk.mobileoa.android.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.android.ui.fragment.AppCenterFragment;
import com.idxk.mobileoa.android.ui.fragment.ColleagueFragment;
import com.idxk.mobileoa.android.ui.fragment.ContactFragment;
import com.idxk.mobileoa.android.ui.fragment.FreshFragment;
import com.idxk.mobileoa.android.ui.fragment.HomeNewSunFragment;
import com.idxk.mobileoa.android.ui.fragment.MyFragment;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.config.constant.NoticeCode;
import com.idxk.mobileoa.logic.controller.CommonActionController;
import com.idxk.mobileoa.model.bean.AvarAllBean;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.StaticContext;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.baidu.Utils;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.mogujie.tt.imservice.entity.RecentInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2015/3/4.
 */
public class HomeNewActivity extends FragmentActivity implements
        ColleagueFragment.ISelectedColleague, View.OnClickListener {


    private static Boolean hasTask = false;
    protected HomeNewSunFragment homePage;
    protected IFreshChatRecentInfo iFreshChatRecentInfo;
    boolean isExit = false;
    Timer tExit = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            isExit = false;
            hasTask = true;
        }
    };
    Bundle bundle;
    View homeLayout, freshLayout, contactLayout, myLayout, appLayout;
    View[] views;
    FreshFragment freshPage;
    MyFragment myPage;
    ContactFragment contactPage;
    AppCenterFragment appPage;
    private String mTextViewArray[] = {"首页", "工作", "通讯录", "应用", "我"};
    private Fragment fragmentArray[];
    private LayoutInflater layoutInflater;
    private int currentTabIndex = 0;
    private Bitmap[] defalutBitmaps;
    private Bitmap[] selectBitmaps;
    private android.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Logger.e("create==");
        MobileApplication.getInstance().addActivity(this);
        bundle = savedInstanceState;
        fragmentManager = getFragmentManager();
        homePage = HomeNewSunFragment.getInstance();
        freshPage = FreshFragment.getInstance();
        contactPage = ContactFragment.getInstance();
        appPage = AppCenterFragment.getInstance();
        myPage = MyFragment.getInstance();

        FragmentTransaction transction = fragmentManager.beginTransaction();
        transction.add(R.id.realTabContent, homePage);
        transction.add(R.id.realTabContent, freshPage);
        transction.add(R.id.realTabContent, contactPage);
        transction.add(R.id.realTabContent, appPage);
        transction.add(R.id.realTabContent, myPage);
        transction.commit();
        fragmentArray = new Fragment[]{homePage, freshPage, contactPage, appPage, myPage};
        initView();
        iFreshChatRecentInfo = homePage;

        PushManager.startWork(this.getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(this, "api_key"));
//        loginRegistrationId();
    }

    /**
     * 登陆
     */
    private void loginRegistrationId() {
        new LoginJpushTask().execute();
    }

    private void initView() {
        layoutInflater = LayoutInflater.from(this);
        homeLayout = findViewById(R.id.homeLayout);
        homeLayout.setOnClickListener(this);
        freshLayout = findViewById(R.id.freshLayout);
        freshLayout.setOnClickListener(this);
        contactLayout = findViewById(R.id.contactLayout);
        contactLayout.setOnClickListener(this);
        myLayout = findViewById(R.id.myLayout);
        myLayout.setOnClickListener(this);
        appLayout = findViewById(R.id.appLayout);
        appLayout.setOnClickListener(this);
        views = new View[]{homeLayout, freshLayout, contactLayout, appLayout, myLayout};
        initTabBitmaps();
        for (int i = 0; i < views.length; i++) {
            View view = views[i];
            view.setBackgroundColor(getResources().getColor(R.color.white));
            TextView textView = (TextView) view.findViewById(R.id.textview);
            textView.setText(mTextViewArray[i]);
            textView.setTextColor(getResources().getColor(R.color.home_tab_text_defalut));
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setImageBitmap(defalutBitmaps[i]);
        }
        setCurrentTabIndex(currentTabIndex);
    }

    /**
     * 实例化迪兰的 tab需要的图片
     */
    private void initTabBitmaps() {
        defalutBitmaps = new Bitmap[]{BitmapFactory.decodeResource(getResources(), R.drawable.home),
                BitmapFactory.decodeResource(getResources(), R.drawable.newinofr),
                BitmapFactory.decodeResource(getResources(), R.drawable.contact),
                BitmapFactory.decodeResource(getResources(), R.drawable.app),
                BitmapFactory.decodeResource(getResources(), R.drawable.person_normal)};
        selectBitmaps = new Bitmap[]{BitmapFactory.decodeResource(getResources(), R.drawable.home_selected),
                BitmapFactory.decodeResource(getResources(), R.drawable.new_selected),
                BitmapFactory.decodeResource(getResources(), R.drawable.contact_selected),
                BitmapFactory.decodeResource(getResources(), R.drawable.app_selected),
                BitmapFactory.decodeResource(getResources(), R.drawable.person_selected)};
    }

    /**
     * 设置当前的tab
     */
    private void setCurrentTabIndex(int index) {
        for (int i = 0; i < views.length; i++) {
            setDefalut(i);
        }
        if (index == 0) {
            homePage.freshNum();
        }
        chanageTab(index, selectBitmaps[index], getResources().getColor(R.color.home_tab_text_select));
        setCurrentFragment(index);
    }

    private void chanageTab(int index, Bitmap bm, int color) {
        View view = views[index];
        Logger.e("----------" + view.getId());
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageBitmap(bm);
        TextView tv = (TextView) view.findViewById(R.id.textview);
        tv.setTextColor(color);
    }

    private void setDefalut(int index) {
        chanageTab(index, defalutBitmaps[index], getResources().getColor(R.color.home_tab_text_defalut));
    }

    public void onLayoutClick(View view) {

        switch (view.getId()) {
            case R.id.personLayout:
                IntentTool.startPersonInfor(this);
                break;
            case R.id.draftLayout:
                IntentTool.startDraftActivity(this);
                break;
            case R.id.editLayout:
//                IntentTool.startEditeActivity(this);
                break;
            case R.id.broastLayout:
                IntentTool.startNotifyActivity(this);
                break;
            case R.id.sitLayout:
                IntentTool.startSetActivity(this);
                break;
            case R.id.departLayout:
                IntentTool.startDepartmentsActivity(this);
                break;
            case R.id.feedBackLayout:
                IntentTool.startFeedBackActivity(this);
                break;
            case R.id.signatureLayout:
                Object ob = view.getTag();
                if (ob != null) {
                    IntentTool.startEditeSignature(this, ob.toString());
                }
                break;
            case R.id.aboutLayout:
                IntentTool.aboutPage(this);


                break;
            case R.id.fragment_app_gzt:
                String uname = PreferceManager.getInsance().getValueBYkey(this, "login_email");
                Logger.e(uname + "++++++++++++++++++++++");
                String url = getResources().getString(R.string.gztUrl) + "?uname=" + uname;
                Logger.e(url + "=====================");
                IntentTool.webViewPage(this, url, "工资条");
                break;
            case R.id.fragment_app_zmtbi:
                if (Common.installPackageCheck(this, "cn.wiz.zmt.note")) {
                    ComponentName componentName = new ComponentName("cn.wiz.zmt.note", "cn.wiz.note.MainActivity");
                    Intent intent = new Intent();
                    intent.setComponent(componentName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(this, WebViewActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void freshSelectedColleague(ContactBean contactBean, String position) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("TabHost_Index.java onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                ToastTool.show(R.string.exitSecond);
                if (!hasTask) {
                    tExit.schedule(task, 2000);
                }
            } else {
                MobileApplication.getInstance().clearActivities();
                StaticContext.getInstance().clear();
                PushManager.stopWork(this);
                finish();
                System.exit(0);
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == homeLayout) {
            setCurrentTabIndex(0);
            homePage.freshNum();
//            homePage.upScrollView();
            return;

        }
        if (v == freshLayout) {
            setCurrentTabIndex(1);
            return;

        }
        if (v == contactLayout) {
            setCurrentTabIndex(2);
            return;

        }
        if (v == appLayout) {
            setCurrentTabIndex(3);
            return;

        }
        if (v == myLayout) {
            setCurrentTabIndex(4);
            return;

        }


    }

    private void hideFragments() {
//        fragmentArray = new Fragment[]{homePage, freshPage, contactPage, appPage, myPage};
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragmentArray) {
            if (fragment != null) {
                transaction.hide(fragment);
//                transaction.remove(fragment);
            }
        }
        transaction.commit();
    }

    private void setCurrentFragment(int index) {
        hideFragments();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentArray[index] == null) {
            fragmentArray[index] = FreshFragment.getInstance();
        }
//        transaction.replace(R.id.realTabContent,fragmentArray[index]);
        transaction.show(fragmentArray[index]);
        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ToastTool.showDev("=");
        if (resultCode == Activity.RESULT_OK && requestCode == IConstant.CutRescode) {
            String path = data.getStringExtra("path");
            Logger.e("path1=" + path);
            CommonActionController.getInstance().upLoadUserPicture(new UpLoadViewCallBack(), path, this);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            String content = data.getStringExtra("content");
            myPage.infor(content);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        PushManager.stopWork(this);
//        Debug.stopMethodTracing();
    }

    public interface IFreshChatRecentInfo {
        void freshChatRecentInfo(List<RecentInfo> lists);
    }

    private class LoginJpushTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            String chId = JPushInterface.getRegistrationID(HomeNewActivity.this);
            Logger.e("jpush chid=" + chId);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }

    public class UpLoadViewCallBack implements ViewNetCallBack {

        @Override
        public void onConnectStart() {

            Common.noty("图片正在上传", NoticeCode.UserUpLoad);
        }

        @Override
        public void onConnectEnd() {
            Common.cancleNoticeByid(NoticeCode.UserUpLoad);
        }

        @Override
        public void onFail(Exception e) {

        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            AvarAllBean model = (AvarAllBean) result;
            String path = model.getData().getMiddle();
            myPage.freshIcon(path);

        }
    }


}
