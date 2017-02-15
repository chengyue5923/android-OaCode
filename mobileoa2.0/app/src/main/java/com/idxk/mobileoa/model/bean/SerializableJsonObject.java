package com.idxk.mobileoa.model.bean;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by lenovo on 2015/3/16.
 */
public class SerializableJsonObject implements Serializable {
    private JsonObject jsonObject;

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
