package com.idxk.mobileoa.utils.common.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.idxk.mobileoa.android.ui.activity.AboutActivity;
import com.idxk.mobileoa.android.ui.activity.AgreeActivity;
import com.idxk.mobileoa.android.ui.activity.ApplicationAlarmActivity;
import com.idxk.mobileoa.android.ui.activity.ChangePasswordActivity;
import com.idxk.mobileoa.android.ui.activity.ContactDetailActivity;
import com.idxk.mobileoa.android.ui.activity.DealWithCommandDetailsActivity;
import com.idxk.mobileoa.android.ui.activity.DealWithDiaryDetailsActivity;
import com.idxk.mobileoa.android.ui.activity.DepartmentContectsActivity;
import com.idxk.mobileoa.android.ui.activity.DepartmentSelectionActivity;
import com.idxk.mobileoa.android.ui.activity.DepartmentSunSelectionActivity;
import com.idxk.mobileoa.android.ui.activity.DraftActivity;
import com.idxk.mobileoa.android.ui.activity.EditeInoforActivity;
import com.idxk.mobileoa.android.ui.activity.EditeUserSignature;
import com.idxk.mobileoa.android.ui.activity.FeedBackActivity;
import com.idxk.mobileoa.android.ui.activity.FileShowListActivity;
import com.idxk.mobileoa.android.ui.activity.HomeNewSunActivity;
import com.idxk.mobileoa.android.ui.activity.MessageReceivedActivity;
import com.idxk.mobileoa.android.ui.activity.MessageSendActivity;
import com.idxk.mobileoa.android.ui.activity.NativeCommonActivity;
import com.idxk.mobileoa.android.ui.activity.NativeFilesActivity;
import com.idxk.mobileoa.android.ui.activity.PersonInforActivity;
import com.idxk.mobileoa.android.ui.activity.PraiseReceivedActivity;
import com.idxk.mobileoa.android.ui.activity.PraiseReplyActivity;
import com.idxk.mobileoa.android.ui.activity.ReminderMeActivity;
import com.idxk.mobileoa.android.ui.activity.ReplyActivity;
import com.idxk.mobileoa.android.ui.activity.SearchActivity;
import com.idxk.mobileoa.android.ui.activity.SelectImageActivity;
import com.idxk.mobileoa.android.ui.activity.SelectMobileActivity;
import com.idxk.mobileoa.android.ui.activity.SelectSendScopeActivity;
import com.idxk.mobileoa.android.ui.activity.SendCommandActivity;
import com.idxk.mobileoa.android.ui.activity.SendDiaryActivity;
import com.idxk.mobileoa.android.ui.activity.SendExamineActivity;
import com.idxk.mobileoa.android.ui.activity.SendRangeActivity;
import com.idxk.mobileoa.android.ui.activity.SendShareActivity;
import com.idxk.mobileoa.android.ui.activity.SetActivity;
import com.idxk.mobileoa.android.ui.activity.ShareScopeActivity;
import com.idxk.mobileoa.android.ui.activity.ShowBigImageActivity;
import com.idxk.mobileoa.android.ui.activity.ShowImageActivity;
import com.idxk.mobileoa.android.ui.activity.ShowImageToolActivity;
import com.idxk.mobileoa.android.ui.activity.SystemNotifyActivity;
import com.idxk.mobileoa.android.ui.activity.WaitForDealWithActivity;
import com.idxk.mobileoa.android.ui.activity.WebViewActivity;
import com.idxk.mobileoa.android.ui.activity.WeiboDetailsActivity;
import com.idxk.mobileoa.android.ui.activity.WorkAlarmActivity;
import com.idxk.mobileoa.android.ui.activity.WorkSendActivity;
import com.idxk.mobileoa.android.ui.activity.approval.ApprovalEditeActivity;
import com.idxk.mobileoa.android.ui.activity.approval.ApprovalInputActivity;
import com.idxk.mobileoa.android.ui.activity.approval.ApprovalShowActivity;
import com.idxk.mobileoa.android.ui.activity.approval.ApprovalTypesActivity;
import com.idxk.mobileoa.android.ui.activity.im.ChatActivity;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.model.bean.ApprovalTypesBean;
import com.idxk.mobileoa.model.bean.AttachBean;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.model.bean.FileListsBean;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.model.bean.MessageReceivedListItemBean;
import com.idxk.mobileoa.model.bean.RemindMeListModel;
import com.idxk.mobileoa.model.bean.SendRangesBean;
import com.idxk.mobileoa.model.bean.WeiboCommentListModel;

import java.io.Serializable;
import java.util.List;

public class IntentTool {


    /**
     * 跳转主页面
     *
     * @param con
     */
    public static void homePage(Context con) {

        Intent intent = new Intent(con, HomeNewSunActivity.class);
        con.startActivity(intent);
    }

    /**
     * 跳转主页面
     *
     * @param con
     */
    public static void aboutPage(Context con) {

        Intent intent = new Intent(con, AboutActivity.class);
        con.startActivity(intent);
    }

    /**
     * 跳转聊天界面
     *
     * @param con
     */
    public static void chatPage(Context con) {

        Intent intent = new Intent(con,
                com.mogujie.tt.ui.activity.LoginActivity.class);
        con.startActivity(intent);
    }

    /**
     * 跳转主页面
     *
     * @param con
     */
    public static void webViewPage(Context con, String url, String titleName) {

        Intent intent = new Intent(con, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", titleName);
        Logger.e("--tilte--" + titleName);
        Logger.e("--tilte-url-"+url);
        con.startActivity(intent);
    }

    /**
     * 跳转主页面
     *
     * @param con
     */
    public static void webViewPage(Context con, String url, String titleName,boolean showBottom) {

        Intent intent = new Intent(con, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("showBottom", showBottom);
        intent.putExtra("title", titleName);
        con.startActivity(intent);
    }


    /**
     * 跳转工作提醒页面
     *
     * @param context
     */
    public static void workAlarmPage(Context context) {
        Intent intent = new Intent(context, WorkAlarmActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转应用提醒页面
     *
     * @param context
     */
    public static void applicationAlarmPage(Context context) {
        Intent intent = new Intent(context, ApplicationAlarmActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳转到分享页面
     *
     * @param context
     */
    public static void sendSharePage(Context context) {
        Intent intent = new Intent(context, SendShareActivity.class);
        context.startActivity(intent);
    }

    public static void startSendCommandActivity(Context context) {
        Intent intent = new Intent(context, SendCommandActivity.class);
        context.startActivity(intent);
    }

    /**
     * 調到發日誌頁面
     *
     * @param context
     */
    public static void sendDiaryPage(Context context) {
        Intent intent = new Intent(context, SendDiaryActivity.class);
        context.startActivity(intent);
    }

    public static void startSendExamineActivity(Context context) {
        Intent intent = new Intent(context, SendExamineActivity.class);
        context.startActivity(intent);
    }

    public static void startSendExamineActivity(Context context,int htmlId,String url) {
        Intent intent = new Intent(context, SendExamineActivity.class);
        intent.putExtra("htmlId",htmlId);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    /**
     * 開始個人主頁的 頁面
     *
     * @param context
     */
    public static void startPersonInfor(Context context) {
        Intent intent = new Intent(context, PersonInforActivity.class);
        context.startActivity(intent);
    }

    /**
     * 收到回复页面
     *
     * @param context
     */
    public static void messageReceivedPage(Context context) {
        Intent intent = new Intent(context, MessageReceivedActivity.class);
        context.startActivity(intent);
    }


    /**
     * 发出消息页面
     *
     * @param context
     */
    public static void messageSendPage(Context context) {
        Intent intent = new Intent(context, MessageSendActivity.class);
        context.startActivity(intent);
    }


    /**
     * 回复页面
     *
     * @param context
     * @param type    0==回复页面
     *                1==同意页面
     *                2==不同意页面
     *                3==复议页面
     *                4==汇报结果页面
     *                5===回复回复微博页面
     *                6==点评日志
     */
    public static void replyPage(Context context, int type, Object object) {
        Intent intent = new Intent(context, ReplyActivity.class);
        intent.putExtra("type", type);
        switch (type) {
            case 0:
                if (object instanceof FreshListModel) {
                    intent.putExtra("object", (FreshListModel) object);
                } else if (object instanceof RemindMeListModel) {
                    intent.putExtra("object", (RemindMeListModel) object);
                } else if (object instanceof DealWithDiaryListItemBean) {
                    intent.putExtra("object", (DealWithDiaryListItemBean) object);
                } else if (object instanceof MessageReceivedListItemBean) {
                    intent.putExtra("object", (MessageReceivedListItemBean) object);
                } else if (object instanceof WeiboCommentListModel) {
                    intent.putExtra("object", (WeiboCommentListModel) object);
                }
                break;
            case 5:
                intent.putExtra("object", (WeiboCommentListModel) object);
                break;
            case 1:
            case 2:
            case 3:
                intent.putExtra("object", (DealWithDiaryListItemBean) object);
                break;
            case 4:
                intent.putExtra("object", (DealWithDiaryListItemBean) object);
                break;
            case 6:
                intent.putExtra("object", (DealWithDiaryListItemBean) object);
                break;
        }

        if (object instanceof WeiboCommentListModel) {
            ((Activity) context).startActivityForResult(intent, IConstant.COMMENTLISTREFRESH);
        } else {
            context.startActivity(intent);
        }

    }


    /**
     * 回复页面
     *
     * @param context
     * @param type    0==回复页面
     *                1==同意页面
     *                2==不同意页面
     *                3==复议页面
     *                4==汇报结果页面
     *                5===回复回复微博页面
     *                6==点评日志
     */
    public static void replyPageForResult(Activity context, int type, Object object) {
        Intent intent = new Intent(context, ReplyActivity.class);
        intent.putExtra("type", type);
        switch (type) {
            case 0:
                if (object instanceof FreshListModel) {
                    intent.putExtra("object", (FreshListModel) object);
                } else if (object instanceof RemindMeListModel) {
                    intent.putExtra("object", (RemindMeListModel) object);
                } else if (object instanceof DealWithDiaryListItemBean) {
                    intent.putExtra("object", (DealWithDiaryListItemBean) object);
                } else if (object instanceof MessageReceivedListItemBean) {
                    intent.putExtra("object", (MessageReceivedListItemBean) object);
                }
                break;
            case 5:
                intent.putExtra("object", (WeiboCommentListModel) object);
                break;
            case 1:
            case 2:
            case 3:
                intent.putExtra("object", (DealWithDiaryListItemBean) object);
                break;
            case 4:
                intent.putExtra("object", (DealWithDiaryListItemBean) object);
                break;
            case 6:
                intent.putExtra("object", (DealWithDiaryListItemBean) object);
                break;
        }
        context.startActivityForResult(intent, 1000);
    }


    /**
     * 显示头像页面
     *
     * @param context
     * @param url
     */
    public static void imageViewShowPage(Context context, String url) {
        Intent intent = new Intent(context, ShowBigImageActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }


    public static void scanPicture(Context context, List<AttachBean> attachBeans) {
        Intent intent = new Intent(context, ShowImageActivity.class);
        intent.putExtra("bean", (Serializable) attachBeans);
        context.startActivity(intent);
    }

    public static void scanPicture(Context context, List<AttachBean> attachBeans,int current) {
        Intent intent = new Intent(context, ShowImageActivity.class);
        intent.putExtra("bean", (Serializable) attachBeans);
        intent.putExtra("current", current);
        context.startActivity(intent);
    }

    /**
     * 跳到微博詳情界面
     *
     * @param context
     * @param feedId
     */
    public static void weiboDetailsPage(Context context, FreshListModel feedId, String channel) {
        Intent intent = new Intent(context, WeiboDetailsActivity.class);
        intent.putExtra("file", feedId);
        intent.putExtra("channel", channel);
        context.startActivity(intent);
    }


    /**
     * 跳到微博詳情界面
     *
     * @param context
     * @param feedId
     */
    public static void weiboDetailsPage(Context context, String feedId, String channel) {
        Intent intent = new Intent(context, WeiboDetailsActivity.class);
        intent.putExtra("feedId", feedId);
        intent.putExtra("channel", channel);
        context.startActivity(intent);
    }

    /**
     * 跳到我收到的贊頁面
     *
     * @param context
     */
    public static void praiseReceivedPage(Context context) {
        Intent intent = new Intent(context, PraiseReceivedActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳到我收到的贊頁面
     *
     * @param context
     */
    public static void startWebUrl(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }


    /**
     * 跳到提到我的页面
     *
     * @param context
     */
    public static void reminderMePage(Context context) {
        Intent intent = new Intent(context, ReminderMeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到待处理页面
     *
     * @param context
     */
    public static void waitForDealWithPage(Context context) {
        Intent intent = new Intent(context, WaitForDealWithActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到我发出的页面
     *
     * @param context
     */
    public static void sendWorkPage(Context context) {
        Intent intent = new Intent(context, WorkSendActivity.class);
        context.startActivity(intent);
    }


    /**
     * 搜索界面
     *
     * @param context
     */
    public static void searchPage(Context context, int type) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("searchType", type);
        context.startActivity(intent);
    }

    /**
     * 草稿箱
     */
    public static void startDraftActivity(Context context) {
        Intent intent = new Intent(context, DraftActivity.class);
        context.startActivity(intent);
    }

    /**
     * 设置
     */
    public static void startSetActivity(Context context) {
        Intent intent = new Intent(context, SetActivity.class);
        context.startActivity(intent);
    }

    /**
     * 系统通知
     */
    public static void startNotifyActivity(Context context) {
        Intent intent = new Intent(context, SystemNotifyActivity.class);
        context.startActivity(intent);
    }

    /**
     * 编辑资料
     */
    public static void startEditeActivity(Context context, Serializable value) {
        Intent intent = new Intent(context, EditeInoforActivity.class);
        intent.putExtra("value", value);
        context.startActivity(intent);
    }

    /**
     * 跳到点评日志页面
     *
     * @param context
     */
    public static void startCommentActivity(Context context, int type) {
        Intent intent = new Intent(context, ReplyActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳到待处理中日志详情界面
     *
     * @param context
     * @param id
     */
    public static void startDiaryDetails(Context context, String id) {
        Intent intent = new Intent(context, DealWithDiaryDetailsActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    /**
     * 跳转到同意界面
     *
     * @param context
     * @param id
     */
    public static void startAgreeActivity(Context context, String id) {
        Intent intent = new Intent(context, AgreeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳轉到待處理指令頁面
     *
     * @param context
     * @param id
     */
    public static void startDealWithCommandActivity(Context context, int id) {
        Intent intent = new Intent(context, DealWithCommandDetailsActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    public static void startPraiseReplyPerson(Context context, String id) {
        Intent intent = new Intent(context, PraiseReplyActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    public static void startDepartmentsActivity(Context context) {
        Intent intent = new Intent(context, DepartmentSelectionActivity.class);
        context.startActivity(intent);
    }

    public static void startDepartmentsSunActivity(Context context, String json) {
        Intent intent = new Intent(context, DepartmentSunSelectionActivity.class);
        intent.putExtra("json", json);
        context.startActivity(intent);
    }


    public static void startFeedBackActivity(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到修改密码界面
     *
     * @param context
     */
    public static void startChangePasswordActivity(Context context) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到分享范围
     *
     * @param context
     */
    public static void startShareScopeActivity(Context context, String scopeString) {
        Intent intent = new Intent(context, ShareScopeActivity.class);
        intent.putExtra("scopeString", scopeString);
        context.startActivity(intent);
    }

    /**
     * 跳转 部门下面的 联系人列表
     *
     * @param context
     * @param id
     * @param name
     */
    public static void startDepartContectActivity(Context context, int id, String name) {
        Intent intent = new Intent(context, DepartmentContectsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", name);
        context.startActivity(intent);
    }

    /**
     * 跳转 部门下面的 联系人列表
     *
     * @param context
     * @param id
     * @param name
     */
    public static void startDepartContectActivity(Context context, int id, String name, String json) {
        Intent intent = new Intent(context, DepartmentContectsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", name);
        intent.putExtra("json", json);
        context.startActivity(intent);
    }

    /**
     * 抄送範圍 startActivityForResult
     *
     * @param activity
     */
    public static void startSelectSendScopeActivityForResult(Activity activity,boolean sendRange,int type) {
        Intent intent = new Intent(activity, SelectSendScopeActivity.class);
        intent.putExtra("sendRange",sendRange);
        intent.putExtra("type",type);
        activity.startActivityForResult(intent, IConstant.SENDSCOPE);
    }


    /**
     * 跳转 选择图片界面
     *
     * @param activity
     */
    public static void startSelectImageActivity(Activity activity) {
        Intent intent = new Intent(activity, SelectImageActivity.class);
        activity.startActivityForResult(intent, IConstant.SENDSCOPEOther);
    }

    /**
     * 启动本地文件页面
     *
     * @param activity
     */
    public static void startNativeFiles(Activity activity) {
        Intent intent = new Intent(activity, NativeFilesActivity.class);
        activity.startActivity(intent);
    }

    public static void startAlbumActivity(Activity activity, int number, String title) {
        Intent intent = new Intent(activity, NativeCommonActivity.class);
        intent.putExtra("page", number);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    public static void startSelectImageActivity(Activity activity, boolean cut) {
        Intent intent = new Intent(activity, SelectImageActivity.class);
        intent.putExtra("cut", cut);
        if (cut) {
            activity.startActivityForResult(intent, IConstant.CutRescode);
            return;
        }

        activity.startActivityForResult(intent, IConstant.SENDSCOPEOther);
    }

    public static void startSelectImageActivity(Activity activity, boolean cut,String  path) {
        Intent intent = new Intent(activity, SelectImageActivity.class);
        intent.putExtra("cut", cut);
        intent.putExtra("imagePaths", path);
        if (cut) {
            activity.startActivityForResult(intent, IConstant.CutRescode);
            return;
        }

        activity.startActivityForResult(intent, IConstant.SENDSCOPEOther);
    }


    /**
     * @param activity
     * @param bean
     * @param type     0 是打电话  1 是发短信
     */
    public static void startSelectMobileActivity(Context activity, ContactBean bean, int type) {
        Intent intent = new Intent(activity, SelectMobileActivity.class);
        intent.putExtra("value", bean);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }


    /**
     * 跳转 选择图片界面
     *
     * @param activity
     */
    public static void startContactDetailActivity(Activity activity, String uid) {
        Intent intent = new Intent(activity, ContactDetailActivity.class);
        intent.putExtra("uid", uid);
        activity.startActivity(intent);
    }

    public static void startContactDetailActivityByName(Activity activity, String uname) {
        Intent intent = new Intent(activity, ContactDetailActivity.class);
        intent.putExtra("uname", uname);
        activity.startActivity(intent);
    }

    public static void  startContactDetailActivityByBean(Activity activity, ContactBean bean) {
        Intent intent = new Intent(activity, ContactDetailActivity.class);
        intent.putExtra("bean", bean);
        activity.startActivity(intent);
    }
    /**
     *siliao
     * @param context
     */
    public static void startChat(Context context,int userid,String username) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("fromUserId",userid);
        intent.putExtra("username",username);
        context.startActivity(intent);
    }
    /**
     * 跳转 选择图片界面
     *
     * @param activity
     */
    public static void startShowImageActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, ShowImageActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }


    /**
     * 跳转 选择图片界面
     *
     * @param activity
     */
    public static void startFilesShowActivity(Activity activity, FileListsBean fs) {
        Intent intent = new Intent(activity, FileShowListActivity.class);
        intent.putExtra("files", fs);
        activity.startActivity(intent);
    }



    /**
     * 跳转 选择图片界面
     *
     * @param activity
     */
    public static void startImageToolShowActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, ShowImageToolActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }


    /**
     * 跳转 选择图片界面
     *
     * @param activity
     */
    public static void startSendRangeListActivity(Activity activity, SendRangesBean fs) {
        Intent intent = new Intent(activity, SendRangeActivity.class);
        intent.putExtra("files", fs);
        activity.startActivity(intent);
    }


    /**
     * 打开审批show的界面
     * @param context
     */
    public static void startApprovalInput(Context context, ApprovalTypesBean bean){
        Intent intent =  new Intent(context, ApprovalInputActivity.class);
        intent.putExtra("type",bean);
        context.startActivity(intent);
    }


    /**
     * 打开审批show的界面
     * @param context
     */
    public static void startApprovalEdite(Context context, String url){
        Intent intent =  new Intent(context, ApprovalInputActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    /**
     * 打开审批的类型的界面
     * @param context
     */
    public static void startApprovalTypesActivity(Context context,ApprovalTypesBean bean){
        Intent intent =  new Intent(context, ApprovalTypesActivity.class);
        intent.putExtra("bean",bean);
        context.startActivity(intent);
    }

    /**
     * 打开审批的类型的界面
     * @param context
     */
    public static void startApprovalTypesActivity(Context context){
        Intent intent =  new Intent(context, ApprovalTypesActivity.class);

        context.startActivity(intent);
    }

    /**
     * 打开审批的类型的界面
     * @param context
     */
    public static void startApprovalShowActivity(Context context,String url,int htmlId){
        Intent intent =  new Intent(context, ApprovalEditeActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("id",htmlId);
        context.startActivity(intent);
    }


    /**
     * 打开审批的类型的界面
     * @param context
     */
    public static void startApprovalShow1Activity(Context context,String url){
        Intent intent =  new Intent(context, ApprovalShowActivity.class);
        intent.putExtra("url",url);

        context.startActivity(intent);
    }

    /**
     * 编辑个性签名
     * @param context
     */
    public static void startEditeSignature(Activity context,String content){
        Intent intent =  new Intent(context, EditeUserSignature.class);
        intent.putExtra("content",content);
        context.startActivityForResult(intent, 100);
    }


}

