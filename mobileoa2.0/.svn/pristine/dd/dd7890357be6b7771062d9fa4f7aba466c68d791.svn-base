package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.Selection;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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
import com.idxk.mobileoa.utils.common.android.LogUtils;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2015/3/5.
 */
public class SendShareActivity extends BaseListViewActivity implements View.OnClickListener, BottomBar.OnBottomClick,
        AdapterView.OnItemClickListener {
    Map<String, String> map = new HashMap<String, String>();
    ShowImageGvAdapter imageAdapter;
    private TextView titleCancel;
    private TextView titleSend;
    private Button sendBtnScope;
    private EditText activity_sendShare_sendContent;
    private TextView title_common;
    private String picturePath = null;
    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            imageAdapter.appendData(picturePath);
        }
    };
    private BottomBar bottomBar;
    private Map<String, ContactBean> sendScopeMap;//发送范围
    private GridView activity_showPic;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sendshare);
        ToastTool.show("oncreate");
        //控件实例化
        titleCancel = (TextView) id2v(R.id.titleCancel);
        titleSend = (TextView) id2v(R.id.titleSend);
        sendBtnScope = (Button) id2v(R.id.activity_sendshare_sendscope);
        activity_sendShare_sendContent = (EditText) id2v(R.id.activity_sendShare_sendContent);
        Common.setOnAtLisener(activity_sendShare_sendContent, this, "@范围");
        activity_showPic = (GridView) id2v(R.id.activity_sendshare_showPic);
        imageAdapter = new ShowImageGvAdapter(this);
        activity_showPic.setOnItemClickListener(this);
        activity_showPic.setAdapter(imageAdapter);
        title_common = (TextView) id2v(R.id.title_common);
        title_common.setText(R.string.sendShare);
        //添加监听
        titleCancel.setOnClickListener(this);
        titleSend.setOnClickListener(this);
        sendBtnScope.setOnClickListener(this);
        bottomBar = (BottomBar) id2v(R.id.commonBottomBar);
        bottomBar.setOnBottomClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        boolean select = !Boolean.parseBoolean(view.getTag().toString());
        if (select) {
            IntentTool.startSelectImageActivity(this);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();

//        activity_sendShare_sendContent.setFocusable(true);
//        activity_sendShare_sendContent.setFocusableInTouchMode(true);
//        activity_sendShare_sendContent.requestFocus();
        Common.showSoftinput(activity_sendShare_sendContent, this);
        Selection.setSelection(activity_sendShare_sendContent.getText(), activity_sendShare_sendContent.getText().length());

    }

    @Override
    protected void onStop() {
        super.onStop();
        Common.hidenSoftinput(activity_sendShare_sendContent, this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleCancel:
                this.finish();
                break;
            case R.id.titleSend:
                if (activity_sendShare_sendContent.getText() == null || activity_sendShare_sendContent.getText().length() <= 0) {
                    ToastTool.show(R.string.notnull);
                    return;
                }

                if (sendScopeMap == null || sendScopeMap.size() < 1) {
                    ToastTool.show("发送范围不能为空");
                    return;
                }

                map.put("content", String.valueOf(activity_sendShare_sendContent.getText()));
                if (sendScopeMap != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                        stringBuilder.append(entry.getValue().getUid()).append(",");
                    }
                    map.put("scope", stringBuilder.toString());
                }


                if (picturePath != null) {
                    map.put("picturePath", picturePath);
                    map.put("type", "postimage");

                } else {
                    map.put("type", "post");
                }
                UserController.getInstance().sendShare(map, IConstant.TYPESHARE, new ViewCallBack());
                break;
            case R.id.activity_sendshare_sendscope:
                IntentTool.startSelectSendScopeActivityForResult(this, true, 1);
                break;
            case R.id.activity_sendshare_addattachment:
                ToastTool.show("addAttachment click");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


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
                sendBtnScope.setText(stringBuilder.toString());
            } else {
                for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                    sendBtnScope.setText(entry.getValue().getUname());
                }
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.ATCOLLEAGUE) {
            SerializableMap serializableMap = (SerializableMap) data.getSerializableExtra("map");
            sendScopeMap = serializableMap.getMap();
            for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                activity_sendShare_sendContent.append("@" + entry.getValue().getUname() + " ");
            }
            sendScopeMap.clear();
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.SENDSCOPEOther) {
            String path = data.getStringExtra("path");

            Logger.e("getPath>>>>>>" + path);

            picturePath = path;
            Bitmap bm =data.getParcelableExtra("data");
            Logger.e("000000000000000" + bm);
            if (bm!=null){
                imageAdapter.appendData(picturePath, bm);
                CommonActionController.getInstance().upLoadPicture(new UpLoadViewCallBack(), path, this,bm);

            }else{
                imageAdapter.appendData(picturePath);
                CommonActionController.getInstance().upLoadPicture(new UpLoadViewCallBack(), path, this);
            }


//            handler.sendEmptyMessageDelayed(0, 1000);
        }
    }

    @Override
    public void clickLeft() {

        IntentTool.startSelectImageActivity(this);
    }

    @Override
    public void clickMiddle() {
        ToastTool.show("addAttachment click");
    }

    @Override
    public void clickRight() {
        Intent atIntent = new Intent(this, SelectSendScopeActivity.class);
        atIntent.putExtra("atScope", "@范围");
        startActivityForResult(atIntent, IConstant.ATCOLLEAGUE);
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

            LogUtils.e(upLoadPictureResultModel.getData().getName());
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
//            ToastTool.show(getResources().getString(R.string.sendFailed));
            SendShareActivity.this.finish();
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            SendResultModel sendResultModel = (SendResultModel) result;
            if (sendResultModel.getFeed_id() == 0) {
                ToastTool.show(getResources().getString(R.string.sendFailed));
                SendShareActivity.this.finish();
            } else {
//                ToastTool.show(getResources().getString(R.string.sendSuccess));
                SendShareActivity.this.finish();
            }
            PreferceManager.getInsance().saveValueBYkey("isRefresh", "true", SendShareActivity.this);
        }
    }
}
