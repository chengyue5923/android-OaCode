package com.idxk.mobileoa.model.bean;

import java.util.List;

/**
 * Created by lenovo on 2015/3/6.
 */
public class FreshListModel extends BaseModel {
    private String uname; //用戶名
    private String avatar_middle; //圖片url
    private String publish_time;  //发布时间
    private int digg_count; //赞数量
    private String comment_count; //回复数量
    private String avatar_big;  //显示打图片
    private String feed_id; //微博id
    private String channel_id; //发布的类型
    private String uid;
    private String type;
    private String feed_status; //wait：待审批 ，done：完成
    private String scope; //发送范围
    private int is_digg; //是否赞
    private String content_plan; //日志的工作计划（第二个框）
    private String content_summary;//日志的工作总结（第一个框）
    private String scopeDepartentNum;
    private String scopeUserNum;
    private ApprovalUserInfo approval_user_info;

    private String content;

    private String feed_approval_result;

    private String commentator_uid;

    private String commentator_uname;

    private int has_attach;

    private List<AttachBean> attach;

    private String feed_order_result;

    private String order_end_time;

    private String feed_work_log_result;

    private ScopeInfo scope_info;

    private String approval_form_id;

    public String getApproval_form_id() {
        return approval_form_id;
    }

    public void setApproval_form_id(String approval_form_id) {
        this.approval_form_id = approval_form_id;
    }

    public ScopeInfo getScope_info() {
        return scope_info;
    }

    public void setScope_info(ScopeInfo scope_info) {
        this.scope_info = scope_info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeed_work_log_result() {
        return feed_work_log_result;
    }

    public void setFeed_work_log_result(String feed_work_log_result) {
        this.feed_work_log_result = feed_work_log_result;
    }

    public String getFeed_approval_result() {
        return feed_approval_result;
    }

    public void setFeed_approval_result(String feed_approval_result) {
        this.feed_approval_result = feed_approval_result;
    }

    public String getFeed_order_result() {
        return feed_order_result;
    }

    public void setFeed_order_result(String feed_order_result) {
        this.feed_order_result = feed_order_result;
    }

    public String getOrder_end_time() {
        return order_end_time;
    }

    public void setOrder_end_time(String order_end_time) {
        this.order_end_time = order_end_time;
    }

    public String getCommentator_uid() {
        return commentator_uid;
    }

    public void setCommentator_uid(String commentator_uid) {
        this.commentator_uid = commentator_uid;
    }

    public String getCommentator_uname() {
        return commentator_uname;
    }

    public void setCommentator_uname(String commentator_uname) {
        this.commentator_uname = commentator_uname;
    }

    public ApprovalUserInfo getApproval_user_info() {
        return approval_user_info;
    }

    public void setApproval_user_info(ApprovalUserInfo approval_user_info) {
        this.approval_user_info = approval_user_info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getHas_attach() {
        return has_attach;
    }

    public void setHas_attach(int has_attach) {
        this.has_attach = has_attach;
    }

    public String getFeed_status() {
        return feed_status;
    }

    public void setFeed_status(String feed_status) {
        this.feed_status = feed_status;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getIs_digg() {
        return is_digg;
    }

    public void setIs_digg(int is_digg) {
        this.is_digg = is_digg;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getScopeDepartentNum() {
        return scopeDepartentNum;
    }

    public void setScopeDepartentNum(String scopeDepartentNum) {
        this.scopeDepartentNum = scopeDepartentNum;
    }

    public String getScopeUserNum() {
        return scopeUserNum;
    }

    public void setScopeUserNum(String scopeUserNum) {
        this.scopeUserNum = scopeUserNum;
    }

    public String getContent_plan() {
        return content_plan;
    }

    public void setContent_plan(String content_plan) {
        this.content_plan = content_plan;
    }

    public String getContent_summary() {
        return content_summary;
    }

    public void setContent_summary(String content_summary) {
        this.content_summary = content_summary;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }

    public String getAvatar_big() {
        return avatar_big;
    }

    public void setAvatar_big(String avatar_big) {
        this.avatar_big = avatar_big;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public int getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(int digg_count) {
        this.digg_count = digg_count;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getAvatar_middle() {
        return avatar_middle;
    }

    public void setAvatar_middle(String avatar_middle) {
        this.avatar_middle = avatar_middle;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FreshListModel)) {
            return false;
        }
        FreshListModel m = (FreshListModel) o;
        return m.getFeed_id().equals(getFeed_id());
    }

    @Override
    public String toString() {
        return "FreshListModel{" +
                "uname='" + uname + '\'' +
                ", avatar_middle='" + avatar_middle + '\'' +
                ", publish_time='" + publish_time + '\'' +
                ", digg_count=" + digg_count +
                ", comment_count='" + comment_count + '\'' +
                ", avatar_big='" + avatar_big + '\'' +
                ", feed_id='" + feed_id + '\'' +
                ", channel_id='" + channel_id + '\'' +
                ", uid='" + uid + '\'' +
                ", type='" + type + '\'' +
                ", feed_status='" + feed_status + '\'' +
                ", scope='" + scope + '\'' +
                ", is_digg=" + is_digg +
                ", content_plan='" + content_plan + '\'' +
                ", content_summary='" + content_summary + '\'' +
                ", scopeDepartentNum='" + scopeDepartentNum + '\'' +
                ", scopeUserNum='" + scopeUserNum + '\'' +
                ", approval_user_info=" + approval_user_info +
                ", content='" + content + '\'' +
                ", feed_approval_result='" + feed_approval_result + '\'' +
                ", commentator_uid='" + commentator_uid + '\'' +
                ", commentator_uname='" + commentator_uname + '\'' +
                ", has_attach=" + has_attach +
                ", attach=" + attach +
                ", feed_order_result='" + feed_order_result + '\'' +
                ", order_end_time='" + order_end_time + '\'' +
                ", feed_work_log_result='" + feed_work_log_result + '\'' +
                ", scope_info=" + scope_info +
                ", approval_form_id='" + approval_form_id + '\'' +
                '}';
    }
}
