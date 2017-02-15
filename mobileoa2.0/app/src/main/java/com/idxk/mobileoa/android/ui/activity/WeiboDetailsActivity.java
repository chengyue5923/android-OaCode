package com.idxk.mobileoa.android.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.FileListAdapter;
import com.idxk.mobileoa.android.ui.views.adapter.ShareScopeAdapter;
import com.idxk.mobileoa.android.ui.views.adapter.WeiBoDetailsOperateAdapter;
import com.idxk.mobileoa.android.ui.views.adapter.WeiboFilesAdapter;
import com.idxk.mobileoa.android.ui.views.dialog.WriteReplyDialog;
import com.idxk.mobileoa.android.ui.views.dialog.factory.DialogFacory;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.config.constant.Constant;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.config.parse.UrlRes;
import com.idxk.mobileoa.exception.CommentNoDataException;
import com.idxk.mobileoa.exception.PraiseNoDataException;
import com.idxk.mobileoa.exception.ResolveException;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.AttachBean;
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.model.bean.PraiseListModel;
import com.idxk.mobileoa.model.bean.WeiboCommentListModel;
import com.idxk.mobileoa.model.bean.WeiboDetailsModel;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.FileIntent;
import com.idxk.mobileoa.utils.common.android.GsonTool;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.DateTools;
import com.idxk.mobileoa.utils.common.java.StaticUtils;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.FileDownListener;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.file.ImageLoaderManager;
import com.idxk.mobileoa.utils.net.connect.http.file.LoadFileThread;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 微博详情界面
 */
public class WeiboDetailsActivity extends BaseListViewActivity implements View.OnClickListener, MainTitleView.OnTitleClick,
        AdapterView.OnItemClickListener {
    FreshListModel model;
    WeiboDetailsModel weiboDetailsModel;
    LinearLayout approvalLayout;
    LinearLayout imagesContain;
    ListView filesLv;
    WeiboFilesAdapter weiboFilesAdapter;
    Dialog dialog;
    private ImageView weiboPersonPicture;
    private Context context;
    private TextView weiboPersonName;
    private TextView weiboTime;
    private TextView weiboContent;
    private TextView weiBoDetailsReplyNumber;
    private TextView weiBoDetailsPraiseNumber;
    private ListView weiBoDetailsSetListView;
    private List<WeiboCommentListModel> weiBoDetailsOperateListItemBeans;
    private WeiBoDetailsOperateAdapter weiBoDetailsOperateAdapter;
    private TextView weiBoDetailsReply; //回复
    private TextView weiBoDetailsDelete; //删除
    private MainTitleView homeCommonTitle;
    private String feedId = null; //微博Id
    private TextView sendScope;
    private String scopeString;
    private LinearLayout weiBoDetailsReplyLayout;//回复栏
    private LinearLayout weiBoDetailsPraiseLayout;//赞栏
    private ImageView weiBoDetailsZan;
    private ImageView fileIcon;
    private ListView weiBoDetailsPraiseList;//赞列表
    private List<PraiseListModel> listModels;
    private ShareScopeAdapter praiseAdapter;//赞列表适配器
    private TextView diaryContent_todayWorkSummaryContent;
    private TextView diaryContent_tomorrowWorkPlanContent;
    private TextView diaryContent_workSummaryContent;
    private LinearLayout diaryContentLayout;
    private LinearLayout ll_popup;
    private String channel;
    private String feed_type;//四种类型
    private View parentView;
    private PopupWindow popupWindow;
    private Button popUp_replayButton, popUp_dimiss;
    private int clickPosition;

    @Override
    protected void initView() {
        parentView = getLayoutInflater().inflate(R.layout.activity_weibodetails, null);
        setContentView(parentView);
        homeCommonTitle = (MainTitleView) id2v(R.id.titleLayout);
        homeCommonTitle.setOnTitleClickLisener(this);
        weiboPersonPicture = (ImageView) id2v(R.id.weiboPersonPicture);
        weiboPersonPicture.setOnClickListener(this);
        imagesContain = (LinearLayout) id2v(R.id.imagesContain);
        fileIcon = (ImageView) id2v(R.id.fileIcon);
        weiboPersonName = (TextView) id2v(R.id.weiboPersonName);
        weiboTime = (TextView) id2v(R.id.weiboTime);
        weiboContent = (TextView) id2v(R.id.weiboContent);
        weiBoDetailsReplyNumber = (TextView) id2v(R.id.weiboDetailsReplyNumber);
        weiBoDetailsPraiseNumber = (TextView) id2v(R.id.weiboDetailsPraiseNumber);
        weiBoDetailsReplyNumber.setOnClickListener(this);
        weiBoDetailsPraiseNumber.setOnClickListener(this);
        weiBoDetailsReply = (TextView) id2v(R.id.weiBoDetailsReply);
        filesLv = (ListView) id2v(R.id.filesLv);
        weiBoDetailsDelete = (TextView) id2v(R.id.weiBoDetailsDelete);
        weiBoDetailsDelete.setOnClickListener(this);
        sendScope = (TextView) id2v(R.id.sendScope);
        weiBoDetailsSetListView = (ListView) id2v(R.id.weiBoDetailsSetListView);
        weiBoDetailsSetListView.setOnItemClickListener(this);
        weiBoDetailsOperateListItemBeans = new ArrayList<>();
        weiBoDetailsOperateAdapter = new WeiBoDetailsOperateAdapter(this, weiBoDetailsOperateListItemBeans);
        weiBoDetailsSetListView.setAdapter(weiBoDetailsOperateAdapter);
        weiBoDetailsReplyLayout = (LinearLayout) id2v(R.id.weiBoDetailsReplyLayout);
        weiBoDetailsReplyLayout.setOnClickListener(this);
        weiBoDetailsPraiseLayout = (LinearLayout) id2v(R.id.weiBoDetailsPraiseLayout);
        approvalLayout = (LinearLayout) id2v(R.id.approvalLayout);
        approvalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.showDev(v.getTag().toString());

                String oauth_token = PreferceManager.getInsance().getValueBYkey(WeiboDetailsActivity.this, "oauth_token");
                String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(WeiboDetailsActivity.this, "oauth_token_secret");
                String url = UrlRes.getInstance().getUrlSever() + Constant.APPROVAL_URL + "&oauth_token=" + oauth_token
                        + "&oauth_token_secret=" + oauth_token_secret + "&form_id=" + v.getTag().toString();

                Logger.e("click=" + url);
                IntentTool.startApprovalShow1Activity(WeiboDetailsActivity.this, url);


            }
        });
        weiBoDetailsPraiseLayout.setOnClickListener(this);
        weiBoDetailsZan = (ImageView) id2v(R.id.weiBoDetailsZan);
        weiBoDetailsPraiseList = (ListView) id2v(R.id.weiBoDetailsPraiseList);
        listModels = new ArrayList<>();
        praiseAdapter = new ShareScopeAdapter(this, listModels);
        weiBoDetailsPraiseList.setAdapter(praiseAdapter);
        weiBoDetailsSetListView.setVisibility(View.VISIBLE);
        diaryContent_todayWorkSummaryContent = (TextView) id2v(R.id.diaryContent_todayWorkSummaryContent);
        diaryContent_tomorrowWorkPlanContent = (TextView) id2v(R.id.diaryContent_tomorrowWorkPlanContent);
        diaryContent_workSummaryContent = (TextView) id2v(R.id.diaryContent_workSummaryContent);
        diaryContentLayout = (LinearLayout) id2v(R.id.diaryContentLayout);
        popupWindow = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.popupwindow_details_item, null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_transparent));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popUp_replayButton = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        popUp_replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                ll_popup.clearAnimation();
                IntentTool.replyPage(WeiboDetailsActivity.this, 0, weiBoDetailsOperateListItemBeans.get(clickPosition));
            }
        });
        popUp_dimiss = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        popUp_dimiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                ll_popup.clearAnimation();
            }
        });
        weiboFilesAdapter = new WeiboFilesAdapter(this);
        filesLv.setAdapter(weiboFilesAdapter);
        filesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AttachBean bean = weiboFilesAdapter.getItem(position);
                new LoadFileThread(new LoadLener(bean.getExtension())).execute(bean.getAttach_url(), FileListAdapter.getInnerSd() + File.separator + "zmt" + File.separator + bean.getAttach_name());
            }
        });
        Common.setListViewHeightBasedOnChildren(filesLv);
        dialog = DialogFacory.getInstance().createRunDialog(this);
    }

    @Override
    protected void initData() {
        this.context = this;
        model = (FreshListModel) getIntent().getSerializableExtra("file");
        if (model == null) {
            feedId = getIntent().getStringExtra("feedId");
        } else {
            feedId = model.getFeed_id();
        }
        channel = getIntent().getStringExtra("channel");
        if ("1".equals(channel)) {
            homeCommonTitle.setCenterTitle("分享详情");
        } else if ("2".equals(channel)) {
            homeCommonTitle.setCenterTitle("日志详情");
        } else if ("3".equals(channel)) {
            homeCommonTitle.setCenterTitle("指令详情");
        } else if ("4".equals(channel)) {
            homeCommonTitle.setCenterTitle("审批详情");
        }
        if ("2".equals(channel)) {
            diaryContentLayout.setVisibility(View.VISIBLE);
            weiboContent.setVisibility(View.GONE);
        } else {
            diaryContentLayout.setVisibility(View.GONE);
            weiboContent.setVisibility(View.VISIBLE);
        }
        setZanValue();
        UserController.getInstance().getPraiseListForWeiBo(new PraiseListViewNetCallBack(), feedId);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        StaticUtils.backActivity = 1;
        UserController.getInstance().getWeiboDetails(feedId, new ViewCallBack());
        UserController.getInstance().getWeiboCommentList(feedId, new ViewCommentListCallBack());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weiBoDetailsReplyLayout:
                if (weiboDetailsModel != null) {
                    if (weiboDetailsModel.getChannel_id() == 4 && weiboDetailsModel.getFeed_status() != null && weiboDetailsModel.getApproval_user_info() != null) {
                        String myuid = PreferceManager.getInsance().getValueBYkey(context, "uid");
                        if (myuid.equals(String.valueOf(weiboDetailsModel.getApproval_user_info().getUid())) && "wait".equals(weiboDetailsModel.getFeed_status())) {
                            DealWithDiaryListItemBean dealWithDiaryListItemBean = new DealWithDiaryListItemBean();
                            dealWithDiaryListItemBean.feed_id = weiboDetailsModel.getFeed_id();
                            WriteReplyDialog writeReplyDialog = new WriteReplyDialog(context, "1", dealWithDiaryListItemBean);
                            writeReplyDialog.show();
                        } else {
                            IntentTool.replyPageForResult(this, 0, model);
                        }
                    } else {
                        IntentTool.replyPageForResult(this, 0, model);
                    }

                }
                break;
            case R.id.weiBoDetailsDelete:
                ToastTool.show("weiBoDetailsDelete");
                break;
            case R.id.weiBoDetailsPraiseLayout:
                if (weiboDetailsModel != null) {
                    if (weiboDetailsModel.getIs_digg() == 0) {
                        UserController.getInstance().praiseWeiBo(feedId, new PraiseResult());
                        weiboDetailsModel.setIs_digg(1);
                        weiBoDetailsZan.setImageResource(R.drawable.zaned);
                        weiboDetailsModel.setDigg_count(weiboDetailsModel.getDigg_count() + 1);
                        String praiseFormat = "赞 " + context.getResources().getString(R.string.person_praise);
                        String praiseFinal = String.format(praiseFormat, weiboDetailsModel.getDigg_count());
                        weiBoDetailsPraiseNumber.setText(praiseFinal);
                    } else {
                        UserController.getInstance().cancelPraiseWeiBo(feedId, new PraiseResult());
                        weiBoDetailsZan.setImageResource(R.drawable.zan);
                        weiboDetailsModel.setIs_digg(0);
                        weiboDetailsModel.setDigg_count(weiboDetailsModel.getDigg_count() - 1);
                        String praiseFormat = "赞 " + context.getResources().getString(R.string.person_praise);
                        String praiseFinal = String.format(praiseFormat, weiboDetailsModel.getDigg_count());
                        weiBoDetailsPraiseNumber.setText(praiseFinal);
                    }
                }
                break;
            case R.id.sendScope:

                try {
                    Object jsonObject = weiboDetailsModel.getScope_info().getData();
                    HashMap<String, String> hashMap1 = GsonTool.jsonToHas(jsonObject.toString());
                    List<HashMap<String, String>> hashMapList = new ArrayList<>();
                    Logger.e("GetSize" + hashMap1.size());
                    for (Map.Entry<String, String> entry : hashMap1.entrySet()) {
                        Object key = entry.getKey();
                        Object val = entry.getValue();
                        Logger.e(key.toString() + val.toString());
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put(key.toString(), val.toString());
                        hashMapList.add(hashMap);
                    }
                    IntentTool.startSendRangeListActivity((Activity) context, ContactController.getInstance().getMixList(hashMapList));
                } catch (ResolveException e) {
                    Logger.e(e.getLocalizedMessage());
                }


                break;

            case R.id.weiboDetailsReplyNumber:
                clickReply();
                break;
            case R.id.weiboDetailsPraiseNumber:
                clickPraise();
                break;
            case R.id.weiboPersonPicture:
                IntentTool.startContactDetailActivityByName((Activity) context, model.getUname());
                break;
        }
    }

    private void clickReply() {
        weiBoDetailsSetListView.setVisibility(View.VISIBLE);
        weiBoDetailsPraiseList.setVisibility(View.GONE);
        dealException(new CommentNoDataException(), praiseAdapter, weiBoDetailsPraiseList, false);

        if (weiBoDetailsOperateAdapter.getCount() == 0) {
//            dealException(new CommentNoDataException(), weiBoDetailsOperateAdapter, weiBoDetailsSetListView, true);
        } else {
            dealException(new CommentNoDataException(), weiBoDetailsOperateAdapter, weiBoDetailsSetListView, false);
        }
    }

    private void clickPraise() {
        weiBoDetailsSetListView.setVisibility(View.GONE);
        weiBoDetailsPraiseList.setVisibility(View.VISIBLE);
        dealException(StaticUtils.getExcepiton(), weiBoDetailsOperateAdapter, weiBoDetailsSetListView, false);
        if (praiseAdapter.getCount() == 0) {
//            dealException(new PraiseNoDataException(), praiseAdapter, weiBoDetailsPraiseList, true);
        } else {
            dealException(new PraiseNoDataException(), praiseAdapter, weiBoDetailsPraiseList, false);
        }
    }

    private void setZanValue() {
        if (weiboDetailsModel != null) {
            if (weiboDetailsModel.getIs_digg() == 0) {
                weiBoDetailsZan.setImageResource(R.drawable.zan);
            } else {
                weiBoDetailsZan.setImageResource(R.drawable.zaned);
            }
            if (weiboDetailsModel.getDigg_count() == 0) {
                weiBoDetailsPraiseNumber.setText("赞 ");
            } else {
                weiBoDetailsPraiseNumber.setText("赞 " + weiboDetailsModel.getDigg_count());
            }
        } else {
            weiBoDetailsPraiseNumber.setText("赞 ");
        }
    }

    @Override
    public void clickLeft() {
        this.finish();
    }

    @Override
    public void clickRight() {


    }

    @Override
    public void clickCenterTitle() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == 1000) {
            UserController.getInstance().getWeiboCommentList(feedId, new ViewCommentListCallBack());
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.COMMENTLISTREFRESH) {
            UserController.getInstance().getWeiboCommentList(feedId, new ViewCommentListCallBack());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ll_popup.startAnimation(AnimationUtils.loadAnimation(WeiboDetailsActivity.this, R.anim.translate_popupwindow));
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 10);
        clickPosition = i;
    }

    @Override
    public void tryAgin(View view) {

        if (view == weiBoDetailsSetListView) {
            UserController.getInstance().getWeiboCommentList(feedId, new ViewCommentListCallBack());
            return;
        }

        if (view == weiBoDetailsPraiseList) {
            UserController.getInstance().getPraiseListForWeiBo(new PraiseListViewNetCallBack(), feedId);
        }
    }

    public class LoadLener implements FileDownListener {
        String ex;

        public LoadLener(String ex) {
            this.ex = ex;
        }

        @Override
        public void starDownLoad() {
            dialog.show();
        }

        @Override
        public void endDownLoad(File file, boolean isSuess) {
            dialog.dismiss();
            if (isSuess) {
                Logger.e("下载完成");
                try {
                    startActivity(FileIntent.getOfficeIntent(file.getPath(), ex));
                } catch (Exception e) {
                    ToastTool.show("没有程序来支持附件的打开！请到相关市场下载相关office 程序！");
                }

            }
        }


    }

    public class PraiseResult implements ViewNetCallBack {
        @Override
        public void onConnectStart() {
        }

        @Override
        public void onConnectEnd() {
        }

        @Override
        public void onFail(Exception e) {
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            UserController.getInstance().getPraiseListForWeiBo(new PraiseListViewNetCallBack(), feedId);

        }
    }

    private class PraiseListViewNetCallBack implements ViewNetCallBack {
        @Override
        public void onConnectStart() {

        }

        @Override
        public void onConnectEnd() {

        }

        @Override
        public void onFail(Exception e) {
//            dealException(e, praiseAdapter, weiBoDetailsPraiseList);
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {


            Logger.e(String.valueOf(result instanceof PraiseListModel));
            if (result instanceof PraiseListModel) {
                PraiseListModel praiseListModel = (PraiseListModel) result;
                if ("00002".equals(praiseListModel.getCode())) {
                    listModels.clear();
                    praiseAdapter.setData(listModels);
                }
                return;
            }
            if (result instanceof List) {
                listModels.clear();
                listModels = (List<PraiseListModel>) result;
                praiseAdapter.setData(listModels);
            }
            clickPraise();
        }
    }

    public class ViewCallBack implements ViewNetCallBack {
        @Override
        public void onConnectStart() {

        }

        @Override
        public void onConnectEnd() {

        }

        @Override
        public void onFail(Exception e) {
//            ToastTool.show(getResources().getString(R.string.loginFaile));
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            weiboDetailsModel = (WeiboDetailsModel) result;
            setZanValue();


            if (!StringTools.isNullOrEmpty(weiboDetailsModel.getApproval_form_id())&&
                    !StringTools.toTrim(weiboDetailsModel.getApproval_form_id()).equals("0")) {
                approvalLayout.setVisibility(View.VISIBLE);
                approvalLayout.setTag(weiboDetailsModel.getApproval_form_id());
            } else {
                approvalLayout.setVisibility(View.GONE);
            }

            if (weiboDetailsModel.getChannel_id() == 4
                    && weiboDetailsModel.getFeed_status() != null
                    && weiboDetailsModel.getApproval_user_info() != null) {
                String myuid = PreferceManager.getInsance().getValueBYkey(context, "uid");
                if (myuid.equals(String.valueOf(weiboDetailsModel.getApproval_user_info().getUid())) && "wait".equals(weiboDetailsModel.getFeed_status())) {
                    weiBoDetailsReply.setText("批复");
                } else {
                    weiBoDetailsReply.setText("回复");
                }
            } else {
                weiBoDetailsReply.setText("回复");
            }

            feedId = weiboDetailsModel.getFeed_id();
            feed_type = weiboDetailsModel.getFeed_type();
            ImageLoaderManager.getInstance().disPlayImage(weiboDetailsModel.getAvatar_middle(), weiboPersonPicture);
//            Picasso.with(context).load(weiboDetailsModel.getAvatar_middle()).into(weiboPersonPicture);
            weiboPersonName.setText(weiboDetailsModel.getUname());
            String dateString = DateTools.friendlyTime(Long.parseLong(weiboDetailsModel.getPublish_time()) * 1000L);
            weiboTime.setText(dateString);
            weiboContent.setText(weiboDetailsModel.getContent());
            scopeString = weiboDetailsModel.getScope_info().getPlainText();
            if (StringTools.isNullOrEmpty(scopeString)) {
                sendScope.setVisibility(View.GONE);
            } else {
                sendScope.setOnClickListener(WeiboDetailsActivity.this);
//                sendScope.setText(getResources().getString(R.string.sendScope) + " " + scopeString);
            }


            if ("2".equals(channel)) {

                diaryContent_todayWorkSummaryContent.setText(StaticUtils.dealWithString(weiboDetailsModel.getContent_summary(), WeiboDetailsActivity.this));
                diaryContent_tomorrowWorkPlanContent.setText(StaticUtils.dealWithString(weiboDetailsModel.getContent_plan(), WeiboDetailsActivity.this));
                diaryContent_workSummaryContent.setText(StaticUtils.dealWithString(weiboDetailsModel.getContent(), WeiboDetailsActivity.this));

                diaryContent_todayWorkSummaryContent.setClickable(true);
                diaryContent_tomorrowWorkPlanContent.setClickable(true);
                diaryContent_workSummaryContent.setClickable(true);

                diaryContent_todayWorkSummaryContent.setMovementMethod(LinkMovementMethod.getInstance());
                diaryContent_tomorrowWorkPlanContent.setMovementMethod(LinkMovementMethod.getInstance());
                diaryContent_workSummaryContent.setMovementMethod(LinkMovementMethod.getInstance());

            } else {
                weiboContent.setText(StaticUtils.dealWithString(weiboDetailsModel.getContent(), WeiboDetailsActivity.this));
                weiboContent.setClickable(true);
                weiboContent.setMovementMethod(LinkMovementMethod.getInstance());
            }

            if (weiboDetailsModel.hasAttach()) {
                if (weiboDetailsModel.isFile()) {
                    weiboFilesAdapter.setRes(weiboDetailsModel.getAttach());
                    fileIcon.setVisibility(View.VISIBLE);

                } else {
                    imagesContain.removeAllViews();
                    for (int i = 0; i < weiboDetailsModel.getAttach().size(); i++) {
                        AttachBean bean = weiboDetailsModel.getAttach().get(i);
                        try {
                            View view = getLayoutInflater().inflate(R.layout.view_weibo_detail_item, null);
                            ImageView fileImage = (ImageView) view.findViewById(R.id.fileImage);
                            ImageLoaderManager.getInstance().disPlayImage(bean.getAttach_small(), fileImage);
                            view.setTag(i);
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        int index = Integer.parseInt(v.getTag().toString());
                                        IntentTool.scanPicture(context, weiboDetailsModel.getAttach(), index);
                                    } catch (Exception e) {
                                        Logger.e(e.getLocalizedMessage(), e);
                                    }
                                }
                            });
                            Logger.e("------file size-------" + bean.getAttach_small());
                            imagesContain.addView(view);
                        } catch (Exception e) {
                            Logger.e(e.getLocalizedMessage(), e);
                        }

                    }
                }
            } else {
                Logger.e("else");
            }


        }
    }


    public class ViewCommentListCallBack implements ViewNetCallBack {
        @Override
        public void onConnectStart() {

        }

        @Override
        public void onConnectEnd() {

        }

        @Override
        public void onFail(Exception e) {
            dealException(new CommentNoDataException(), weiBoDetailsOperateAdapter, weiBoDetailsSetListView);
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            weiBoDetailsOperateListItemBeans = (List<WeiboCommentListModel>) result;
            String replyFinal = "回复 " + weiBoDetailsOperateListItemBeans.size();
            weiBoDetailsReplyNumber.setText(replyFinal);
            weiBoDetailsOperateAdapter.setData(weiBoDetailsOperateListItemBeans);
            clickReply();
        }
    }

}


