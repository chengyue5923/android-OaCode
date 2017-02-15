package com.idxk.mobileoa.android.ui.activity.im;

import java.io.Serializable;

/**
 * Created by admin on 2017/2/13.
 */

public class AudioModel implements Serializable{


    String id;
    String content;
    String user_id;
    String session_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
