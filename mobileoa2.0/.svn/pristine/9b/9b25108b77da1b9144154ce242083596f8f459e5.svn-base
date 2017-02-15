package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Selection;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2015/3/13.
 */
public class SendDiaryActivity extends BaseListViewActivity implements View.OnClickListener, BottomBar.OnBottomClick,
        AdapterView.OnItemClickListener {
    ShowImageGvAdapter imageAdapter;
    String path;
    private TextView title_common;
    private TextView sendDiary;
    private EditText workSummaryContent; //工作心得
    private PopupWindow popupWindow;
    private TextView titleCancel;
    private EditText todayWorkSummaryContent; //今日总结
    private EditText tomorrowWorkSummaryContent; //明日总结
    private String diaryType = "day";
    private String scope = "all";
    private Map<String, ContactBean> sendScopeMap;//发送范围
    private Map<String, ContactBean> atPerson;
    private BottomBar bottomBar;
    private TextView sendDiary_commentPerson; //點評人
    private TextView sendDiary_sendScope; //抄送范围
    private Map<String, ContactBean> commentPersonMap; //点评人
    private ImageView titleCenterIcon;
    private LinearLayout centerTitleLayout;
    private Button sendDiaryCommentPerson;
    private GridView sendDiaryPhoto;
    private Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_senddiary);

        title_common = (TextView) id2v(R.id.title_common);
        title_common.setText(R.string.sendDiary);
//        title_common.setOnClickListener(this);

        sendDiary = (TextView) id2v(R.id.titleSend);
        sendDiary.setOnClickListener(this);

        workSummaryContent = (EditText) id2v(R.id.workSummaryContent);
        todayWorkSummaryContent = (EditText) id2v(R.id.todayWorkSummaryContent);
        tomorrowWorkSummaryContent = (EditText) id2v(R.id.tomorrowWorkSummaryContent);
        Common.setOnAtLisener(workSummaryContent, this, "@范围");
        Common.setOnAtLisener(todayWorkSummaryContent, this, "@范围");
        Common.setOnAtLisener(tomorrowWorkSummaryContent, this, "@范围");
        titleCancel = (TextView) id2v(R.id.titleCancel);
        titleCancel.setOnClickListener(this);

        bottomBar = (BottomBar) id2v(R.id.sendDiary_commonBottomBar);
        bottomBar.setOnBottomClickListener(this);


        sendDiary_sendScope = (TextView) id2v(R.id.sendDiary_sendScope);
        sendDiary_sendScope.setOnClickListener(this);

        sendDiary_commentPerson = (TextView) id2v(R.id.sendDiary_commentPerson);
        sendDiary_commentPerson.setOnClickListener(this);

        titleCenterIcon = (ImageView) id2v(R.id.titleCenterIcon);
        titleCenterIcon.setVisibility(View.VISIBLE);

        centerTitleLayout = (LinearLayout) id2v(R.id.centerTitleLayout);
        centerTitleLayout.setOnClickListener(this);

        sendDiaryCommentPerson = (Button) id2v(R.id.sendDiary_commentPerson);
        sendDiaryCommentPerson.setText("点评人");
        sendDiaryCommentPerson.setOnClickListener(this);

        sendDiaryPhoto = (GridView) id2v(R.id.sendDiaryPhoto);
        imageAdapter = new ShowImageGvAdapter(this);
        sendDiaryPhoto.setOnItemClickListener(this);
        sendDiaryPhoto.setAdapter(imageAdapter);
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
        Selection.setSelection(todayWorkSummaryContent.getText(), todayWorkSummaryContent.getText().length());
        Selection.setSelection(tomorrowWorkSummaryContent.getText(), tomorrowWorkSummaryContent.getText().length());
        Selection.setSelection(workSummaryContent.getText(), workSummaryContent.getText().length());
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleSend:
                if (workSummaryContent.getText().length() > 0 || todayWorkSummaryContent.getText().length() > 0 || tomorrowWorkSummaryContent.getText().length() > 0) {

                } else {
                    ToastTool.show("至少输入一项");
                    return;
                }
                map.put("content", String.valueOf(workSummaryContent.getText()));
                map.put("content_summary", String.valueOf(todayWorkSummaryContent.getText()));
                map.put("content_plan", String.valueOf(tomorrowWorkSummaryContent.getText()));
                map.put("log_type", diaryType);
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
                    ToastTool.show("点评人不能为空!");
                    return;
                }
                if (path != null) {
                    map.put("picturePath", path);
                    map.put("type", "postimage");
                } else {
                    map.put("type", "post");
                }
                UserController.getInstance().sendShare(map, IConstant.TYPEDIARY, new ViewCallBack());
                break;
            case R.id.centerTitleLayout:
                LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup menuView = (ViewGroup) mLayoutInflater.inflate(
                        R.layout.popupwindow_senddiary_layout, null, true);

                menuView.findViewById(R.id.sendDiary).setOnClickListener(this);
                menuView.findViewById(R.id.sendDiary_week).setOnClickListener(this);
                menuView.findViewById(R.id.sendDiary_month).setOnClickListener(this);

                popupWindow = new PopupWindow(menuView, WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.setOutsideTouchable(false);
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int yDistance = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, dm);
                popupWindow.showAtLocation(title_common, Gravity.TOP | Gravity.CENTER, 0, yDistance);
                break;
            case R.id.titleCancel:
                this.finish();
                break;
            case R.id.sendDiary:
                diaryType = "day";
                popupWindow.dismiss();
                title_common.setText(R.string.sendDiary);
                todayWorkSummaryContent.setHint(R.string.todayWorkSummaryHint);
                tomorrowWorkSummaryContent.setHint(R.string.tomorrowWorkSummaryHint);
                break;
            case R.id.sendDiary_week:
                diaryType = "week";
                popupWindow.dismiss();
                title_common.setText(R.string.sendDiary_week);
                todayWorkSummaryContent.setHint(R.string.weekWorkSummaryHint);
                tomorrowWorkSummaryContent.setHint(R.string.nextWeekWorkSummaryHint);
                break;
            case R.id.sendDiary_month:
                diaryType = "month";
                popupWindow.dismiss();
                title_common.setText(R.string.sendDiary_month);
                todayWorkSummaryContent.setHint(R.string.monthWorkSummaryHint);
                tomorrowWorkSummaryContent.setHint(R.string.nextMonthWorkSummaryHint);
                break;
            case R.id.sendDiary_sendScope: //抄送范围
                IntentTool.startSelectSendScopeActivityForResult(this,true,2);
                break;
            case R.id.sendDiary_commentPerson:
                Intent intent = new Intent(this, SelectPersonActivity.class);
                intent.putExtra("commentPerson", "选择点评人");
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
            atPerson = serializableMap.getMap();
            for (Map.Entry<String, ContactBean> entry : atPerson.entrySet()) {
                if (workSummaryContent.isFocused()) {
                    workSummaryContent.append("@" + entry.getValue().getUname() + " ");

                }
                if (todayWorkSummaryContent.isFocused()) {
                    todayWorkSummaryContent.append("@" + entry.getValue().getUname() + " ");

                }
                if (tomorrowWorkSummaryContent.isFocused()) {
                    tomorrowWorkSummaryContent.append("@" + entry.getValue().getUname() + " ");

                }
            }
        }

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
                sendDiary_sendScope.setText(stringBuilder.toString());
            } else {
                for (Map.Entry<String, ContactBean> entry : sendScopeMap.entrySet()) {
                    sendDiary_sendScope.setText(entry.getValue().getUname());
                }
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.COMMENTPERSON) {
            SerializableMap serializableMap = (SerializableMap) data.getSerializableExtra("map");
            commentPersonMap = serializableMap.getMap();
            if (commentPersonMap.size() >= 2) {
                sendDiary_commentPerson.setText(commentPersonMap.size() + "个同事");
            } else {
                for (Map.Entry<String, ContactBean> entry : commentPersonMap.entrySet()) {
                    sendDiary_commentPerson.setText(entry.getValue().getUname());

                }
            }
        }

        if (resultCode == RESULT_OK && requestCode == IConstant.SENDSCOPEOther) {
            String path = data.getStringExtra("path");
            imageAdapter.appendData(path);
            this.path = path;
            Bitmap bm =data.getParcelableExtra("data");

            if (bm!=null){
                imageAdapter.appendData(path, bm);
                CommonActionController.getInstance().upLoadPicture(new UpLoadViewCallBack(), path, this,bm);

            }else{
                imageAdapter.appendData(path);
                CommonActionController.getInstance().upLoadPicture(new UpLoadViewCallBack(), path, this);
            }
        }
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
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            SendResultModel sendResultModel = (SendResultModel) result;
            if (sendResultModel.getFeed_id() == 0) {
                ToastTool.show(getResources().getString(R.string.sendFailed));
                SendDiaryActivity.this.finish();
            } else {
//                ToastTool.show(getResources().getString(R.string.sendSuccess));
                SendDiaryActivity.this.finish();
            }
            PreferceManager.getInsance().saveValueBYkey("isRefresh", "true", SendDiaryActivity.this);
        }
    }
}
