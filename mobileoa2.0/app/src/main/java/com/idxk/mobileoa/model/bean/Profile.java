package com.idxk.mobileoa.model.bean;

/**
 * Created by Wesley on 2015/3/13.
 */
public class Profile extends BaseModel {

    Zmt_department_id zmt_department_id;
    Zmt_work_number zmt_work_number;
    Mobile mobile;
    Tel tel;
    Email email;
    Intro intro;
    Department department;

    public Zmt_department_id getZmt_department_id() {
        return zmt_department_id;
    }

    public void setZmt_department_id(Zmt_department_id zmt_department_id) {
        this.zmt_department_id = zmt_department_id;
    }

    public Zmt_work_number getZmt_work_number() {
        return zmt_work_number;
    }

    public void setZmt_work_number(Zmt_work_number zmt_work_number) {
        this.zmt_work_number = zmt_work_number;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Tel getTel() {
        return tel;
    }

    public void setTel(Tel tel) {
        this.tel = tel;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Intro getIntro() {
        return intro;
    }

    public void setIntro(Intro intro) {
        this.intro = intro;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
