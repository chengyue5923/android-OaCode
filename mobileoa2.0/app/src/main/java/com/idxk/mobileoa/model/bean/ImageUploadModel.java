package com.idxk.mobileoa.model.bean;

import java.io.Serializable;

/**
 * Created by admin on 2017/1/9.
 */

public class ImageUploadModel implements Serializable {
    String img_id;
    String url;
    String thumb;

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
