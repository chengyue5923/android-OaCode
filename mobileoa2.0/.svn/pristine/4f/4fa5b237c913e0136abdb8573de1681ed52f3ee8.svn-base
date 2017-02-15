package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Selection;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.BottomBar;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.CommonActionController;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.model.bean.MessageReceivedListItemBean;
import com.idxk.mobileoa.model.bean.PraiseResultModel;
import com.idxk.mobileoa.model.bean.RemindMeListModel;
import com.idxk.mobileoa.model.bean.SerializableMap;
import com.idxk.mobileoa.model.bean.UploadFileModel;
import com.idxk.mobileoa.model.bean.WeiboCommentListModel;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.LogUtils;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2015/3/7.
 */
public class ReplyActivity extends BaseActivity implements View.OnClickListener, ViewNetCallBack, BottomBar.OnBottomClick {
    HashMap<String, String> hashMap = new HashMap<>();
    String picturePath;
    private TextView titleCommon; //标题显示
    private TextView titleSend; //发布
    private TextView titleCancel; //取消
    private ImageView activity_reply_showPic; //取消
    private TextView nextPerson;
    private Intent intent;
    private EditText editTextContent;
    private Map<String, ContactBean> atPerson;//at person
    private BottomBar bottomBar;
    private Map<String, ContactBean> commentPersonMap; //点评人
    private String nextPersonId;

    /**
     * 0==回复
     * 1==同意
     * 2==不同意
     * 3==复议
     * 6==点评日志
     */

    @Override
    protected void initView() {
        setContentView(R.layout.activity_reply);


        editTextContent = (EditText) findViewById(R.id.activity_reply_sendcontent);
        Common.setOnAtLisener(editTextContent, this, "@范围");
        intent = getIntent();
        if (intent == null) {
            return;
        }
        Serializable serializable = intent.getSerializableExtra("object");
        if (serializable instanceof WeiboCommentListModel) {
            editTextContent.setText("回复@" + ((WeiboCommentListModel) serializable).getUser_info().getUname() + " : ");
            Selection.setSelection(editTextContent.getText(), editTextContent.getText().length());
        }

        titleCommon = (TextView) id2v(R.id.title_common);
        titleSend = (TextView) id2v(R.id.titleSend);
        titleCancel = (TextView) id2v(R.id.titleCancel);
        nextPerson = (TextView) id2v(R.id.nextPerson);
        activity_reply_showPic = (ImageView) id2v(R.id.activity_reply_showPic);

        bottomBar = (BottomBar) id2v(R.id.commonBottomBar);
        bottomBar.setItemGone(0);
        bottomBar.setItemGone(1);
//        bottomBar.setItemVisible(1);
        bottomBar.setOnBottomClickListener(this);

        switch (intent.getIntExtra("type", 0)) {
            case 0:
                titleCommon.setText(R.string.reply);
                titleSend.setText(R.string.fresh_send);
                nextPerson.setVisibility(View.GONE);
                break;
            case 1:
                titleCommon.setText(R.string.agree);
                titleSend.setText(R.string.fresh_send);
                nextPerson.setVisibility(View.VISIBLE);
                nextPerson.setOnClickListener(this);
                editTextContent.setText("同意。");
                Selection.setSelection(editTextContent.getText(), editTextContent.getText().length());
                break;
            case 2:
                titleCommon.setText(R.string.disagree);
                titleSend.setText(R.string.fresh_send);
                nextPerson.setVisibility(View.GONE);
                editTextContent.setText("不同意。");
                Selection.setSelection(editTextContent.getText(), editTextContent.getText().length());
                break;
            case 3:
                titleCommon.setText(R.string.replyagain);
                titleSend.setText(R.string.fresh_send);
                nextPerson.setVisibility(View.GONE);
                break;
            case 4:
                titleCommon.setText(R.string.replyResult);
                titleSend.setText(R.string.fresh_send);
                nextPerson.setVisibility(View.GONE);
                break;
            case 5:
                WeiboCommentListModel listModel = (WeiboCommentListModel) intent.getSerializableExtra("object");
                String name = listModel.getUser_info().getUname();
                String reply = getResources().getString(R.string.reply);
                titleCommon.setText(reply + name);
                titleSend.setText(R.string.fresh_send);
                nextPerson.setVisibility(View.GONE);
                editTextContent.setText(reply + "@" + name + ":");
                Selection.setSelection(editTextContent.getText(), editTextContent.getText().length());
                break;
            case 6:
                titleCommon.setText(R.string.commentDiary);
                titleSend.setText(R.string.fresh_send);
                nextPerson.setVisibility(View.GONE);
                break;


        }

        titleSend.setOnClickListener(this);
        titleCancel.setOnClickListener(this);
        nextPerson.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleSend:
                if (editTextContent == null || editTextContent.length() <= 0) {
                    ToastTool.show(R.string.notnull);
                    return;
                }
                hashMap.put("content", editTextContent.getText().toString());
                switch (intent.getIntExtra("type", 0)) {
                    case 0:
                        Serializable serializable = intent.getSerializableExtra("object");
                        if (serializable instanceof FreshListModel) {
                            hashMap.put("row_id", ((FreshListModel) serializable).getFeed_id());
                            hashMap.put("app_uid", ((FreshListModel) serializable).getUid());
                        } else if (serializable instanceof RemindMeListModel) {
                            hashMap.put("row_id", ((RemindMeListModel) serializable).getFeed_id());
                            hashMap.put("app_uid", ((RemindMeListModel) serializable).getUid());
                        } else if (serializable instanceof DealWithDiaryListItemBean) {
                            hashMap.put("row_id", ((DealWithDiaryListItemBean) serializable).feed_id);
                            hashMap.put("app_uid", ((DealWithDiaryListItemBean) serializable).uid);
                        } else if (serializable instanceof MessageReceivedListItemBean) {
                            hashMap.put("row_id", ((MessageReceivedListItemBean) serializable).row_id);
                            hashMap.put("app_uid", ((MessageReceivedListItemBean) serializable).app_uid);
//                            hashMap.put("to_uid", ((MessageReceivedListItemBean) serializable).uid);
//                            hashMap.put("to_comment_id", ((MessageReceivedListItemBean) serializable).comment_id);
                        } else if (serializable instanceof WeiboCommentListModel) {
                            hashMap.put("row_id", ((WeiboCommentListModel) serializable).getRow_id());
                            hashMap.put("app_uid", ((WeiboCommentListModel) serializable).getApp_uid());
                        }
                        break;
                    case 5:
                        WeiboCommentListModel weiboCommentListModel = (WeiboCommentListModel) intent.getSerializableExtra("object");
                        hashMap.put("row_id", weiboCommentListModel.getRow_id());
                        hashMap.put("to_comment_id", weiboCommentListModel.getComment_id());
                        hashMap.put("app_uid", weiboCommentListModel.getApp_uid());
                        hashMap.put("to_uid", weiboCommentListModel.getTo_uid());
                        break;
                    case 1:
                        DealWithDiaryListItemBean dealWithDiaryListItemBean = (DealWithDiaryListItemBean) intent.getSerializableExtra("object");
                        hashMap.put("row_id", dealWithDiaryListItemBean.feed_id);
                        hashMap.put("approve", nextPersonId);
                        hashMap.put("result", "yes");
                        break;
                    case 2:
                        DealWithDiaryListItemBean listItemBeanNo = (DealWithDiaryListItemBean) intent.getSerializableExtra("object");
                        hashMap.put("row_id", listItemBeanNo.feed_id);
                        hashMap.put("approve", nextPersonId);
                        hashMap.put("result", "no");
                        break;
                    case 3:
                        DealWithDiaryListItemBean listItemBeanReconsider = (DealWithDiaryListItemBean) intent.getSerializableExtra("object");
                        hashMap.put("row_id", listItemBeanReconsider.feed_id);
                        hashMap.put("approve", nextPersonId);
                        hashMap.put("result", "reconsider");
                        break;
                    case 4:
                        DealWithDiaryListItemBean listItemBeanReplyResult = (DealWithDiaryListItemBean) intent.getSerializableExtra("object");
                        hashMap.put("row_id", listItemBeanReplyResult.feed_id);
                        hashMap.put("approve", nextPersonId);
                        hashMap.put("result", "reconsider");
                        break;
                    case 6:
                        DealWithDiaryListItemBean listItemBean = (DealWithDiaryListItemBean) intent.getSerializableExtra("object");
                        hashMap.put("row_id", listItemBean.feed_id);
                        hashMap.put("app_uid", listItemBean.uid);
                        break;
                }
                UserController.getInstance().replyWeiBo(this, hashMap);
                break;
            case R.id.titleCancel:
                this.finish();
                break;
            case R.id.nextPerson:
                Intent intentPerson = new Intent(this, SelectPersonActivity.class);
                intentPerson.putExtra("commentPerson", "选择审批人");
                startActivityForResult(intentPerson, IConstant.COMMENTPERSON);
                break;
        }
    }

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
        PraiseResultModel praiseResultModel = (PraiseResultModel) result;
        if (praiseResultModel.getSuccess() == 1) {
//            ToastTool.show(R.string.sendSuccess);
        }
        setResult(RESULT_OK);
        this.finish();
    }

    @Override
    public void clickLeft() {
        IntentTool.startSelectImageActivity(this);
    }

    @Override
    public void clickMiddle() {
        IntentTool.startNativeFiles(this);
    }

    @Override
    public void clickRight() {
        Intent intent = new Intent(this, SelectSendScopeActivity.class);
        intent.putExtra("atScope", "@范围");
        startActivityForResult(intent, IConstant.ATCOLLEAGUE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IConstant.ATCOLLEAGUE) {
            SerializableMap serializableMap = (SerializableMap) data.getSerializableExtra("map");
            atPerson = serializableMap.getMap();
            for (Map.Entry<String, ContactBean> entry : atPerson.entrySet()) {
                editTextContent.append("@" + entry.getValue().getUname() + " ");
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.COMMENTPERSON) {
            SerializableMap serializableMap = (SerializableMap) data.getSerializableExtra("map");
            commentPersonMap = serializableMap.getMap();
            for (Map.Entry<String, ContactBean> entry : commentPersonMap.entrySet()) {
                nextPerson.setText(entry.getValue().getUname());
                nextPersonId = entry.getValue().getUid();
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.SENDSCOPEOther) {
            String path = data.getStringExtra("path");

            Bitmap bitmap = BitmapFactory.decodeFile(path);

            activity_reply_showPic.setImageBitmap(bitmap);
            picturePath = path;
            CommonActionController.getInstance().upLoadPicture(new UpLoadViewCallBack(), picturePath, this);

        }
    }

    public class UpLoadViewCallBack implements ViewNetCallBack {

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
            UploadFileModel upLoadPictureResultModel = (UploadFileModel) result;
            hashMap.put("attach_id", upLoadPictureResultModel.getData().getAttach_id());
            LogUtils.e(upLoadPictureResultModel.getData().getName());
        }
    }
}
