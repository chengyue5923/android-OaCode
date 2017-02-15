package com.idxk.mobileoa.model.bean;

import com.idxk.mobileoa.utils.common.java.StringTools;

/**
 * 关于部门的 bean
 */
public class DepartMentBean extends BaseModel {


    public boolean isChecked = false;
    private int id;
    private String name;
    private int num;
    private String department_id;
    private String title;
    private int pCount;

    public int getpCount() {
        return pCount;
    }

    public void setpCount(int pCount) {
        this.pCount = pCount;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        if (StringTools.isIntent(department_id)) {
            return Integer.parseInt(department_id);
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
