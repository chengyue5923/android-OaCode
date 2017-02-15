package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.ShareScopeAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.PersonBean;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/12.
 */
public class ShareScopeActivity extends BaseListViewActivity implements MainTitleView.OnTitleClick, AdapterView.OnItemClickListener, ViewNetCallBack {
    private MainTitleView homeCommonTitle;
    private ListView shareScopeListView;
    private ShareScopeAdapter shareScopeAdapter;
    private List<PersonBean> shareScopeListItemBeans;

    private Intent intent = null;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sharescope);
        homeCommonTitle = (MainTitleView) id2v(R.id.shareScopeTitle);
        homeCommonTitle.setOnTitleClickLisener(this);

        shareScopeListView = (ListView) id2v(R.id.shareScopeListView);
        shareScopeListView.setOnItemClickListener(this);

        shareScopeListItemBeans = new ArrayList<PersonBean>();


//        shareScopeAdapter = new ShareScopeAdapter(this, shareScopeListItemBeans);
//        shareScopeListView.setAdapter(shareScopeAdapter);
    }

    @Override
    protected void initData() {
        intent = getIntent();
        if (intent != null && intent.getStringExtra("scopeString") != null) {
            String scopeString = intent.getStringExtra("scopeString").trim();
            String personId[] = scopeString.split(",");
            for (String string : personId) {
                if (string.equals("all")) {
                    PersonBean peronBean = new PersonBean();
                    peronBean.setUname("全公司");
                    shareScopeListItemBeans.add(peronBean);
                    shareScopeAdapter.notifyDataSetChanged();
                    continue;
                }
                UserController.getInstance().getPersonInfor(this, Integer.valueOf(string));
            }
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
        PersonBean peronBean = (PersonBean) result;
        shareScopeListItemBeans.add(peronBean);
        shareScopeAdapter.notifyDataSetChanged();
    }

    @Override
    public void tryAgin(View view) {

    }
}
