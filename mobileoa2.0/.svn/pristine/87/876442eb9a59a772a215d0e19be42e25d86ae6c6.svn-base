package com.idxk.mobileoa.model.bean;

import com.idxk.mobileoa.utils.common.java.StringTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/7.
 */
public class WeiboDetailsModel extends BaseModel {
    int scopeDepartentNum;
    int scopeUserNum;
    List<AttachBean> attach;

    private String content;
    private ScopeInfo scope_info;
    private String feed_id;
    private String uid;
    private String feed_content;
    private String avatar_middle; //头像
    private String uname; //用戶名
    private String publish_time;
    private String scope;
    private String type;
    private int has_attach;

    private int digg_count = 0;

    private int is_digg;

    private int comment_count;

    private int channel_id;

    private String feed_approval_result;
    private String approval_form_id;

    public String getApproval_form_id() {
        return approval_form_id;
    }

    public void setApproval_form_id(String approval_form_id) {
        this.approval_form_id = approval_form_id;
    }

    private String feed_status;

    private ApprovalUserInfo approval_user_info;
    private String feed_type;

    private String content_summary;

    private String content_plan;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_summary() {
        return content_summary;
    }

    public void setContent_summary(String content_summary) {
        this.content_summary = content_summary;
    }

    public String getContent_plan() {
        return content_plan;
    }

    public void setContent_plan(String content_plan) {
        this.content_plan = content_plan;
    }

    public ScopeInfo getScope_info() {
        return scope_info;
    }

    public void setScope_info(ScopeInfo scope_info) {
        this.scope_info = scope_info;
    }

    public ApprovalUserInfo getApproval_user_info() {
        return approval_user_info;
    }

    public void setApproval_user_info(ApprovalUserInfo approval_user_info) {
        this.approval_user_info = approval_user_info;
    }

    public String getFeed_status() {
        return feed_status;
    }

    public void setFeed_status(String feed_status) {
        this.feed_status = feed_status;
    }

    public String getFeed_approval_result() {
        return feed_approval_result;
    }

    public void setFeed_approval_result(String feed_approval_result) {
        this.feed_approval_result = feed_approval_result;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(int digg_count) {
        this.digg_count = digg_count;
    }

    public int getIs_digg() {
        return is_digg;
    }

    public void setIs_digg(int is_digg) {
        this.is_digg = is_digg;
    }

    public String getFeed_type() {
        return feed_type;
    }

    public void setFeed_type(String feed_type) {
        this.feed_type = feed_type;
    }

    public int getScopeDepartentNum() {
        return scopeDepartentNum;
    }

    public void setScopeDepartentNum(int scopeDepartentNum) {
        this.scopeDepartentNum = scopeDepartentNum;
    }

    public int getScopeUserNum() {
        return scopeUserNum;
    }

    public void setScopeUserNum(int scopeUserNum) {
        this.scopeUserNum = scopeUserNum;
    }

    public boolean isFile() {
        return type.equals("postfile");
    }

    public boolean hasAttach() {
        return has_attach == 1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHas_attach() {
        return has_attach;
    }

    public void setHas_attach(int has_attach) {
        this.has_attach = has_attach;
    }

    public List<AttachBean> getAttach() {
        return attach;
    }

    public void setAttach(List<AttachBean> attach) {
        this.attach = attach;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<String> getDepartIds() {
        List<String> list = new ArrayList<String>();
        String[] strings = scope.split(",");
        if (strings.length == 0) {
            return list;
        }
        for (String string : strings) {
            if (string.contains("d_")) {
                String str = string.replace("d_", "");
                if (StringTools.isIntent(str)) {
                    list.add(str);
                }
            }
        }
        return list;
    }

    public List<String> getPersonsIds() {
        List<String> list = new ArrayList<String>();
        String[] strings = scope.split(",");
        if (strings.length == 0) {
            return list;
        }
        for (String string : strings) {

            if (StringTools.isIntent(string)) {
                list.add(string);
            }
        }

        return list;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAvatar_middle() {
        return avatar_middle;
    }

    public void setAvatar_middle(String avatar_middle) {
        this.avatar_middle = avatar_middle;
    }

    public String getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }

    public String getFeed_content() {
        return feed_content;
    }

    public void setFeed_content(String feed_content) {
        this.feed_content = feed_content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "WeiboDetailsModel{" +
                "feed_id='" + feed_id + '\'' +
                ", uid='" + uid + '\'' +
                ", feed_content='" + feed_content + '\'' +
                '}';
    }
}
