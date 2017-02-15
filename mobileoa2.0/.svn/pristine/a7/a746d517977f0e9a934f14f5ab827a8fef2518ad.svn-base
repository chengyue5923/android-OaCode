package com.idxk.mobileoa.utils.common.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import com.idxk.mobileoa.android.ui.views.dialog.factory.DialogFacory;
import com.idxk.mobileoa.utils.common.java.MD5Util;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/4/2.
 */
public class StaticContext {

    static StaticContext instance;

    public StaticContext() {

    }

    public static StaticContext getInstance() {
        if (instance==null){
            instance = new StaticContext();
        }
        return instance;
    }

    public void clear(){
        dList.clear();
    }
    public void init(){
        dList =  new HashMap<String, Dialog>();
    }

    private  Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private HashMap<String,Dialog> dList;

    public  void setCurrentContext(Context con){
            context = con;
    }

    public Dialog createDialog(String key){
        try {
            dList.clear();
            Dialog dialog= DialogFacory.getInstance().createRunDialog(context);
            dList.put(MD5Util.string2MD5(key),dialog);
            return dialog;
        }catch (Exception e){
            return null;
        }

    }

    public Dialog getDisMisDialogByKey(String key){

        return dList.get(MD5Util.string2MD5(key));
//        return null;
    }











}
