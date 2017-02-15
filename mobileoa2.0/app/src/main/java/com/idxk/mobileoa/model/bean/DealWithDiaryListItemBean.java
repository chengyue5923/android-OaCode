package com.idxk.mobileoa.model.bean;

import java.util.List;

/**
 * Created by lenovo on 2015/3/9.
 */
public class DealWithDiaryListItemBean extends BaseModel {

    public String avatar_middle; //图片地址
    public String uname;
    public String publish_time; //发布时间
    public String diaryState;
    public String diaryComment;
    public String content_summary;
    public String content_plan;
    public String content;
    public int is_digg;
    public String feed_id = "";
    public String uid;
    public int digg_count = 0;
    public String feed_work_log_result = "";
    public String feed_order_result;
    public String feed_approval_result;
    public int comment_count = 0;
    public String channel_id;
    public String commentator_uname;

    private int has_attach;
    private List<AttachBean> attach;
    private String type;

    public int getHas_attach() {
        return has_attach;

    }

    public boolean isFile() {
        return type.equals("postfile");
    }

    public List<AttachBean> getAttach() {
        return attach;
    }

    public void setAttach(List<AttachBean> attach) {
        this.attach = attach;
    }
}
