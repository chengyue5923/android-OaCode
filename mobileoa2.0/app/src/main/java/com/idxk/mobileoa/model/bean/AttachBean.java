package com.idxk.mobileoa.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/3/28.
 */
public class AttachBean implements Serializable {
    String attach_id;
    String attach_name;
    String attach_url;
    String extension;
    String size;
    String attach_small;
    String attach_middle;
    boolean isImage = false;

    public boolean isImage() {
        return extension.equals("png") || extension.equals("jpg");
    }


    public String getAttach_id() {
        return attach_id;
    }

    public void setAttach_id(String attach_id) {
        this.attach_id = attach_id;
    }

    public String getAttach_name() {
        return attach_name;
    }

    public void setAttach_name(String attach_name) {
        this.attach_name = attach_name;
    }

    public String getAttach_url() {
        return attach_url;
    }

    public void setAttach_url(String attach_url) {
        this.attach_url = attach_url;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAttach_small() {
        return attach_small;
    }

    public void setAttach_small(String attach_small) {
        this.attach_small = attach_small;
    }

    public String getAttach_middle() {
        return attach_middle;
    }

    public void setAttach_middle(String attach_middle) {
        this.attach_middle = attach_middle;
    }

    @Override
    public String toString() {
        return "AttachBean{" +
                "attach_id='" + attach_id + '\'' +
                ", attach_name='" + attach_name + '\'' +
                ", attach_url='" + attach_url + '\'' +
                ", extension='" + extension + '\'' +
                ", size='" + size + '\'' +
                ", attach_small='" + attach_small + '\'' +
                ", attach_middle='" + attach_middle + '\'' +
                ", isImage=" + isImage +
                '}';
    }
}
