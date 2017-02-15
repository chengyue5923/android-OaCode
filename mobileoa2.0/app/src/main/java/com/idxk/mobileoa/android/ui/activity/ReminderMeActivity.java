package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.FreshListAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.exception.NoDataException;
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
public class ReminderMeActivity extends BaseListViewActivity implements MainTitleView.OnTitleClick, AdapterView.OnItemClickListener, ViewNetCallBack {
    private ListView reminderMeListView;
    private List<FreshListModel> reminderMeListItemBeans;
    private MainTitleView homeCommonTitle;
    private FreshListAdapter reminderMeAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_reminderme);
        reminderMeListView = (ListView) id2v(R.id.reminderMeListView);
        homeCommonTitle = (MainTitleView) id2v(R.id.remindMeHomeCommonTitle);
        homeCommonTitle.setOnTitleClickLisener(this);
        reminderMeListView.setOnItemClickListener(this);
    }


    @Override
    protected void initData() {
        reminderMeListItemBeans = new ArrayList<FreshListModel>();
        reminderMeAdapter = new FreshListAdapter(this, reminderMeListItemBeans);
        reminderMeListView.setAdapter(reminderMeAdapter);
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
    protected void onResume() {
        super.onResume();
        loadData();
    }

    void loadData() {
        UserController.getInstance().remindMeWeiBo(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        FreshListModel remindMeListModel = reminderMeListItemBeans.get(position);
        FreshListModel freshListModel = new FreshListModel();
        freshListModel.setFeed_id(remindMeListModel.getFeed_id());
        freshListModel.setDigg_count(remindMeListModel.getDigg_count());
        freshListModel.setIs_digg(remindMeListModel.getIs_digg());
        freshListModel.setChannel_id(remindMeListModel.getChannel_id());
        IntentTool.weiboDetailsPage(this, freshListModel, remindMeListModel.getChannel_id());
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {
        dealException(e, reminderMeAdapter, reminderMeListView);
    }

    @Override
    public void onData(Serializable result, boolean b, Object o) {
        reminderMeListItemBeans = (List<FreshListModel>) result;
        reminderMeAdapter.setData(reminderMeListItemBeans);
        if (lvNull(reminderMeAdapter) && b) {
            onFail(new NoDataException());
        }
    }

    @Override
    public void tryAgin(View view) {
        loadData();
    }

}
