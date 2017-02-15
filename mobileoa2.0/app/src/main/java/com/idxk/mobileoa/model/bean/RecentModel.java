package com.idxk.mobileoa.model.bean;

/**
 * Created by lenovo on 2015/4/3.
 */
public class RecentModel extends BaseModel {
    private String uid;
    private String name;
    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RecentModel)) {
            return false;
        }
        RecentModel model = (RecentModel) o;
        return model.getUid().equals(uid);
    }
}
