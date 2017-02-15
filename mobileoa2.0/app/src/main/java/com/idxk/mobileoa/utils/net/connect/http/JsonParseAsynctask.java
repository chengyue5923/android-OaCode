package com.idxk.mobileoa.utils.net.connect.http;

import android.content.Context;
import android.os.AsyncTask;

import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.config.enums.HttpManager;
import com.idxk.mobileoa.config.parse.UrlRes;
import com.idxk.mobileoa.model.bean.HttpConfigBean;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.GsonTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.JsonTool;
import com.idxk.mobileoa.utils.common.java.MD5Util;
import com.idxk.mobileoa.utils.common.java.MapUtils;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by lenovo on 2015/6/16.
 */
public class JsonParseAsynctask extends AsyncTask {

    HttpConfigBean config;
    private HttpConfig configs;
    private Map<String, Object> map;
    private ViewNetCallBack viewNetCallBack;
    private Class entityClass;
    private Serializable ser;
    private String jsonstring;

    public JsonParseAsynctask(HttpConfig configs, Map<String, Object> param, ViewNetCallBack listener,
                              Class cl) {
        this.configs = configs;
        this.map = param;
        this.viewNetCallBack = listener;
        this.entityClass = cl;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        config = HttpManager.getInstance().getConifgById(configs);


    }

    @Override
    protected Object doInBackground(Object[] params) {

        if (!MobileApplication.getInstance().isNetworkConnected()) {
            return null;
        }
        if (config.isNeedLogin()) {
            Context context = MobileApplication.getInstance();
            String oauth_token = PreferceManager.getInsance().getValueBYkey(context, "oauth_token");
            String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(context, "oauth_token_secret");
            map.put("oauth_token", oauth_token);
            map.put("oauth_token_secret", oauth_token_secret);

        }


        String url = UrlRes.getInstance().getUrl() + config.getActioin();
        url = MapUtils.map2UrlParams(url, map);

        Logger.e("result" + url);
        HttpResponse response = null;
        if (config.getMethod().getMethod() == 0) {
            HttpGet httpGet = new HttpGet(url);
            try {
                response = new DefaultHttpClient().execute(httpGet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            HttpPost httpPost = new HttpPost(url);
            try {
                Logger.e("This is post method");
                response = new DefaultHttpClient().execute(httpPost);
                Logger.e("This is post method" + response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {

            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                jsonstring = EntityUtils.toString(entity, HTTP.UTF_8).trim();
                Logger.e(jsonstring + "this is jsonParse" + jsonstring);
                if (config.isCach() && StringTools.isNullOrEmpty(JsonTool.getString(jsonstring, "code"))) {
                    //---缓存
                    String key = MD5Util.string2MD5(url);
                    PreferceManager.getInsance().saveValueBYkeyFromTable(jsonstring, key, MobileApplication.getInstance().getApplicationContext()
                            , "net");
                }
                if (JsonTool.isArray(jsonstring)) {
                    if (entityClass.equals(String.class)) {
                        ser = (Serializable) GsonTool.jsonToStringArrayEntity(jsonstring);
                        return ser;
                    }
                    ser = (Serializable) GsonTool.jsonToArrayEntity(jsonstring, entityClass);
                    return ser;
                }
                ser = (Serializable) GsonTool.jsonToEntity(jsonstring, entityClass);
                return ser;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (ser != null) {
            viewNetCallBack.onData(ser, true, jsonstring);
            viewNetCallBack.onConnectEnd();
            return;
        }

        viewNetCallBack.onFail(new Exception());

        if (config.isCach()) {
            CacheJsonReadAsynctask cacheJsonReadAsynctask = new CacheJsonReadAsynctask(configs, map, viewNetCallBack,
                    entityClass);
            cacheJsonReadAsynctask.execute();
        }

        viewNetCallBack.onConnectEnd();


    }
}
