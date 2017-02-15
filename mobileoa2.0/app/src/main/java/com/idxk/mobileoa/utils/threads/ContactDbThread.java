package com.idxk.mobileoa.utils.threads;

import android.os.AsyncTask;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.DepartMentBean;
import com.idxk.mobileoa.utils.common.java.ListUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/4/9.
 */
public abstract class ContactDbThread extends AsyncTask {


    Serializable result;
    int type;



    public ContactDbThread(Serializable result, int type) {
        this.result = result;
        this.type = type;
    }

    @Override
    protected Object doInBackground(Object[] params) {
//        ContactController.getInstance().clearContactTable();
        if (type==0){
            List<ContactBean> persons = (List<ContactBean>) result;
            if (ListUtil.isNullOrEmpty(persons)) {
                return null;
            }
            ContactController.getInstance().insertConstacts(persons);
        }else {
            List<DepartMentBean>  beans = (List<DepartMentBean>) result;
            if (ListUtil.isNullOrEmpty(beans)) {
                return null;
            }
            ContactController.getInstance().insertDeparts(beans);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        onView();


    }

    public abstract void onView();

}
