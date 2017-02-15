package com.idxk.mobileoa.android.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.WorkAlarmController;
import com.idxk.mobileoa.model.bean.MessageAlarmModel;
import com.idxk.mobileoa.model.bean.WaitForDealWithNumModel;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.mogujie.tt.imservice.entity.RecentInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lenovo on 2015/3/4.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, MainTitleView.OnTitleClick {
    private MainTitleView mainTitleView;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private AtomicInteger countNumber = new AtomicInteger(0);
    private TextView workAlarmNumber;

    private int runtimes = 0;

    private int twocount = 0;

    private ListView chatPersonListView;

    private List<RecentInfo> recentInfos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mainTitleView = (MainTitleView) view.findViewById(R.id.fragment_home_titleHome);
        mainTitleView.setOnTitleClickLisener(this);
        chatPersonListView = (ListView) view.findViewById(R.id.chatPersonListView);
        recentInfos = new ArrayList<>(1);
        workAlarmNumber = (TextView) view.findViewById(R.id.workAlarmNumber);
        view.findViewById(R.id.fragment_home_workalarmLayout).setOnClickListener(this);
        view.findViewById(R.id.fragment_home_applicationalarmLayout).setOnClickListener(this);
        freshNum();
        return view;
    }


    public void freshNum() {
        atomicInteger.set(0);
        countNumber.set(0);
        runtimes = 0;
        twocount = 0;
        Logger.e("=======================" + countNumber.toString());
        Logger.e("=======================" + atomicInteger.toString());
        WorkAlarmController.getInstance().getWaitForDealWithNumber(new getNumberViewCallBack());
        WorkAlarmController.getInstance().getMessageAlarmNumber(new getMessageAlarmViewCallBack());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_home_workalarmLayout:
                IntentTool.workAlarmPage(getActivity());
                break;
            case R.id.fragment_home_applicationalarmLayout:
                IntentTool.applicationAlarmPage(getActivity());
                break;
        }
    }

    @Override
    public void clickLeft() {

    }

    @Override
    public void clickRight() {
        IntentTool.searchPage(getActivity(), IConstant.SEARCHCOLLEAGUE);
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
        public void onData(Serializable result, boolean b, Object o) {
            if (!b) {
                return;
            }
            twocount++;
            Logger.e(">>>>>>>>>twocount>>>>>>>>>>>" + twocount);
            List<MessageAlarmModel> messageAlarmModels = (List<MessageAlarmModel>) result;
            for (MessageAlarmModel alarmModel : messageAlarmModels) {
                if (alarmModel.type.equals("atme")) {
                    countNumber.addAndGet(alarmModel.count);
                    if (atomicInteger.incrementAndGet() == 2) {
                        if (countNumber.intValue() > 0) {
                            workAlarmNumber.setText(String.valueOf(countNumber));
                            workAlarmNumber.setVisibility(View.VISIBLE);
                        } else {
                            workAlarmNumber.setVisibility(View.GONE);
                        }
                        break;
                    }
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
        public void onData(Serializable result, boolean b, Object o) {
            runtimes++;
            Logger.e(">>>>>>>>>>>>>>run times>>>>>>>>>>>>>" + runtimes);
            WaitForDealWithNumModel waitForDealWithNumModel = (WaitForDealWithNumModel) result;
            countNumber.addAndGet(waitForDealWithNumModel.approval_rel_num + waitForDealWithNumModel.order_rel_num + waitForDealWithNumModel.worklog_num);
            if (atomicInteger.incrementAndGet() == 2) {
                if (countNumber.intValue() > 0) {
                    workAlarmNumber.setText(String.valueOf(countNumber));
                    workAlarmNumber.setVisibility(View.VISIBLE);
                } else {
                    workAlarmNumber.setVisibility(View.GONE);
                }

            }
        }
    }
}
