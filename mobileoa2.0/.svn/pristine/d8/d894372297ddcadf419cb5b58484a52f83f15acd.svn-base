package com.idxk.mobileoa.android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.Selection;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.ShowImageGvAdapter;
import com.idxk.mobileoa.android.ui.views.widget.BottomBar;
import com.idxk.mobileoa.android.ui.views.widget.ScreenInfo;
import com.idxk.mobileoa.android.ui.views.widget.WheelMain;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2015/3/13.
 */
public class SendCommandActivity extends BaseListViewActivity implements View.OnClickListener,
        BottomBar.OnBottomClick, AdapterView.OnItemClickListener {
    ShowImageGvAdapter imageAdapter;
    private TextView titleCommon;
    private TextView titleSend;
    private TextView titleCancel;
    private EditText sendCommandContent;
    private Button sendCommand_sendScope; //发送范围
    private Map<String, ContactBean> sendScopeMap;//发送范围map
    private Map<String, ContactBean> commentPersonMap; //点评人
    private TextView commentPerson; //点评人
    private BottomBar bottomBar;
    private RelativeLayout finishTimeLayout;
    private TextView finishTimeContent;
    private Button sendCommand_commentPerson;
    private GridView sendCommandImageView;
    private Map<String, String> map = new HashMap<String, String>();
    private int year, month, day, hour, min;
    private View totalView;
    private Button determine, cacel;
    private PopupWindow mPopupWindowDialog;
    private LayoutInflater inflater;
    private WheelMain wheelMain;
    private String picturePath = null;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_sendcommand);

        titleCommon = (TextView) id2v(R.id.title_common);
        titleCommon.setText(R.string.sendCommand);

        titleSend = (TextView) id2v(R.id.titleSend);
        titleSend.setOnClickListener(this);

        sendCommandContent = (EditText) id2v(R.id.sendCommandContent);
        Common.setOnAtLisener(sendCommandContent, this, "@范围");
        titleCancel = (TextView) id2v(R.id.titleCancel);
        titleCancel.setOnClickListener(this);

        sendCommand_sendScope = (Button) id2v(R.id.sendCommand_sendScope);
        sendCommand_sendScope.setOnClickListener(this);

        commentPerson = (TextView) id2v(R.id.sendCommand_commentPerson);
        commentPerson.setOnClickListener(this);

        bottomBar = (BottomBar) id2v(R.id.sendCommand_commonBottomBar);
        bottomBar.setOnBottomClickListener(this);

        finishTimeLayout = (RelativeLayout) id2v(R.id.finishTimeLayout);
        finishTimeLayout.setOnClickListener(this);

        finishTimeContent = (TextView) id2v(R.id.finishTimeContent);

        sendCommand_commentPerson = (Button) id2v(R.id.sendCommand_commentPerson);
        sendCommand_commentPerson.setOnClickListener(this);
        sendCommand_commentPerson.setText("执行人");

        sendCommandImageView = (GridView) id2v(R.id.sendCommandImageView);
        imageAdapter = new ShowImageGvAdapter(this);
        sendCommandImageView.setOnItemClickListener(this);
        sendCommandImageView.setAdapter(imageAdapter);
    }

    @Override
    protected void initData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        String time = format.format(calendar.getTime());
        finishTimeContent.setText(time);

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Selection.setSelection(sendCommandContent.getText(), sendCommandContent.getText().length());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        boolean select = !Boolean.parseBoolean(view.getTag().toString());
        if (select) {
            IntentTool.startSelectImageActivity(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleSend:
                if (sendCommandContent.getText() == null || sendCommandContent.getText().length() <= 0) {
                    ToastTool.show(R.string.notnull);
                    return;
                }
                map.put("content", String.valueOf(sendCommandContent.getText()));
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
                    ToastTool.show("执行人不能为空");
                    return;
                }
                if (finishTimeContent != null) {
                    map.put("order_end_datetime", finishTimeContent.getText().toString());
                }
                if (picturePath != null) {
                    map.put("picturePath", picturePath);
                    map.put("type", "postimage");
                } else {
                    map.put("type", "post");
                }
                UserController.getInstance().sendShare(map, IConstant.TYPECOMMAND, new ViewCallBack());
                break;
            case R.id.titleCancel:
                this.finish();
                break;
            case R.id.sendCommand_sendScope:
                IntentTool.startSelectSendScopeActivityForResult(this,true,2);
                break;
            case R.id.sendCommand_commentPerson:
                Intent commentIntent = new Intent(this, SelectPersonActivity.class);
                commentIntent.putExtra("commentPerson", "选择执行人");
                commentIntent.putExtra("command", String.valueOf(IConstant.TYPECOMMAND));
                startActivityForResult(commentIntent, IConstant.COMMENTPERSON);
                break;

            case R.id.finishTimeLayout:
                totalView = inflater.inflate(R.layout.choose_dialog, null);
                setPopupWindowDialog();
                ScreenInfo screenInfo = new ScreenInfo(this);
                wheelMain = new WheelMain(totalView, 0);
                wheelMain.screenheight = screenInfo.getHeight();
                wheelMain.initDateTimePicker(year, month, day, hour, min);
                DisplayMetrics dm = getResources().getDisplayMetrics();
                if (mPopupWindowDialog != null) {
                    mPopupWindowDialog.showAtLocation(
                            findViewById(R.id.finishTimeLayout), Gravity.BOTTOM, dm.widthPixels / 2, dm.heightPixels / 2);
                }
                bottomBtn();
                break;
        }
    }

    protected void setPopupWindowDialog() {
        determine = (Button) totalView.findViewById(R.id.textview_dialog_album);
        cacel = (Button) totalView.findViewById(R.id.textview_dialog_cancel);
        mPopupWindowDialog = new PopupWindow(totalView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindowDialog.setFocusable(true);
        mPopupWindowDialog.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindowDialog.update();
        mPopupWindowDialog.setBackgroundDrawable(new BitmapDrawable(
                getResources(), (Bitmap) null));
        mPopupWindowDialog.setOutsideTouchable(true);
    }

    protected void bottomBtn() {
        determine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    Date date = sdf.parse(wheelMain.getTime());
                    if (date.getTime() < System.currentTimeMillis()) {
                        ToastTool.show("完成时间不能小于当前时间");
                        return;
                    }
                    finishTimeContent.setText(wheelMain.getTime());
                    if (mPopupWindowDialog != null
                            && mPopupWindowDialog.isShowing()) {
                        mPopupWindowDialog.dismiss();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        cacel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mPopupWindowDialog != null
                        && mPopupWindowDialog.isShowing()) {
                    mPopupWindowDialog.dismiss();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == IConstant.SENDSCOPE) {
            SerializableMap serializableMap = (SerializableMap) data.getSerializableExtra("map");
            sendScopeMap = serializableMap.getMap();
            int departmentCount = 0;
            int colleagueCount = 0;
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
                sendCommand_sendScope.setText(stringBuilder.toString());
            } else {
                for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                    Logger.e("key= " + entry.getKey() + " and value= " + entry.getValue().getUname() + entry.getValue().getUid());
                    sendCommand_sendScope.setText(entry.getValue().getUname());

                }
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.COMMENTPERSON) {
            SerializableMap serializableMap = (SerializableMap) data.getSerializableExtra("map");
            commentPersonMap = serializableMap.getMap();
            if (commentPersonMap.size() >= 2) {
                commentPerson.setText(commentPersonMap.size() + "个同事");
            } else {
                for (Map.Entry<String, ContactBean> entry : commentPersonMap.entrySet()) {
                    Logger.e("key= " + entry.getKey() + " and value= " + entry.getValue().getUname() + entry.getValue().getUid());
                    commentPerson.setText(entry.getValue().getUname());

                }
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.ATCOLLEAGUE) {
            SerializableMap serializableMap = (SerializableMap) data.getSerializableExtra("map");
            sendScopeMap = serializableMap.getMap();
            for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                sendCommandContent.append("@" + entry.getValue().getUname() + " ");
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.SENDSCOPEOther) {
            String path = data.getStringExtra("path");

            imageAdapter.appendData(path);
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
            SendCommandActivity.this.finish();
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            SendResultModel sendResultModel = (SendResultModel) result;
            if (sendResultModel.getFeed_id() == 0) {
                ToastTool.show(getResources().getString(R.string.sendFailed));
                SendCommandActivity.this.finish();
            } else {
                SendCommandActivity.this.finish();
            }
            PreferceManager.getInsance().saveValueBYkey("isRefresh", "true", SendCommandActivity.this);
        }
    }
}
