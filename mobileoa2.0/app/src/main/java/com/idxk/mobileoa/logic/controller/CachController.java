package com.idxk.mobileoa.logic.controller;


import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.config.enums.DPType;
import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.dao.CachDao;
import com.idxk.mobileoa.dao.ContactsDao;
import com.idxk.mobileoa.model.bean.*;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.ConnectTool;

import java.util.*;

/**
 * 联系人的 控制类
 */
public class CachController {
    public static CachController getInstance() {
        if (instance==null){
            instance =  new CachController();
        }

        return instance;

    }

    static CachController instance;


    static class SingleClearCach {
        static CachController instance = new CachController();
    }




    public void saveReCach(String uid,String name,boolean isUser){
        CachDao  dao = new CachDao();
        String mID = PreferceManager.getInsance().getValueBYkey(MobileApplication.getInstance().getApplicationContext(), "uid");
        dao.insert(uid,name,mID,isUser);
    }
    public void saveReCach(Map<String, ContactBean> map){
        CachDao  dao = new CachDao();
        String mID = PreferceManager.getInsance().getValueBYkey(MobileApplication.getInstance().getApplicationContext(), "uid");
        Collection<ContactBean> collection= map.values();
        ContactBean[] array = new ContactBean[collection.size()];
        collection.toArray(array);
        for (ContactBean bean:array){
            dao.insert(bean.getUid(),bean.getUname(),mID,!bean.isDepartment);
        }

    }

    public List<String[]> getAll(){
        CachDao  dao = new CachDao();
        String mID = PreferceManager.getInsance().getValueBYkey(MobileApplication.getInstance().getApplicationContext(), "uid");
        return dao.quaryAllByMid(mID);
    }
    public List<String[]> getAllNotId(String uid){
        CachDao  dao = new CachDao();
        String mID = PreferceManager.getInsance().getValueBYkey(MobileApplication.getInstance().getApplicationContext(), "uid");
        return dao.quaryAllBynotId(uid,mID);
    }

    public String[] getEntityById(String uid ){
        CachDao  dao = new CachDao();
        String mID = PreferceManager.getInsance().getValueBYkey(MobileApplication.getInstance().getApplicationContext(), "uid");
        return dao.quaryOneById(uid,mID);
    }



}
