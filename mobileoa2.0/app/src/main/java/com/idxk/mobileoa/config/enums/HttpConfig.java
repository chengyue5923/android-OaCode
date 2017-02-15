package com.idxk.mobileoa.config.enums;


/**
 * Created by Wesley on 2015/3/4.
 */
public enum HttpConfig {
    login(1),
    //获取微博列表
    getFreshList(2),

    //获取微博详情
    getWeiboDetails(3),

    //某条微博评论列表接口
    getWeiboCommentList(4),

    getContactDepartments(5),
    //获取用户信息接口
    getPersonInfor(6),

    //发微博接口
    sendShare(7),

    //赞微博接口
    praiseWeibo(8),

    //取消赞微博
    cancelPraiseWeibo(9),

    //評論接口
    replyWeibo(10),
    //@我的接口
    remindMeWeiBo(11),
    //搜索微博内容
    searchWeiBoContent(12),
    //搜索同事入口
    searchColleague(13),

    //获取待处理日志
    getWaitForDealWithDiary(14),
    //獲取待處理指令列表
    getWaitForDealWithCommand(15),
    //获取审批列表
    getWaitForDealWithExamine(16),
    //获取待处理数量
    getWaitForDealWithNumber(17),
    getContacts(18),
    //我收到的評論
    getMessageReceived(19),
    //我发出的回复
    getMessageSend(20),
    //上传图片接口
    upLoadPicture(21),
    upLoadUserPicture(22),
    //消息提醒接口
    getMessageAlarmNumber(23),
    //某条微博的赞列表
    getPraiseListForWeiBo(24),
    //我发出的工作
    getMySendWorkList(25),
    //获取意见反馈类型
    getFeedBackType(26),
    //意见反馈接口
    sendFeedBackContent(27),

    //绑定百度推送
    bindBaiDuPush(28),

    //解除百度推送
    unBindBaiDuPush(29),
    appList(30),
    sunCompany(31),
    approvalTypes(32),
    outNotice(33),
    noticeRead(34),
    userSignature(35),
    yapull(36),
    publish(37),
    audioContent(38),
    ImgetDetailmessage(39),
    Impub(40),
    UploadImage(41),
    ;
    int type;
    HttpConfig(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
