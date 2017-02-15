package com.idxk.mobileoa.android.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.ChatPersonAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.NoticeController;
import com.idxk.mobileoa.logic.controller.WorkAlarmController;
import com.idxk.mobileoa.model.bean.AppToastBean;
import com.idxk.mobileoa.model.bean.MessageAlarmModel;
import com.idxk.mobileoa.model.bean.WaitForDealWithNumModel;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.mogujie.tt.imservice.entity.RecentInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lenovo on 2015/3/4.
 */
public abstract class HomeNewFragment extends Fragment implements View.OnClickListener, MainTitleView.OnTitleClick, AdapterView.OnItemClickListener {

    protected ChatPersonAdapter chatPersonAdapter;
    TextView appAlarmNumber;
    private MainTitleView mainTitleView;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private AtomicInteger countNumber = new AtomicInteger(0);
    private TextView workAlarmNumber;
    private int runtimes = 0;
    private int twocount = 0;
    private ListView chatPersonListView;
    private List<RecentInfo> recentInfos;
    private ScrollView scrollView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        scrollView = (ScrollView) view.findViewById(R.id.homeScrollView);
        mainTitleView = (MainTitleView) view.findViewById(R.id.fragment_home_titleHome);
        mainTitleView.setOnTitleClickLisener(this);
        chatPersonListView = (ListView) view.findViewById(R.id.chatPersonListView);
        chatPersonListView.setOnItemClickListener(this);
        recentInfos = new ArrayList<>(1);
        chatPersonAdapter = new ChatPersonAdapter(getActivity(), recentInfos);
        chatPersonListView.setAdapter(chatPersonAdapter);
        workAlarmNumber = (TextView) view.findViewById(R.id.workAlarmNumber);
        appAlarmNumber = (TextView) view.findViewById(R.id.appAlarmNumber);
        view.findViewById(R.id.fragment_home_workalarmLayout).setOnClickListener(this);
        view.findViewById(R.id.fragment_home_applicationalarmLayout).setOnClickListener(this);
//        freshNum();

        return view;
    }


    public void freshNum() {
        atomicInteger.set(0);
        countNumber.set(0);
        runtimes = 0;
        twocount = 0;
        WorkAlarmController.getInstance().getWaitForDealWithNumber(new getNumberViewCallBack());
        WorkAlarmController.getInstance().getMessageAlarmNumber(new getMessageAlarmViewCallBack());
        NoticeController.getInstance().getAppToasList(new AppToastCallBack(), 0);
    }


    public void upScrollView() {
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        }, 100);
    }

    @Override
    public void onResume() {
        super.onResume();
        freshNum();
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        RecentInfo recentInfo = chatPersonAdapter.getItem(i);
        itemCLick(recentInfo);
    }

    public abstract void itemCLick(RecentInfo recentInfo);


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

    private class AppToastCallBack implements ViewNetCallBack {

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

            List<AppToastBean> r = (List<AppToastBean>) result;

            if (ListUtil.isNullOrEmpty(r)) {

                appAlarmNumber.setVisibility(View.GONE);

            } else {
                appAlarmNumber.setVisibility(View.VISIBLE);
                appAlarmNumber.setText(r.size() + "");
            }
        }
    }
}
