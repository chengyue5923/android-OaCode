package com.idxk.mobileoa.utils.net.callbackipl;

import android.app.Dialog;
import android.content.Intent;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.config.constant.Constant;
import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.config.enums.HttpManager;
import com.idxk.mobileoa.config.parse.UrlRes;
import com.idxk.mobileoa.exception.NeedLoginException;
import com.idxk.mobileoa.model.bean.HttpConfigBean;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.GsonTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.StaticContext;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.android.UIExceptionTool;
import com.idxk.mobileoa.utils.common.java.JsonTool;
import com.idxk.mobileoa.utils.common.java.MD5Util;
import com.idxk.mobileoa.utils.common.java.MapUtils;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.NetCallback;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 * 对底层接口的 对接类
 */
public class BaseNetImpl implements NetCallback {


    ViewNetCallBack listener;
    Class entityClass;
    HttpConfigBean config;
    Map<String, Object> param;


    public BaseNetImpl(ViewNetCallBack listener, Class entityClass, HttpConfig config) {
        this.listener = listener;
        this.entityClass = entityClass;
        this.config = HttpManager.getInstance().getConifgById(config);
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    @Override
    public void dealReslut(String jsonstring) {
        try {
            Logger.e("reslut=" + jsonstring);
            if (config.isShowToast()) {
                String mess = JsonTool.getString(jsonstring, "message");
                if (!StringTools.isNullOrEmpty(mess)) {
                    ToastTool.show(mess);
                }
            }

            if (JsonTool.getString(jsonstring, "code").equals(Constant.NeedLoginNetCode)) {
                if (config.getId() != HttpConfig.bindBaiDuPush.getType() && config.getId() != HttpConfig.unBindBaiDuPush.getType()) {
                    Intent needLoginIntent = new Intent(MobileApplication.getInstance().getApplicationContext().getResources().getString(R.string.needLoginAction));
                    MobileApplication.getInstance().sendBroadcast(needLoginIntent);
                    listener.onFail(new NeedLoginException());
                    return;
                }

            }

            if (config.isShowNotify() && StringTools.isNullOrEmpty(JsonTool.getString(jsonstring, "code"))) {
                Common.noty(config.getNofity().getName() + "成功", config.getNofity().getId());
                Common.cancleNoticeByIdAfterTime(config.getNofity().getId());
            }

            if (config.isCach() && StringTools.isNullOrEmpty(JsonTool.getString(jsonstring, "code"))) {

                //---缓存
                String url = UrlRes.getInstance().getUrl() + config.getActioin();
                url = MapUtils.map2UrlParams(url, param);
                String key = MD5Util.string2MD5(url);
                PreferceManager.getInsance().saveValueBYkeyFromTable(jsonstring, key, MobileApplication.getInstance().getApplicationContext()
                        , "net");
            }
            if (JsonTool.isArray(jsonstring)) {
                if (entityClass.equals(String.class)) {

                    Serializable ser = (Serializable) GsonTool.jsonToStringArrayEntity(jsonstring);
                    listener.onData(ser, true, jsonstring);
                    return;

                }
                Serializable ser = (Serializable) GsonTool.jsonToArrayEntity(jsonstring, entityClass);
                listener.onData(ser, true, jsonstring);
                return;
            }
            if(config.getId()==HttpConfig.audioContent.getType()||config.getId()==HttpConfig.Impub.getType()||config.getId()==HttpConfig.UploadImage.getType()||config.getId()==HttpConfig.ImgetDetailmessage.getType()){
                JSONObject jsonObject = new JSONObject(jsonstring);
                try {
                    listener.onData((Serializable) GsonTool.jsonToEntity(jsonObject.getString("data"), entityClass), true, jsonstring );
                } catch (Exception e) {
                    listener.onData((Serializable) GsonTool.jsonToArrayEntity(jsonObject.getString("data"), entityClass),  true, jsonstring);
                }
                return;
            }
            Serializable ser = (Serializable) GsonTool.jsonToEntity(jsonstring, entityClass);

            listener.onData(ser, true, jsonstring);

        } catch (Exception e) {
            listener.onFail(e);
        }
    }

    @Override
    public void dealFailer(Exception e) {

        if (config.isShowNotify()) {
//            Common.noty(config.getNofity().getName()+"失败", config.getNofity().getId());
//            Common.cancleNoticeByIdAfterTime(config.getNofity().getId());
        }

        if (config.isShowToast()) {
            String toast = UIExceptionTool.getToastByException(e);
            if (!StringTools.isNullOrEmpty(toast)) {
                ToastTool.show(toast);
            }
            return;
        }
        listener.onFail(e);
    }

    @Override
    public void onstart() {

        listener.onConnectStart();
        if (config.isShowLoadDialog()) {
            Dialog dialog = StaticContext.getInstance().createDialog(config.getActioin());
            if (dialog != null) {
                dialog.show();
            }

        }


        if (config.isCach()) {
            //---缓存
            String url = UrlRes.getInstance().getUrl() + config.getActioin();
            url = MapUtils.map2UrlParams(url, param);
            String key = MD5Util.string2MD5(url);
            String jsonstring = PreferceManager.getInsance().getValueBYkeyFromTable(MobileApplication.getInstance().getApplicationContext(),
                    key, "net");
            if (JsonTool.isArray(jsonstring)) {
                if (entityClass.equals(String.class)) {
                    Serializable ser = (Serializable) GsonTool.jsonToStringArrayEntity(jsonstring);
                    listener.onData(ser, false, jsonstring);
                    return;

                }
                Serializable ser = (Serializable) GsonTool.jsonToArrayEntity(jsonstring, entityClass);
                listener.onData(ser, false, jsonstring);
                return;
            }
            try {
                Serializable ser = (Serializable) GsonTool.jsonToEntity(jsonstring, entityClass);
                listener.onData(ser, false, jsonstring);
            } catch (Exception e) {

            }

        }
    }

    @Override
    public void onEnd() {
        listener.onConnectEnd();
        if (config.isShowLoadDialog()) {
            Dialog dialog = StaticContext.getInstance().getDisMisDialogByKey(config.getActioin());
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }

        }
    }
}
