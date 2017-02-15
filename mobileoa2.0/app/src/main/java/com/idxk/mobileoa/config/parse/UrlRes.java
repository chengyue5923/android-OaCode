package com.idxk.mobileoa.config.parse;

import android.content.Context;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;

/**
 * 从资源中获取的rul 的res
 */
public class UrlRes {


    Context context;

    public static UrlRes getInstance() {

        return SingleClearCach.instance;

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUrl() {
        String en = MobileApplication.getInstance().url;

        if(en.equals("url1")){
            return context.getResources().getString(R.string.url1);
        }

        if(en.equals("url")){
            return context.getResources().getString(R.string.url);
        }
        if(en.equals("url2")){
            return context.getResources().getString(R.string.url2);
        }

        return context.getResources().getString(R.string.url2);
    }

    public String getUrlSever(){

        String en = MobileApplication.getInstance().url;

        if (en.equals("url1")) {
            return context.getResources().getString(R.string.urlSeverBeta);
        }

        if (en.equals("url")) {
            return context.getResources().getString(R.string.urlSever);
        }
        if (en.equals("url2")) {
            return context.getResources().getString(R.string.urlSeverNormal);
        }

        return context.getResources().getString(R.string.urlSeverNormal);

    }



    public String getPictureUrl() {
            return "http://gui.ideathink.com.cn/";
    }

    static class SingleClearCach {
        static UrlRes instance = new UrlRes();
    }
}
