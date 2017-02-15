package com.idxk.mobileoa.utils.net.connect.http;

import android.content.Context;
import android.os.AsyncTask;

import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.config.enums.HttpManager;
import com.idxk.mobileoa.config.parse.UrlRes;
import com.idxk.mobileoa.exception.ResolveException;
import com.idxk.mobileoa.model.bean.HttpConfigBean;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.GsonTool;
import com.idxk.mobileoa.utils.common.java.JsonTool;
import com.idxk.mobileoa.utils.common.java.MD5Util;
import com.idxk.mobileoa.utils.common.java.MapUtils;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lenovo on 2015/6/16.
 */
public class CacheJsonReadAsynctask extends AsyncTask {


    HttpConfigBean config;
    private HttpConfig configs;
    private Map<String, Object> map;
    private ViewNetCallBack viewNetCallBack;
    private Class entityClass;
    private Serializable ser;
    private String jsonString;

    public CacheJsonReadAsynctask(HttpConfig configs, Map<String, Object> param, ViewNetCallBack listener,
                                  Class cl) {
        this.configs = configs;
        this.map = param;
        this.viewNetCallBack = listener;
        this.entityClass = cl;
    }


    @Override
    protected Object doInBackground(Object[] params) {
        config = HttpManager.getInstance().getConifgById(configs);
        if (config.isNeedLogin()) {
            Context context = MobileApplication.getInstance();
            String oauth_token = PreferceManager.getInsance().getValueBYkey(context, "oauth_token");
            String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(context, "oauth_token_secret");
            map.put("oauth_token", oauth_token);
            map.put("oauth_token_secret", oauth_token_secret);

        }


        String url = UrlRes.getInstance().getUrl() + config.getActioin();
        url = MapUtils.map2UrlParams(url, map);


        if (config.isCach()) {
            String key = MD5Util.string2MD5(url);
            jsonString = PreferceManager.getInsance().getValueBYkeyFromTable(MobileApplication.getInstance().getApplicationContext(),
                    key, "net").trim();

            if (JsonTool.isArray(jsonString)) {
                if (entityClass.equals(String.class)) {
                    ser = (Serializable) GsonTool.jsonToStringArrayEntity(jsonString);
                    return ser;
                }
                ser = (Serializable) GsonTool.jsonToArrayEntity(jsonString, entityClass);
                return ser;
            }
            try {
                ser = (Serializable) GsonTool.jsonToEntity(jsonString, entityClass);
            } catch (ResolveException e) {
                e.printStackTrace();
            }
            return ser;

        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (ser != null) {
            viewNetCallBack.onData(ser, true, jsonString);
            viewNetCallBack.onConnectEnd();
            return;
        }

        viewNetCallBack.onFail(new Exception());
        viewNetCallBack.onConnectEnd();


    }
}
