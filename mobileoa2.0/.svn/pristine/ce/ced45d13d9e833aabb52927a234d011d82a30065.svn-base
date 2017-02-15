package com.idxk.mobileoa.logic.manager;

import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.logic.controller.CachController;
import com.idxk.mobileoa.model.bean.RecentModel;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.common.java.StringTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 最近联系人的controller
 *
 */
public class RecenteManager {

    public static RecenteManager getInstance() {
        return SingleClearCach.instance;
    }

    /**
     *
     * @param type  传入的类型
     * @return
     */
     public List<RecentModel> quaryRes(int type){
         String mId =
                 PreferceManager.getInsance().getValueBYkey(MobileApplication.getInstance().
                         getApplicationContext(), "uid");
         List<RecentModel> list = new ArrayList<>();
         String[] me = CachController.getInstance().getEntityById(mId);
         if (me!=null&&me.length>0){
             RecentModel model =  new RecentModel();
             model.setName(me[1]);
             model.setUid(me[0]);
             if (!list.contains(model)) {
                 list.add(model);
             }

         }
         if (type>0){
             RecentModel all = new RecentModel();
             all.setUid("all");
             all.setName("全公司");

             all.setChecked(type == 1 ? true : false);
             if (!list.contains(all)) {
                 list.add(all);
             }
         }

         String dId = PreferceManager.getInsance().
                 getValueBYkey(MobileApplication.getInstance().getApplicationContext(),"dId");

         String dName = PreferceManager.getInsance().
                 getValueBYkey(MobileApplication.getInstance().getApplicationContext(), "dName");


         if (StringTools.isNullOrEmpty(dId)||StringTools.isNullOrEmpty(dName)){
             Logger.e("meiyou 部门");
         }else{
             RecentModel dPart = new RecentModel();
             dPart.setUid(dId);
             dPart.setName(dName);

             dPart.setChecked(type == 2 ? true : false);
             if (!list.contains(dPart)) {
                 list.add(dPart);
             }

         }
         List<String[]> array = CachController.getInstance().getAllNotId(mId);
         if (ListUtil.isNullOrEmpty(array)){
             return list;
         }

         for (String[] a:array){
             if (a!=null&&a.length>0){
                 RecentModel model =  new RecentModel();
                 model.setName(a[1]);
                 model.setUid(a[0]);
                 if (!list.contains(model)) {
                     list.add(model);
                 }
             }
         }
         return list;
     }

    public List<RecentModel> quaryRes(){
        String mId =
                PreferceManager.getInsance().getValueBYkey(MobileApplication.getInstance().
                        getApplicationContext(), "uid");
        List<RecentModel> list = new ArrayList<>();
        String[] me = CachController.getInstance().getEntityById(mId);
        if (me!=null&&me.length>0){
            RecentModel model =  new RecentModel();
            model.setName(me[1]);
            model.setUid(me[0]);
            if (!list.contains(model)) {
                list.add(model);
            }
        }

        List<String[]> array = CachController.getInstance().getAllNotId(mId);
        if (ListUtil.isNullOrEmpty(array)){
            return list;
        }
        for (String[] a:array){
            if (a!=null&&a.length>0){
                RecentModel model =  new RecentModel();
                model.setName(a[1]);
                model.setUid(a[0]);
                if (!list.contains(model)) {
                    list.add(model);
                }
            }
        }
        return list;
    }

    static class SingleClearCach {
        static RecenteManager instance = new RecenteManager();
    }


}
