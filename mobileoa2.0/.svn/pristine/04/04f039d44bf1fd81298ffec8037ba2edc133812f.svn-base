package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.logic.controller.WorkAlarmController;
import com.idxk.mobileoa.model.bean.MessageAlarmModel;
import com.idxk.mobileoa.model.bean.WaitForDealWithNumModel;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/3/5.
 */
public class WorkAlarmActivity extends BaseActivity implements View.OnClickListener, MainTitleView.OnTitleClick {
    private RelativeLayout receivedMessageLayout; //我收到的回复
    private RelativeLayout sendMessageLayout; //我发出的回复
    private RelativeLayout praiseReceivedLayout; //我收到的赞
    private RelativeLayout reminderMeLayout; //提到我的
    private RelativeLayout workAlarm_waitForDealWithLayout;
    private RelativeLayout sendWorkLayout;
    private MainTitleView mainTitleView;
    private TextView waitForDealNumber;
    private TextView workAlarm_atMeNumber;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_workalarm);

        receivedMessageLayout = (RelativeLayout) id2v(R.id.receivedMessageLayout);
        receivedMessageLayout.setOnClickListener(this);

        sendMessageLayout = (RelativeLayout) id2v(R.id.sendMessageLayout);
        sendMessageLayout.setOnClickListener(this);

        praiseReceivedLayout = (RelativeLayout) id2v(R.id.praiseReceivedLayout);
        praiseReceivedLayout.setOnClickListener(this);

        reminderMeLayout = (RelativeLayout) id2v(R.id.reminderMeLayout);
        reminderMeLayout.setOnClickListener(this);

        workAlarm_waitForDealWithLayout = (RelativeLayout) id2v(R.id.workAlarm_waitForDealWithLayout);
        workAlarm_waitForDealWithLayout.setOnClickListener(this);

        sendWorkLayout = (RelativeLayout) id2v(R.id.workAlarm_sendWorkLayout);
        sendWorkLayout.setOnClickListener(this);

        mainTitleView = (MainTitleView) id2v(R.id.workAlarm_titleView);
        mainTitleView.setOnTitleClickLisener(this);

        waitForDealNumber = (TextView) id2v(R.id.waitForDealNumber);

        workAlarm_atMeNumber = (TextView) id2v(R.id.workAlarm_atMeNumber);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        WorkAlarmController.getInstance().getWaitForDealWithNumber(new getNumberViewCallBack());
        WorkAlarmController.getInstance().getMessageAlarmNumber(new getMessageAlarmViewCallBack());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.receivedMessageLayout:
                IntentTool.messageReceivedPage(this);
                break;
            case R.id.sendMessageLayout:
                IntentTool.messageSendPage(this);
                break;
            case R.id.praiseReceivedLayout:
                IntentTool.praiseReceivedPage(this);
                break;
            case R.id.reminderMeLayout:
                IntentTool.reminderMePage(this);
                break;
            case R.id.workAlarm_waitForDealWithLayout:
                IntentTool.waitForDealWithPage(this);
                break;
            case R.id.workAlarm_sendWorkLayout:
                IntentTool.sendWorkPage(this);
                break;
            default:
                break;
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

    private class getMessageAlarmViewCallBack implements ViewNetCallBack {

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
        public void onData(Serializable result,boolean b,Object o) {
            List<MessageAlarmModel> messageAlarmModels = (List<MessageAlarmModel>) result;
            for (MessageAlarmModel alarmModel : messageAlarmModels) {
                if (alarmModel.type.equals("atme")) {
                    if (alarmModel.count > 0) {
                        workAlarm_atMeNumber.setText(String.valueOf(alarmModel.count));
                        workAlarm_atMeNumber.setVisibility(View.VISIBLE);
                    } else {
                        workAlarm_atMeNumber.setVisibility(View.GONE);
                    }
                    break;
                }
            }
        }
    }

    private class getNumberViewCallBack implements ViewNetCallBack {

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
        public void onData(Serializable result,boolean b,Object o) {
            WaitForDealWithNumModel waitForDealWithNumModel = (WaitForDealWithNumModel) result;
            int totalNumber = waitForDealWithNumModel.approval_rel_num + waitForDealWithNumModel.order_rel_num + waitForDealWithNumModel.worklog_num;
            if (totalNumber > 0) {
                waitForDealNumber.setText(String.valueOf(totalNumber));
                waitForDealNumber.setVisibility(View.VISIBLE);
            } else {
                waitForDealNumber.setVisibility(View.GONE);
            }

        }
    }
}
