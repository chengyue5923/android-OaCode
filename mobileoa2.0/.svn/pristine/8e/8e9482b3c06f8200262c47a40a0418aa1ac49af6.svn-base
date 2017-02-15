package com.idxk.mobileoa.android.ui.activity.approval;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.activity.BaseActivity;
import com.idxk.mobileoa.android.ui.views.adapter.ApprovalTypesAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.logic.controller.ApprovalController;
import com.idxk.mobileoa.model.bean.ApprovalTypesBean;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class ApprovalTypesActivity extends BaseActivity implements
        AdapterView.OnItemClickListener,ViewNetCallBack,MainTitleView.OnTitleClick {
    ApprovalTypesAdapter adapter;
    MainTitleView appAlarm_mainTitle;
    /**
     *
     */
    ListView lv;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_approval_types);
        adapter = new ApprovalTypesAdapter(this);
        appAlarm_mainTitle = (MainTitleView)findViewById(R.id.appAlarm_mainTitle);
        appAlarm_mainTitle.setOnTitleClickLisener(this);
        lv = (ListView)id2v(R.id.lv);
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        ApprovalTypesBean bean = (ApprovalTypesBean) getIntent().getSerializableExtra("bean");
        if (bean==null){
            ApprovalController.getInstance().getApprovalType(this);
            return;

        }

        List<ApprovalTypesBean> list =bean.getList();
        adapter.setList(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!ListUtil.isNullOrEmpty(adapter.getItem(position).getList())){
            IntentTool.startApprovalTypesActivity(this,adapter.getItem(position));
            finish();
            return;
        }

        if (adapter.getItem(position).getId()==-1){
            IntentTool.startSendExamineActivity(this);
            finish();
            return;
        }
        IntentTool.startApprovalInput(this, adapter.getItem(position));
        finish();
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
    public void onData(Serializable result, boolean fromNet, Object o) {

        Logger.e("object=="+o.toString());
        List<ApprovalTypesBean> list = (List<ApprovalTypesBean>)result;
        ApprovalTypesBean bean =  new ApprovalTypesBean();
        bean.setId(-1);
        bean.setName("普通审批单");
        list.add(0, bean);
        adapter.setList(list);
    }

    @Override
    public void clickLeft() {
    finish();
    }

    @Override
    public void clickRight() {

    }

    @Override
    public void clickCenterTitle() {

    }
}
