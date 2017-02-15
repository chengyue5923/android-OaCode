package com.idxk.mobileoa.android.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.config.enums.HttpManager;
import com.idxk.mobileoa.config.parse.UrlRes;
import com.idxk.mobileoa.logic.controller.IMController;
import com.idxk.mobileoa.utils.cache.db.achieve.UserPreference;
import com.idxk.mobileoa.utils.cache.db.factory.DBFactory;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.CrashHandler;
import com.idxk.mobileoa.utils.common.android.FileTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.StaticContext;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.FileTools;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.common.java.StaticUtils;
import com.idxk.mobileoa.utils.im.Constant;
import com.idxk.mobileoa.utils.net.connect.http.file.ImageLoaderManager;
import com.idxk.mobileoa.utils.net.factory.HttpRequestFactory;
import com.mogujie.tt.TTIMManager;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

/**
 * App入口
 */
public class MobileApplication extends Application {
    static MobileApplication instance;
    public String toastShow = "false";
    public String writeLog = "false";
    public String debug = "false";
    public String url = "url2";
    public static String cachePath="";
    public boolean talkHasLogin = false;
    List<Activity> activitys;

    public static MobileApplication getInstance() {
        return instance;
    }

    public static String getStringFromMata(String key) {

        try {
            ApplicationInfo appInfo = MobileApplication.getInstance().getPackageManager()
                    .getApplicationInfo(MobileApplication.getInstance().getPackageName(),
                            PackageManager.GET_META_DATA);

            return appInfo.metaData.get(key).toString();
        } catch (Exception e) {
//            Toast.makeText(MobileApplication.getInstance(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }
        return "";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
        FileTool.getInstance().createSystemDirs();
        instance = this;
        init();

        StaticContext.getInstance().init();
        activitys = new LinkedList<>();
        //使用TeamTalk ImageLoader进行初始化
        TTIMManager.getInstance().init(this);
        ImageLoaderManager.getInstance().init(R.drawable.default_icon, R.drawable.default_icon);

        try {
            errorHandlerSwitch(Boolean.parseBoolean(writeLog));

        } catch (Exception e) {

        }
        DBFactory.getInstance().setContext(getApplicationContext());
        StaticUtils.getFragmentArray();
    }


    /**
     * 关于一些程序启动需要初始的内容
     */
    private void init() {
        // url 资源配置的 实例化
        UrlRes.getInstance().setContext(getApplicationContext());
        //http android 特性工厂实例化
        HttpRequestFactory.getInstance().init(getApplicationContext());
        toastShow = getStringFromMata("testToastShow");
        writeLog = getStringFromMata("writeLog");
        debug = getStringFromMata("debug");
        url = getStringFromMata("environment");
        HttpManager.getInstance().init();
        //创建文件夹到sd/zmtCach
        FileTools.creatDir(Environment.getExternalStorageDirectory().getPath() + File.separator + "zmtCach");
//        initJpush();
        cachePath = Environment.getExternalStorageDirectory().getPath() + File.separator + this.getCacheDir();
        FileTools.creatDir(cachePath);
        String audioDir = Constant.AUDIO_PATH;
        String audioTempDir = Constant.AUDIO_TEMP_PATH;
        FileTools.creatDir(audioDir);
        FileTools.creatDir(audioTempDir);
        DBFactory factory = DBFactory.getInstance();
        factory.setContext(getApplicationContext());
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
//                PushAgent.getInstance(ShenGuiApplication.this).onAppStart();
//                PushAgent.getInstance(ShenGuiApplication.this).enable();
//                Logger.e("UmengRegistrar.getRegistrationId(context)" + UmengRegistrar.getRegistrationId(ShenGuiApplication.this));
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                Logger.e("onActivityResumed ===" + activity.getClass().getSimpleName());
                if(UserPreference.getTOKEN()!=null&&UserPreference.getTOKEN().length()>1){
                    IMController.getInstance().oneshotSync(activity);
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Logger.e("onActivityDestroyed ===" + activity.getClass().getSimpleName());
            }
        });
        PreferceManager.getInsance().saveValueBYkey("version","0");
//        ListenNetChangeReceiver receiver = new ListenNetChangeReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(receiver,filter);

    }

    /**
     * 初始化 jupush
     */
    private void initJpush() {

//        JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);            // 初始化 JPush
    }

    public void addActivity(Activity activity) {

        StaticContext.getInstance().setCurrentContext(activity);
        if (!activitys.contains(activity)) {
            activitys.add(activity);
        }


    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    public void clearActivities() {
        if (!ListUtil.isNullOrEmpty(activitys)) {
            for (Activity activity : activitys) {
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ToastTool.showDev("app销毁了");
    }

    private void errorHandlerSwitch(boolean canWriteLog) {
        if (!canWriteLog) {
            return;
        }
        CrashHandler crashHandler = CrashHandler.getInstance();
// 注册crashHandler
        crashHandler.init(getApplicationContext());
    }
}
