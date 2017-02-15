package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.MessageReceivedAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.exception.NoDataException;
import com.idxk.mobileoa.logic.controller.WorkAlarmController;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.model.bean.MessageReceivedListItemBean;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/6.
 */
public class MessageReceivedActivity extends BaseListViewActivity implements MainTitleView.OnTitleClick, ViewNetCallBack, AdapterView.OnItemClickListener {
    List<MessageReceivedListItemBean> listItemBeans;
    private MainTitleView maintitle;
    private ListView messageReceivedListView;
    private List<MessageReceivedListItemBean> messageReceivedLists;
    private MessageReceivedAdapter messageReceivedAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_message_received);
        maintitle = (MainTitleView) id2v(R.id.mainTitle);
        maintitle.setOnTitleClickLisener(this);
        messageReceivedListView = (ListView) id2v(R.id.messageReceivedListView);
        messageReceivedListView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        messageReceivedLists = new ArrayList<MessageReceivedListItemBean>();
        messageReceivedAdapter = new MessageReceivedAdapter(this, messageReceivedLists, IConstant.TYPERECEIVEMESSAGE);
        messageReceivedListView.setAdapter(messageReceivedAdapter);
        loadData();
    }
    void loadData(){
        WorkAlarmController.getInstance().getMessageReceived(this);
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
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {
        dealException(e,messageReceivedAdapter,messageReceivedListView);
    }

    @Override
    public void onData(Serializable result,boolean b,Object o) {
        listItemBeans = (List<MessageReceivedListItemBean>) result;
        messageReceivedAdapter.setData(listItemBeans);
        if (lvNull(messageReceivedAdapter)&&b){
            onFail(new NoDataException());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MessageReceivedListItemBean listItemBean = listItemBeans.get(position);
        FreshListModel freshListModel = new FreshListModel();
        freshListModel.setDigg_count(listItemBean.sourceInfo.getDigg_count());
        freshListModel.setFeed_id(listItemBean.row_id);
        freshListModel.setIs_digg(listItemBean.sourceInfo.is_digg);
        freshListModel.setChannel_id(listItemBean.sourceInfo.channel_id);
        IntentTool.weiboDetailsPage(this, freshListModel, listItemBean.sourceInfo.channel_id);
    }

    @Override
    public void tryAgin(View view) {
        loadData();
    }
}
