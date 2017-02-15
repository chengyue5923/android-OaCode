package com.idxk.mobileoa.model.bean;

import java.io.Serializable;

/**
 * Created by Wesley on 2015/3/13.
 */
public class Mobile implements Serializable {
    String name;
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
