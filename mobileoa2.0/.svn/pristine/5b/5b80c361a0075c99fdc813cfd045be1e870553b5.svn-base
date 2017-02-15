package com.idxk.mobileoa.logic.controller;


import com.idxk.mobileoa.config.enums.DPType;
import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.dao.ContactsDao;
import com.idxk.mobileoa.model.bean.BaseModel;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.DepartMentBean;
import com.idxk.mobileoa.model.bean.SendRangebean;
import com.idxk.mobileoa.model.bean.SendRangesBean;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.ConnectTool;
import com.idxk.mobileoa.utils.net.connect.http.JsonParseAsynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 联系人的 控制类
 */
public class ContactController {
    private static ContactController instance;

    private ContactController() {

    }

    public static ContactController getInstance() {
        if (instance == null) {
            instance = new ContactController();
        }

        return instance;

    }

    public void clearContact() {

        ContactsDao dao = new ContactsDao();
        dao.clearContact();
    }

    public void clearDepart() {
        ContactsDao dao = new ContactsDao();
        dao.createTable();

    }

    public void clearContactTable() {
        ContactsDao dao = new ContactsDao();
        dao.clearContact();
    }

    public void getDepartMentList(ViewNetCallBack listener) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getContactDepartments,
                    new HashMap<String, Object>(), listener, DepartMentBean.class);
        } catch (Exception e) {

        }
    }

    public List<DepartMentBean> getDpartment() {
        ContactsDao dao = new ContactsDao();
        return dao.quaryDepart();

    }

    public void getContacts(ViewNetCallBack listener) {


//        try {
//            ConnectTool.httpRequest(
//                    HttpConfig.getContacts,
//                    new HashMap<String, Object>(), listener, ContactBean.class);
//        } catch (Exception e) {
//
//        }

        JsonParseAsynctask jsonParseAsynctask = new JsonParseAsynctask(HttpConfig.getContacts,
                new HashMap<String, Object>(), listener, ContactBean.class);
        jsonParseAsynctask.execute();



    }


    public void getSunContacts(ViewNetCallBack listener) {
//        try {
//            ConnectTool.httpRequest(
//                    HttpConfig.sunCompany,
//                    new HashMap<String, Object>(), listener, BaseModel.class);
//        } catch (Exception e) {
//
//        }
        JsonParseAsynctask jsonParseAsynctask = new JsonParseAsynctask(HttpConfig.sunCompany,
                new HashMap<String, Object>(), listener, BaseModel.class);
        jsonParseAsynctask.execute();
    }

    public List<ContactBean> getConstacts(String name) {
        ContactsDao dao = new ContactsDao();
        List<ContactBean> res = dao.quary(name);
        Logger.e("res.s" +
                "" + res.size());
        return res;

    }

    public ContactBean getConstactByid(String uid) {
        ContactsDao dao = new ContactsDao();
        return dao.quaryByID(uid);
    }

    public ContactBean getContactByName(String name) {
        ContactsDao dao = new ContactsDao();
        return dao.quaryByName(name);
    }

    public int getContactNumByName(String uname) {
        ContactsDao dao = new ContactsDao();
        return dao.queryCountByUname(uname);
    }

    public int getContactNumByDepart(String uname) {
        ContactsDao dao = new ContactsDao();
        return dao.quaryCountByDName(uname);
    }


    public void insertConstacts(List<ContactBean> beans) {

        ContactsDao dao = new ContactsDao();
        dao.insert(beans);

    }


    public void insertDeparts(List<DepartMentBean> beans) {
        ContactsDao dao = new ContactsDao();
        dao.insertDepartment(beans);
    }

    public int quaryCount() {
        ContactsDao dao = new ContactsDao();
        return dao.quaryCount();
    }

    /**
     * 通过部门名称来获取 部门的联系人列表
     *
     * @param dName
     * @return
     */
    public List<ContactBean> getContactsByDName(String dName, String conact) {

        ContactsDao dao = new ContactsDao();
        List<ContactBean> beans = new ArrayList<ContactBean>();
        List<String> ids = dao.quaryUidByDName(dName);
        if (ListUtil.isNullOrEmpty(ids)) {
            return beans;
        }
        return dao.quaryByIds(ids, conact);
    }


    public SendRangesBean getMixList(List<HashMap<String, String>> hashMapList) {
        List<SendRangebean> all = new ArrayList<SendRangebean>();
        if (!ListUtil.isNullOrEmpty(hashMapList)) {
            for (HashMap<String, String> hashMap : hashMapList) {
                Iterator inter = hashMap.entrySet().iterator();
                while (inter.hasNext()) {
                    Map.Entry entry = (Map.Entry) inter.next();
                    SendRangebean bean = new SendRangebean();
                    bean.setUid(entry.getKey().toString());
                    bean.setName(entry.getValue().toString().replace("\"", ""));
                    all.add(bean);
                }
            }
        }

        SendRangesBean bean = new SendRangesBean();
        bean.setList(all);
        return bean;
    }


    public SendRangesBean getMixList(List<String> personIds, List<String> departmentIds) {
        List<String> pids = personIds;
        List<String> dids = departmentIds;
        ContactsDao dao = new ContactsDao();
        List<SendRangebean> all = new ArrayList<SendRangebean>();
        if (!ListUtil.isNullOrEmpty(pids)) {
            List<ContactBean> ps = dao.quaryByIds(pids, "");
            if (!ListUtil.isNullOrEmpty(ps)) {
                for (int i = 0; i < ps.size(); i++) {
                    SendRangebean bean = new SendRangebean();
                    if (i == 0) {
//                        bean.setShowTitle(true);
                    }
                    bean.setName(ps.get(i).getUname());
                    bean.setType(DPType.P);
                    all.add(bean);
                }
            }

        }
        if (!ListUtil.isNullOrEmpty(dids)) {
            List<SendRangebean> list = dao.quaryByIds(dids);
            if (!ListUtil.isNullOrEmpty(dids)) {
                for (int i = 0; i < list.size(); i++) {
                    SendRangebean bean = list.get(i);
                    if (i == 0) {
//                        bean.setShowTitle(true);
                    }
                    bean.setType(DPType.D);
                    all.add(bean);
                }
            }
        }

//        if (true) {
//            SendRangebean sendRangebean = new SendRangebean();
//            sendRangebean.setName("全公司");
//            all.add(sendRangebean);
//        }

        SendRangesBean bean = new SendRangesBean();
        bean.setList(all);
        return bean;

    }


//    static class SingleClearCach {
//        static ContactController instance = ContactController.getInstance();
//    }


}
