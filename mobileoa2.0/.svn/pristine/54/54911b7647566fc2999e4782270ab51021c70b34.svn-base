package com.idxk.mobileoa.android.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.android.ui.views.adapter.FreshListAdapter;
import com.idxk.mobileoa.android.ui.views.widget.HomeCommonTitle;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/8.
 */
public class WorkSendActivity extends FragmentActivity implements HomeCommonTitle.OnTitleClick, ViewNetCallBack, AdapterView.OnItemClickListener {
    private HomeCommonTitle homeCommonTitle;

    private List<FreshListModel> freshLists;
    private FreshListAdapter freshListAdapter;
    private ListView fresh_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobileApplication.getInstance().addActivity(this);

    }

    protected void initView() {
        setContentView(R.layout.activity_worksend);
        MobileApplication.getInstance().addActivity(this);


        homeCommonTitle = (HomeCommonTitle) findViewById(R.id.workSend_homeCommonTitle);
        homeCommonTitle.setOnTitleClickLisener(this);

        fresh_listview = (ListView) findViewById(R.id.fresh_listview);
        fresh_listview.setOnItemClickListener(this);
        freshLists = new ArrayList<FreshListModel>();
        freshListAdapter = new FreshListAdapter(this, freshLists);
        fresh_listview.setAdapter(freshListAdapter);
        initData();
    }

    private void initData() {
        UserController.getInstance().getMySendWorkList(this);
    }

    @Override
    public void clickLeft() {
        this.finish();
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
    public void onData(Serializable result,boolean b,Object o) {
        freshLists = (List<FreshListModel>) result;
        freshListAdapter.setData(freshLists);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        try {
            FreshListModel freshListModel = freshLists.get(position - 1);
            IntentTool.weiboDetailsPage(this, freshListModel, freshListModel.getChannel_id());
        } catch (Exception e) {

        }
    }
}
