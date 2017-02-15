package com.idxk.mobileoa.logic.controller;

import android.util.Log;

import com.idxk.mobileoa.config.constant.Constant;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.model.bean.BaseModel;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.model.bean.LoginModel;
import com.idxk.mobileoa.model.bean.PersonBean;
import com.idxk.mobileoa.model.bean.PraiseListModel;
import com.idxk.mobileoa.model.bean.PraiseResultModel;
import com.idxk.mobileoa.model.bean.SendResultModel;
import com.idxk.mobileoa.model.bean.WeiboCommentListModel;
import com.idxk.mobileoa.model.bean.WeiboDetailsModel;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.MapBuilder;
import com.idxk.mobileoa.utils.common.java.MapUtils;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.ConnectTool;
import com.idxk.mobileoa.utils.net.connect.http.JsonParseAsynctask;

import java.util.HashMap;
import java.util.Map;


/**
 * 关于用户行为的 controller
 */
public class UserController {

    public static UserController getInstance() {
        return SingleClearCach.instance;
    }

    /**
     * 登录
     *
     * @param uid      用户名
     * @param password 密码
     * @param listener
     */
    public void login(String uid, String password, ViewNetCallBack listener) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.login,
                    new MapBuilder<String, Object>().add("uid", uid).add("passwd", password)
                            .getUnmodifiableMap(), listener, LoginModel.class);

        } catch (Exception e) {

            //--todo exception 进行分离
        }

    }

    public void getFreshList(int page, String feedType, ViewNetCallBack listener) {
        JsonParseAsynctask jsonParseAsynctask = new JsonParseAsynctask(HttpConfig.getFreshList,
                new MapBuilder<String, Object>().
                        add("feed_type", feedType)
                        .add("page", page).add(Constant.PAGE_SIZE_KEY, Constant.PAGE_LIMITE)
                .getUnmodifiableMap(), listener, FreshListModel.class);
        jsonParseAsynctask.execute();
//        try {
//            ConnectTool.httpRequest(
//                    HttpConfig.getFreshList,
//                    new MapBuilder<String, Object>().add("feed_type", feedType).add("page", page)
//                            .getUnmodifiableMap(), listener, FreshListModel.class);
//
//        } catch (Exception e) {
//            Logger.e(e.getLocalizedMessage(), e);
//        }
    }

    public void getWeiboDetails(String id, ViewNetCallBack listener) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getWeiboDetails,
                    new MapBuilder<String, Object>().add("id", id)
                            .getUnmodifiableMap(), listener, WeiboDetailsModel.class);

        } catch (Exception e) {
            Log.e("getWeiBoDetails", e.getMessage());
        }
    }

    public void getWeiboCommentList(String id, ViewNetCallBack listener) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getWeiboCommentList,
                    new MapBuilder<String, Object>().add("id", id)
                            .getUnmodifiableMap(), listener, WeiboCommentListModel.class);

        } catch (Exception e) {
            Log.e("getWeiBoDetails", e.getLocalizedMessage());
        }
    }

    /**
     * @param map
     * @param channelId
     * @param listener  发微博接口
     */
    public void sendShare(Map<String, String> map, int channelId, ViewNetCallBack listener) {

        if (map.containsKey("approval_form_id")){
            try {
                ConnectTool.httpRequest(
                        HttpConfig.sendShare,
                        new MapBuilder<String, Object>()
                                .add("content", MapUtils.getVlaue("content", map))
                                .add("approval_form_id", MapUtils.getVlaue("approval_form_id", map))
                                .add("channel_id", channelId)
                                .add("type", MapUtils.getVlaue("type", map))
                                .add("attach_id", MapUtils.getVlaue("attach_id", map))
                                .add("content_plan", MapUtils.getVlaue("content_plan", map))
                                .add("content_summary", MapUtils.getVlaue("content_summary", map))
                                .add("scope", MapUtils.getVlaue("scope", map))
                                .add("picturePath", MapUtils.getVlaue("picturePath", map))
                                .add("log_type", MapUtils.getVlaue("log_type", map))
                                .add("from", IConstant.ANDROID)
                                .add("commentator_uid", MapUtils.getVlaue("commentator_uid", map))
                                .add("order_end_datetime", MapUtils.getVlaue("order_end_datetime", map))
                                .getUnmodifiableMap(), listener, SendResultModel.class);
            } catch (Exception e) {
                Logger.e(e.getMessage());
            }
            return;
        }

        try {
            ConnectTool.httpRequest(
                    HttpConfig.sendShare,
                    new MapBuilder<String, Object>()
                            .add("content", MapUtils.getVlaue("content", map))
                            .add("channel_id", channelId)
                            .add("type", MapUtils.getVlaue("type", map))
                            .add("attach_id", MapUtils.getVlaue("attach_id", map))
                            .add("content_plan", MapUtils.getVlaue("content_plan", map))
                            .add("content_summary", MapUtils.getVlaue("content_summary", map))
                            .add("scope", MapUtils.getVlaue("scope", map))
                            .add("picturePath", MapUtils.getVlaue("picturePath", map))
                            .add("log_type", MapUtils.getVlaue("log_type", map))
                            .add("from", IConstant.ANDROID)
                            .add("commentator_uid", MapUtils.getVlaue("commentator_uid", map))
                            .add("order_end_datetime", MapUtils.getVlaue("order_end_datetime", map))
                            .getUnmodifiableMap(), listener, SendResultModel.class);
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    public void praiseWeiBo(String feedId, ViewNetCallBack listener) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.praiseWeibo,
                    new MapBuilder<String, Object>().add("feed_id", feedId)
                            .getUnmodifiableMap(), listener, PraiseResultModel.class);

        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    public void cancelPraiseWeiBo(String feedId, ViewNetCallBack listener) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.cancelPraiseWeibo,
                    new MapBuilder<String, Object>().add("feed_id", feedId)
                            .getUnmodifiableMap(), listener, PraiseResultModel.class);

        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    public void getPersonInfor(ViewNetCallBack listener, int userId) {
//        try {
//            ConnectTool.httpRequest(
//                    HttpConfig.getPersonInfor,
//                    new MapBuilder<String, Object>().add("user_id", userId).getUnmodifiableMap(), listener, PersonBean.class);
//        } catch (Exception e) {
//            Logger.e(e.getLocalizedMessage(), e);
//
//        }

        JsonParseAsynctask jsonParseAsynctask = new JsonParseAsynctask(HttpConfig.getPersonInfor,
                new MapBuilder<String, Object>().add("user_id", userId).getUnmodifiableMap(), listener, PersonBean.class);
        jsonParseAsynctask.execute();


    }


    public void getPersonInfor(ViewNetCallBack listener, String userId,String uName) {

        JsonParseAsynctask jsonParseAsynctask = new JsonParseAsynctask(HttpConfig.getPersonInfor,
                new MapBuilder<String, Object>().add("user_id", userId).add("user_name", uName).getUnmodifiableMap(), listener, PersonBean.class);
        jsonParseAsynctask.execute();
    }
    /**
     * 修改密码
     *
     * @param listener
     */
    public void changePassword(ViewNetCallBack listener) {

    }

    /**
     * 評論接口
     *
     * @param listener
     * @param hashMap
     */
    public void replyWeiBo(ViewNetCallBack listener, HashMap<String, String> hashMap) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.replyWeibo,
                    new MapBuilder<String, Object>().add("row_id", MapUtils.getVlaue("row_id", hashMap))
                            .add("content", MapUtils.getVlaue("content", hashMap))
                            .add("app_uid", MapUtils.getVlaue("app_uid", hashMap))
                            .add("from", IConstant.ANDROID)
                            .add("approve", MapUtils.getVlaue("approve", hashMap))
                            .add("result", MapUtils.getVlaue("result", hashMap))
                            .add("to_comment_id", MapUtils.getVlaue("to_comment_id", hashMap))
                            .add("to_uid", MapUtils.getVlaue("to_uid", hashMap))
                            .add("attach_id", MapUtils.getVlaue("attach_id", hashMap))
                            .getUnmodifiableMap(), listener, PraiseResultModel.class);

        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }


    /**
     * 提到我的
     *
     * @param listener
     */
    public void remindMeWeiBo(ViewNetCallBack listener) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.remindMeWeiBo,
                    new HashMap<String, Object>(), listener, FreshListModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);

        }
    }

    /**
     * 搜索微博内容接口
     *
     * @param content
     * @param viewNetCallBack
     * @param page
     */
    public void searchWeiBoContent(String content, ViewNetCallBack viewNetCallBack, int page) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.searchWeiBoContent,
                    new MapBuilder<String, Object>().add("key", content)
                            .add("page", page)
                            .getUnmodifiableMap(), viewNetCallBack, FreshListModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);

        }
    }

    public void searchColleague(String content, ViewNetCallBack viewNetCallBack, int page) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.searchColleague,
                    new MapBuilder<String, Object>().add("key", content)
                            .add("page", page)
                            .getUnmodifiableMap(), viewNetCallBack, ContactBean.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);

        }
    }

    /**
     * 获取待处理日志列表
     *
     * @param viewNetCallBack
     * @param sinceId
     */
    public void getWaitForDealWithDiaryList(ViewNetCallBack viewNetCallBack, int sinceId) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getWaitForDealWithDiary,
                    new HashMap<String, Object>(), viewNetCallBack, DealWithDiaryListItemBean.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);

        }
    }

    public void getWaitForDealWithCommandList(ViewNetCallBack viewNetCallBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getWaitForDealWithCommand,
                    new HashMap<String, Object>(), viewNetCallBack, DealWithDiaryListItemBean.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);

        }
    }

    public void getExamineList(ViewNetCallBack viewNetCallBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getWaitForDealWithExamine,
                    new HashMap<String, Object>(), viewNetCallBack, DealWithDiaryListItemBean.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);

        }
    }

    //某条微博的赞列表
    public void getPraiseListForWeiBo(ViewNetCallBack viewNetCallBack, String feedId) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getPraiseListForWeiBo,
                    new MapBuilder<String, Object>().add("feed_id", feedId)
                            .getUnmodifiableMap(), viewNetCallBack, PraiseListModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);

        }
    }

    //我发出的工作列表
    public void getMySendWorkList(ViewNetCallBack viewNetCallBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getMySendWorkList,
                    new MapBuilder<String, Object>().add("since_id", "0")
                            .add("feed_type", "work")
                            .getUnmodifiableMap(), viewNetCallBack, FreshListModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);

        }
    }

    //我发出的工作列表
    public void getMySendWorkList(ViewNetCallBack viewNetCallBack, int page) {
//        try {
//            ConnectTool.httpRequest(
//                    HttpConfig.getMySendWorkList,
//                    new MapBuilder<String, Object>().add("since_id", "0")
//                            .add("feed_type", "work").add("limit", 1).add("page", page)
//                            .getUnmodifiableMap(), viewNetCallBack, FreshListModel.class);
//        } catch (Exception e) {
//            Logger.e(e.getLocalizedMessage(), e);
//
//        }
        JsonParseAsynctask jsonParseAsynctask = new JsonParseAsynctask(HttpConfig.getMySendWorkList,
                new MapBuilder<String, Object>().add("since_id", "0")
                        .add("feed_type", "work").add("limit", 1).add("page", page)
                        .getUnmodifiableMap(), viewNetCallBack, FreshListModel.class);
        jsonParseAsynctask.execute();


    }

    public void editeUserInfor(String infor,ViewNetCallBack viewNetCallBack){
        try {
            ConnectTool.httpRequest(
                    HttpConfig.userSignature,
                    new MapBuilder<String, Object>().add("intro", infor)
                            .getUnmodifiableMap(), viewNetCallBack, BaseModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);

        }
    }

    static class SingleClearCach {
        static UserController instance = new UserController();
    }





}
