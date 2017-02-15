package com.idxk.mobileoa.model.bean;

import com.idxk.mobileoa.utils.common.java.StringTools;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ContactBean extends BaseModel {

    public boolean isChecked = false;

    public boolean isDepartment = false;
    public int typeCall;
    String workphone;
    String phoneext;
    String departJson = "";
    private String uid;
    private String login;
    private String uname;
    private String location;
    private List<String> department;
    private String empID;
    private String mobile;
    private String tel;//废弃
    private String first_letter;
    private int _id;
    private String avatar_original;
    private List<String> user_department;
    private boolean showUserDepartMent = false;
    private String avatar_middle;
    private String dep;
    private String signature;


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    String company;


    public boolean isChecked() {
        return isChecked;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getAvatar_middle() {
        return avatar_middle;
    }

    public void setAvatar_middle(String avatar_middle) {
        this.avatar_middle = avatar_middle;
    }

    public boolean isShowUserDepartMent() {
        return showUserDepartMent;
    }

    public void setShowUserDepartMent(boolean showUserDepartMent) {
        this.showUserDepartMent = showUserDepartMent;
    }

    public List<String> getUser_department() {
        return user_department;
    }

    public void setUser_department(List<String> user_department) {
        this.user_department = user_department;
    }

    public int getTypeCall() {
        return typeCall;
    }

    public void setTypeCall(int typeCall) {
        this.typeCall = typeCall;
    }

    public boolean showPhone() {
        return !(StringTools.isNullOrEmpty(workphone) && StringTools.isNullOrEmpty(phoneext) && StringTools.isNullOrEmpty(mobile));
    }


    public boolean showMes() {
        return !StringTools.isNullOrEmpty(mobile);
    }

    public List<MoileNum> getmobiles() {
        return getmsByType(mobile, MobileTyle.MOBILE);
    }

    private List<MoileNum> getworkP() {
        return getmsByType(workphone, MobileTyle.WORK);
    }

    private List<MoileNum> getExt() {
        return getmsByType(workphone, MobileTyle.EXT);
    }

    public List<MoileNum> getShowNums() {
        List<MoileNum> shows = new ArrayList<MoileNum>();
        shows.addAll(getmobiles());

        shows.addAll(getworkP());
        shows.addAll(getExt());
        return shows;
    }

    public List<MoileNum> getCanCall() {
        List<MoileNum> calss = new ArrayList<MoileNum>();

        calss.addAll(getmobiles());
        calss.addAll(getworkP());
        return calss;
    }

    public List<MoileNum> getPNByType(int type) {

        switch (type) {
            case 0:
                return getShowNums();
            case 10:
                return getmobiles();
            case 20:
                return getworkP();
            case 30:
                return getExt();

        }
        return getmobiles();

    }

    private List<MoileNum> getmsByType(String contact, MobileTyle tyle) {
        List<MoileNum> mobiles = new ArrayList<MoileNum>();
        if (StringTools.isNullOrEmpty(contact)) {
            return mobiles;
        }
        String[] ms = contact.split(",");
        if (ms == null || ms.length == 0) {
            return mobiles;
        }

        for (String str : ms) {
            MoileNum num = new MoileNum();
            num.setNum(str);
            num.setTyle(tyle);
            mobiles.add(num);
        }

        return mobiles;
    }

    public String getPhoneext() {
        return phoneext;
    }

    public void setPhoneext(String phoneext) {
        this.phoneext = phoneext;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getAvatar_original() {
        return avatar_original;
    }

    public void setAvatar_original(String avatar_original) {
        this.avatar_original = avatar_original;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getDepartment() {
        return department;
    }

    public void setDepartment(List<String> department) {
        this.department = department;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepartmentsJson() {
        JSONArray departments = new JSONArray(department);
        departJson = departments.toString();
        return departJson;
    }

    public String getDepartmentsJsonTest() {

        return departJson;
    }

    public void setDepartJson(String json) {
        List<String> department = new ArrayList<String>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                department.add(jsonArray.get(i).toString());
            }

        } catch (Exception e) {

        }

        departJson = json;
        this.department = department;
    }

    public List<String> getDepartments() {
        List<String> department = new ArrayList<String>();
        try {
            JSONArray jsonArray = new JSONArray(departJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                department.add(jsonArray.get(i).toString());
            }

        } catch (Exception e) {

        }
        return department;
    }

    @Override
    public String toString() {
        return "ContactBean{" +
                "isChecked=" + isChecked +
                ", isDepartment=" + isDepartment +
                ", workphone='" + workphone + '\'' +
                ", phoneext='" + phoneext + '\'' +
                ", departJson='" + departJson + '\'' +
                ", uid='" + uid + '\'' +
                ", login='" + login + '\'' +
                ", uname='" + uname + '\'' +
                ", location='" + location + '\'' +
                ", department=" + department +
                ", empID='" + empID + '\'' +
                ", mobile='" + mobile + '\'' +
                ", tel='" + tel + '\'' +
                ", first_letter='" + first_letter + '\'' +
                ", _id=" + _id +
                ", avatar_original='" + avatar_original + '\'' +
                ", typeCall=" + typeCall +
                '}';
    }

    public enum MobileTyle {

        MOBILE(0, "手机号码"),
        WORK(1, "座机号码"),
        EXT(2, "分机号码");
        int type;
        String name;

        MobileTyle(int type, String name) {
            this.type = type;
            this.name = name;
        }

        MobileTyle(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class MoileNum implements Serializable {
        String num;
        MobileTyle tyle;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public MobileTyle getTyle() {
            return tyle;
        }

        public void setTyle(MobileTyle tyle) {
            this.tyle = tyle;
        }
    }
}
