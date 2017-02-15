package com.idxk.mobileoa.dao;

import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.DepartMentBean;
import com.idxk.mobileoa.model.bean.SendRangebean;
import com.idxk.mobileoa.utils.cache.db.SqlRequest;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.common.java.StringTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 所有数据操作层的父类
 */
public class ContactsDao extends BaseDao {

    public ContactsDao() {
        super();


    }

    public void createTable() {
        try {
            createContactsTable();
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }


    /**
     * 插入 person的 表的数据
     *
     * @param persons
     */
    public void insert(List<ContactBean> persons) {

        clearContact();
        SqlRequest[] sqlRequests = new SqlRequest[persons.size()];
        String sql = "INSERT INTO person (uid,uname,login,mobile,first_letter,url,department,workphone,phoneext) VALUES(?, ?, ?, ?,?,?,?,?,?)";
        String dMap = "INSERT INTO pdmapping ( uid,department) VALUES(?,?) ";
        List<SqlRequest> requests = new ArrayList<SqlRequest>();
        String regex = "[a-zA-Z]";
        for (int i = 0; i < persons.size(); i++) {
            ContactBean bean = persons.get(i);
            if (bean.getFirst_letter() == null || !bean.getFirst_letter().matches(regex)) {
                continue;
            }
            sqlRequests[i] = new SqlRequest(sql, new Object[]{bean.getUid(), bean.getUname(), bean.getLogin(),
                    bean.getMobile(), bean.getFirst_letter(), bean.getAvatar_original(), bean.getDepartmentsJson(), bean.getWorkphone(), bean.getPhoneext()});
            if (!ListUtil.isNullOrEmpty(bean.getDepartment())) {
                for (String d : bean.getDepartment()) {
                    if (!StringTools.isNullOrEmpty(d)) {
                        requests.add(new SqlRequest(dMap, new String[]{bean.getUid(), d}));
                    }
                }

            }

        }
        opration.execSqls(sqlRequests);
        SqlRequest[] array = new SqlRequest[requests.size()];
        requests.toArray(array);
        opration.execSqls(array);
    }

    public ContactBean quaryByID(String id) {
        String sql = "SELECT * FROM person   where uid=? ";
        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, new String[]{id}));
        if (ListUtil.isNullOrEmpty(maps)) {
            return new ContactBean();
        }
        Map<String, String> map = maps.get(0);
        ContactBean bean = new ContactBean();
        bean.setUid(map.get("uid"));
        bean.setAvatar_original(map.get("url"));
        bean.setUname(map.get("uname"));
        bean.setLogin(map.get("login"));
        bean.setMobile(map.get("mobile"));
        bean.setFirst_letter(map.get("first_letter"));
        bean.setDepartJson(map.get("department"));
        bean.setWorkphone(map.get("workphone"));
        bean.setPhoneext(map.get("phoneext"));
        return bean;
    }

    public ContactBean quaryByName(String name) {
        String sql = "SELECT * FROM person   where uname=? ";
        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, new String[]{name}));
        if (ListUtil.isNullOrEmpty(maps)) {
            return new ContactBean();
        }
        Map<String, String> map = maps.get(0);
        ContactBean bean = new ContactBean();
        bean.setUid(map.get("uid"));
        bean.setAvatar_original(map.get("url"));
        bean.setUname(map.get("uname"));
        bean.setLogin(map.get("login"));
        bean.setMobile(map.get("mobile"));
        bean.setFirst_letter(map.get("first_letter"));
        bean.setDepartJson(map.get("department"));
        bean.setWorkphone(map.get("workphone"));
        bean.setPhoneext(map.get("phoneext"));
        return bean;
    }

    public List<ContactBean> quary(String name) {

        String sql;
        String[] filter;
        if (StringTools.isNullOrEmpty(name)) {
            sql = "SELECT * FROM person where uid is not null and department is not '[]' order by first_letter";
            filter = new String[]{};
        } else {
            if (StringTools.isInteger(name)){
                sql = "SELECT * FROM person where uid is not null and department is not '[]' and mobile like '%" + name + "%' or phoneext like '%" + name + "%'   order by first_letter ";
                filter = new String[]{};
            }else{
                sql = "SELECT * FROM person where uid is not null and department is not '[]' and uname like '%" + name + "%' or login like '%" + name + "%'   order by first_letter";
                filter = new String[]{};
            }


        }


        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, filter));

        List<ContactBean> beans = new ArrayList<ContactBean>();
        if (!ListUtil.isNullOrEmpty(maps)) {
            for (Map<String, String> map : maps) {
                ContactBean bean = new ContactBean();
                bean.setUid(map.get("uid"));
                bean.setAvatar_original(map.get("url"));
                bean.setUname(map.get("uname"));
                bean.setLogin(map.get("login"));
                bean.setMobile(map.get("mobile"));
                bean.setFirst_letter(map.get("first_letter"));
                bean.setDepartJson(map.get("department"));
                Logger.e("===================" + map.get("department"));
                beans.add(bean);
            }
        }
        return beans;
    }


    /**
     * 查询数据库里面的数量
     *
     * @return
     */
    public int quaryCount() {


        String sql = "select count(*) from person where uid is not null";
        return opration.queryCount(new SqlRequest(sql, new String[]{}));

    }

    public int queryCountByUname(String uname) {
        String sql = "select count(*) from person where uname =?";
        return opration.queryCount(new SqlRequest(sql, new String[]{uname}));
    }

    /**
     * 获取部门的下面人的数量
     *
     * @param name
     * @return
     */
    public int quaryCountByDName(String name) {
        String sql = "select count(*) from pdmapping where department=?";

        return opration.queryCount(new SqlRequest(sql, new String[]{name}));
    }

    /**
     * 通过 部门名称来查询 uid的集合
     *
     * @return
     */

    public List<String> quaryUidByDName(String name) {
        String sql = "select uid from pdmapping where department=?";
        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, new String[]{name}));
        List<String> ids = new ArrayList<String>();
        if (ListUtil.isNullOrEmpty(maps)) {
            return ids;
        }

        for (Map<String, String> map : maps) {
            ids.add(map.get("uid"));
        }
        return ids;

    }


    public List<ContactBean> quaryByIds(List<String> ids, String contact) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (String id : ids) {
            builder.append(id);
            builder.append(",");
        }
        builder.delete(builder.length() - 1, builder.length()).toString();
        builder.append(") ");
        String sql;
        if (StringTools.isNullOrEmpty(contact)) {
            sql = "SELECT * FROM person where  uid in" + builder.toString() + "  order by first_letter";
        } else {
            sql = "SELECT * FROM person where uid in" + builder.toString() + " and uname like '%" + contact + "%'  or login like '" + contact + "%'   order by first_letter";

        }

        Logger.e("-sql-" + sql);
        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, new String[]{}));
        List<ContactBean> beans = new ArrayList<ContactBean>();
        if (!ListUtil.isNullOrEmpty(maps)) {
            for (Map<String, String> map : maps) {
                ContactBean bean = new ContactBean();
                bean.setUid(map.get("uid"));
                bean.setAvatar_original(map.get("url"));
                bean.setUname(map.get("uname"));
                bean.setLogin(map.get("login"));
                bean.setMobile(map.get("mobile"));
                bean.setFirst_letter(map.get("first_letter"));
                bean.setDepartJson(map.get("department"));
                beans.add(bean);
            }
        }
        return beans;


    }

    public List<SendRangebean> quaryByIds(List<String> ids) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (String id : ids) {
            builder.append(id);
            builder.append(",");
        }
        builder.delete(builder.length() - 1, builder.length()).toString();
        builder.append(") ");
        String sql = "SELECT * FROM departments where uid in" + builder.toString() + "  ";
        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, new String[]{}));
        List<SendRangebean> beans = new ArrayList<SendRangebean>();
        if (ListUtil.isNullOrEmpty(maps)) {
            return beans;
        }
        for (Map<String, String> map : maps) {
            SendRangebean bean = new SendRangebean();
            bean.setName(map.get("department"));
            beans.add(bean);
        }
        return beans;
    }

    public void insertDepartment(List<DepartMentBean> beans) {
        createTable();
        String sql = "INSERT INTO departments (uid,department) VALUES(?, ?)";
        SqlRequest[] sqls = new SqlRequest[beans.size()];
        for (int i = 0; i < beans.size(); i++) {
            Logger.e("-id--" + beans.get(i).getDepartment_id());
            Logger.e("-id--" + beans.get(i).getTitle());
            sqls[i] = new SqlRequest(sql, new Object[]{beans.get(i).getDepartment_id(), beans.get(i).getTitle()});
        }
        opration.execSqls(sqls);
    }

    public List<DepartMentBean> quaryDepart() {
        String sql = "SELECT * FROM departments";
        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, new String[]{}));
        List<DepartMentBean> array = new ArrayList<DepartMentBean>();
        if (ListUtil.isNullOrEmpty(maps)) {
            return array;
        }

        for (Map<String, String> map : maps) {
            DepartMentBean bean = new DepartMentBean();

            bean.setId(Integer.parseInt(map.get("uid")));
            bean.setDepartment_id(map.get("uid"));
            bean.setTitle(map.get("department"));
            bean.setpCount(quaryCountByDName(map.get("department")));
            array.add(bean);
        }
        return array;
    }


}
