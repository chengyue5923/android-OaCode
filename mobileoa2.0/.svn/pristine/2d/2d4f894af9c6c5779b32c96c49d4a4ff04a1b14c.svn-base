package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Selection;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.ShowImageGvAdapter;
import com.idxk.mobileoa.android.ui.views.widget.BottomBar;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.CommonActionController;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.SendResultModel;
import com.idxk.mobileoa.model.bean.SerializableMap;
import com.idxk.mobileoa.model.bean.UploadFileModel;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2015/3/23.
 */
public class SendExamineActivity extends BaseListViewActivity implements View.OnClickListener,
        ViewNetCallBack, BottomBar.OnBottomClick, AdapterView.OnItemClickListener {
    GridView icon;
    Map<String, String> map = new HashMap<>();
    String picturePath;
    ShowImageGvAdapter imageAdapter;
    LinearLayout htmlLayout;
    int htmlId;
    String htmlUrl;
    private TextView title; //标题
    private TextView titleCancel;//取消
    private TextView titleSend;//发送
    private EditText sendExamineContent;
    private Map<String, ContactBean> sendScopeMap;//发送范围map @的人也在发送范围内
    private TextView sendExamine_sendScope;
    private Map<String, ContactBean> commentPersonMap; //点评人Map
    private Button commentPerson;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sendexamine);

        title = (TextView) id2v(R.id.title_common);
        icon = (GridView) id2v(R.id.icon);
        imageAdapter = new ShowImageGvAdapter(this);
        icon.setOnItemClickListener(this);
        icon.setAdapter(imageAdapter);
        title.setText(R.string.sendExamine);

        titleCancel = (TextView) id2v(R.id.titleCancel);
        htmlLayout = (LinearLayout) id2v(R.id.htmlLayout);
        titleCancel.setOnClickListener(this);
        htmlLayout.setOnClickListener(this);

        titleSend = (TextView) id2v(R.id.titleSend);
        titleSend.setOnClickListener(this);

        BottomBar bottomBar = (BottomBar) id2v(R.id.sendExamine_commonBottomBar);
        bottomBar.setOnBottomClickListener(this);

        sendExamineContent = (EditText) id2v(R.id.sendExamineContent);
        Common.setOnAtLisener(sendExamineContent, this, "@范围");

        sendExamine_sendScope = (Button) id2v(R.id.sendExamine_sendScope);
        sendExamine_sendScope.setOnClickListener(this);

        commentPerson = (Button) id2v(R.id.sendExamine_commentPerson);
        commentPerson.setOnClickListener(this);
        commentPerson.setText("审批人");

    }

    @Override
    protected void initData() {
        htmlId = getIntent().getIntExtra("htmlId", -1);
        htmlUrl = getIntent().getStringExtra("url");
        Logger.e("---html-id=" + htmlId);
        Logger.e("---html-url=" + htmlUrl);
        if (htmlId != -1) {
            htmlLayout.setVisibility(View.VISIBLE);

            map.put("approval_form_id", String.valueOf(htmlId));
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        boolean select = !Boolean.parseBoolean(view.getTag().toString());
        if (select) {
            IntentTool.startSelectImageActivity(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Selection.setSelection(sendExamineContent.getText(), sendExamineContent.getText().length());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleCancel:
                this.finish();
                break;
            case R.id.htmlLayout:
//                this.finish();
                //--todo 拼接html的方法

                IntentTool.startApprovalShowActivity(this, htmlUrl, htmlId);

                break;
            case R.id.titleSend:
                if (sendExamineContent.getText() == null || sendExamineContent.getText().length() <= 0) {
                    ToastTool.show(R.string.notnull);
                    return;
                }

                map.put("content", String.valueOf(sendExamineContent.getText()));
                if (sendScopeMap != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                        stringBuilder.append(entry.getValue().getUid()).append(",");
                    }
//                    if(sendScopeMap.size()>0){
//                        stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
//
//                    }
                    map.put("scope", stringBuilder.toString());
                }
                if (commentPersonMap != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map.Entry<String, ContactBean> entry : commentPersonMap.entrySet()) {
                        stringBuilder.append(entry.getValue().getUid()).append(",");
                    }
//                    if(commentPersonMap.size()>0){
//                        stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
//
//                    }

                    map.put("commentator_uid", stringBuilder.toString());
                } else {
                    ToastTool.show("审批人不能为空");
                    return;
                }
                if (!StringTools.isNullOrEmpty(picturePath)) {
                    map.put("picturePath", picturePath);
                    map.put("type", "postimage");
                } else {
                    map.put("type", "post");
                }

                UserController.getInstance().sendShare(map, IConstant.TYPEEXAMINE, this);
                break;
            case R.id.sendExamine_sendScope:
                IntentTool.startSelectSendScopeActivityForResult(this, false, 0);
                break;
            case R.id.sendExamine_commentPerson:
                Intent intent = new Intent(this, SelectPersonActivity.class);
                intent.putExtra("commentPerson", "选择审批人");
                startActivityForResult(intent, IConstant.COMMENTPERSON);
                break;
        }
    }

    @Override
    public void clickLeft() {
        IntentTool.startSelectImageActivity(this);
    }

    @Override
    public void clickMiddle() {

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
            sendScopeMap = serializableMap.getMap();
            for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                sendExamineContent.append("@" + entry.getValue().getUname() + " ");
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.SENDSCOPE) {
            int departmentCount = 0;
            int colleagueCount = 0;
            SerializableMap serializableMap = (SerializableMap) data.getSerializableExtra("map");
            sendScopeMap = serializableMap.getMap();
            StringBuilder stringBuilder = new StringBuilder();
            if (sendScopeMap.size() >= 2) {
                for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                    if (entry.getValue().getUid().startsWith("d_")) {
                        departmentCount++;
                    } else if (entry.getValue().getUid().startsWith("all")) {
                        stringBuilder.append(getResources().getString(R.string.allCompany));
                    } else {
                        colleagueCount++;
                    }
                }
                if (departmentCount > 0) {
                    stringBuilder.append(departmentCount + "个部门").append(",");
                }

                if (colleagueCount > 0) {
                    stringBuilder.append(colleagueCount + "个同事");
                }
                sendExamine_sendScope.setText(stringBuilder.toString());
            } else {
                for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                    sendExamine_sendScope.setText(entry.getValue().getUname());
                }
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.COMMENTPERSON) {
            SerializableMap serializableMap = (SerializableMap) data.getSerializableExtra("map");
            commentPersonMap = serializableMap.getMap();
            for (Map.Entry<String, ContactBean> entry : commentPersonMap.entrySet()) {
                commentPerson.setText(entry.getValue().getUname());
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.SENDSCOPEOther) {
            String path = data.getStringExtra("path");
            Logger.e("path=" + path);


            imageAdapter.appendData(path);
            picturePath = path;
            Bitmap bm = data.getParcelableExtra("data");
            if (bm != null) {
                imageAdapter.appendData(picturePath, bm);
                CommonActionController.getInstance().upLoadPicture(new UpLoadViewCallBack(), path, this, bm);

            } else {
                imageAdapter.appendData(picturePath);
                CommonActionController.getInstance().upLoadPicture(new UpLoadViewCallBack(), path, this);
            }
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

        SendResultModel sendResultModel = (SendResultModel) result;
        if (sendResultModel.getFeed_id() == 0) {
            ToastTool.show(getResources().getString(R.string.sendFailed));
            SendExamineActivity.this.finish();
        } else {
            SendExamineActivity.this.finish();
        }
        PreferceManager.getInsance().saveValueBYkey("isRefresh", "true", SendExamineActivity.this);
    }

    @Override
    public void tryAgin(View view) {

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
            if (!StringTools.isNullOrEmpty(map.get("attach_id"))) {
                String pathAll = map.get("attach_id");
                pathAll = pathAll + "|" + upLoadPictureResultModel.getData().getAttach_id();
                map.put("attach_id", pathAll);
            } else {
                map.put("attach_id", upLoadPictureResultModel.getData().getAttach_id());
            }
            Logger.e(upLoadPictureResultModel.getData().getName());
        }
    }
}
