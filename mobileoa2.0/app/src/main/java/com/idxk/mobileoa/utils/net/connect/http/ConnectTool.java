package com.idxk.mobileoa.utils.net.connect.http;

import android.content.Context;

import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.config.enums.HttpManager;
import com.idxk.mobileoa.config.parse.UrlRes;
import com.idxk.mobileoa.model.bean.HttpConfigBean;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.HttpNetCallBack;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.callbackipl.BaseNetImpl;
import com.idxk.mobileoa.utils.net.factory.HttpRequestFactory;

import org.apache.http.protocol.HTTP;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 连接底层的 http工具类
 */
public class ConnectTool {

    /**
     * http request
     *
     * @param configs  配置的枚举
     * @param param    hm
     * @param listener 监听的
     * @param cl       lass
     * @throws Exception
     */
    public static void httpRequest(HttpConfig configs, Map<String, Object> param, ViewNetCallBack listener,
                                   Class cl) throws Exception {
        Logger.e(Thread.currentThread().getName() + "ThreadGET");
        HttpConfigBean config = HttpManager.getInstance().getConifgById(configs);
        String url="";
        if(configs== HttpConfig.upLoadPicture||configs==HttpConfig.ImgetDetailmessage||configs==HttpConfig.Impub||configs==HttpConfig.UploadImage||configs==HttpConfig.audioContent){
             url = UrlRes.getInstance().getPictureUrl() + config.getActioin();
            Logger.e(">>> request url:" + url);
        }else{
             url = UrlRes.getInstance().getUrl() + config.getActioin();
            Logger.e(">>> request url:" + url);
        }
        HttpRequestFactory until = HttpRequestFactory.getInstance();
        until.setNetType(HttpRequestFactory.HTTPCLIENT);
        HttpNetCallBack callBack = until.getHttpRequst();
        BaseNetImpl implLinener = new BaseNetImpl(listener, cl, configs);
        HashMap<String, String> header = HeaderTools.getHeaderByConfig(configs);
        if (config.isNeedLogin()) {
            Context context = MobileApplication.getInstance();
            String oauth_token = PreferceManager.getInsance().getValueBYkey(context, "oauth_token");
            String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(context, "oauth_token_secret");
            param.put("oauth_token", oauth_token);
            param.put("oauth_token_secret", oauth_token_secret);
        }

        implLinener.setParam(param);

        if (param.size() == 0) {
            Logger.e("request=param is null");
        } else {
            Iterator iter = param.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = entry.getKey().toString();
                Object val = entry.getValue();
                Logger.e("request=param  key=" + key + "   value=" + val);
            }
        }


        switch (config.getMethod()) {
            case GET:
                if (config.isHeader()) {
                    callBack.getWithHeader(url, param, header, config.getTimeout(), config.isCach(), implLinener);
                    return;
                }
                callBack.get(url, param, config.getTimeout(), config.isCach(), implLinener);
                return;
            case POST:
                if (config.isHeader()) {
                    callBack.postWithHeader(url, param, header, config.getTimeout(), config.isCach(), implLinener);
                    return;
                }
                callBack.post(url, param, config.getTimeout(), config.isCach(), implLinener);
                return;
            default:
                break;
        }
    }


}
