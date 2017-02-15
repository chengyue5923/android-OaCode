package com.idxk.mobileoa.config.enums;

import android.content.res.AssetManager;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.model.bean.HttpConfigBean;
import com.idxk.mobileoa.utils.common.android.DomManager;
import com.idxk.mobileoa.utils.common.android.Logger;

import java.io.InputStream;
import java.util.HashMap;

/**
 *
 */
public class HttpManager {

    public  static HttpManager getInstance() {
        return SingleClearCach.instance;
    }

    static class SingleClearCach {
        static HttpManager instance = new HttpManager();
    }

    HashMap<Integer,HttpConfigBean> has;

    public void init(){
        AssetManager am = MobileApplication.getInstance().getAssets();

        try {
            InputStream is = am.open("url.xml");
            has= DomManager.paseFromUrl(is);
        }catch (Exception e){
            Logger.e(e.getLocalizedMessage(), e);

        }

    }


    public HttpConfigBean getConifgById(HttpConfig config){
        if (has == null||has.size()==0){
            init();
        }

        return has.get(config.getType());

    }

}
