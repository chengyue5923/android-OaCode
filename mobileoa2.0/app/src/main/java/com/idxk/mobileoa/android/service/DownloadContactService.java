package com.idxk.mobileoa.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.DepartMentBean;
import com.idxk.mobileoa.utils.cache.db.factory.DBFactory;
import com.idxk.mobileoa.utils.common.android.LogUtils;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/3/19.
 */
public class DownloadContactService extends Service implements ViewNetCallBack {


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("Service start");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e("onStartCommand");
//        DBFactory.getInstance().setContext(getApplicationContext());
        ContactController.getInstance().getContacts(this);
        ContactController.getInstance().getDepartMentList(new DepartMentCallback());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        DBFactory.getInstance().close();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
        LogUtils.e("+++++++++++++service finish+++++++++++++");

        new DbThread(result, 0).execute();

//        stopSelf();
    }

    private class DbThread extends AsyncTask {
        Serializable result;
        int type;


        private DbThread(Serializable result, int type) {
            this.result = result;
            this.type = type;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            ContactController.getInstance().clearContactTable();
            if (type == 0) {
                List<ContactBean> persons = (List<ContactBean>) result;
                if (ListUtil.isNullOrEmpty(persons)) {
                    return null;
                }
                ContactController.getInstance().insertConstacts(persons);
            } else {
                List<DepartMentBean> beans = (List<DepartMentBean>) result;
                if (ListUtil.isNullOrEmpty(beans)) {
                    return null;
                }
                ContactController.getInstance().insertDeparts(beans);
            }
            return null;
        }
    }


    private class DepartMentCallback implements ViewNetCallBack {
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
            new DbThread(result, 1).execute();

        }
    }
}
