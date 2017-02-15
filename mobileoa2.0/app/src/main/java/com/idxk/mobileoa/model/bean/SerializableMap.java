package com.idxk.mobileoa.model.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lenovo on 2015/3/16.
 */
public class SerializableMap implements Serializable {
    private Map<String, ContactBean> map;

    public Map<String, ContactBean> getMap() {
        return map;
    }

    public void setMap(Map<String, ContactBean> map) {
        this.map = map;
    }
}
