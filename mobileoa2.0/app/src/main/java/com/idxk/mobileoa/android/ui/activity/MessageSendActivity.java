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
public class MessageSendActivity extends BaseListViewActivity implements MainTitleView.OnTitleClick, ViewNetCallBack, AdapterView.OnItemClickListener {
    private MainTitleView maintitle;
    private ListView messageSendListView;
    private List<MessageReceivedListItemBean> messageSendLists;
    private MessageReceivedAdapter messageSendAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_message_send);
        maintitle = (MainTitleView) id2v(R.id.mainTitle);
        maintitle.setOnTitleClickLisener(this);
        messageSendListView = (ListView) id2v(R.id.messageSendListView);
        messageSendListView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        messageSendLists = new ArrayList<MessageReceivedListItemBean>();
        messageSendAdapter = new MessageReceivedAdapter(this, messageSendLists, IConstant.TYPESENDMESSAGE);
        messageSendListView.setAdapter(messageSendAdapter);
        loadData();
    }
    void loadData(){
        WorkAlarmController.getInstance().getMessageSend(this);
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
        dealException(e,messageSendAdapter,messageSendListView);
    }

    @Override
    public void onData(Serializable result,boolean b,Object o) {
        messageSendLists = (List<MessageReceivedListItemBean>) result;
        messageSendAdapter.setData(messageSendLists);
        if (lvNull(messageSendAdapter)&&b){
            onFail(new NoDataException());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        MessageReceivedListItemBean listItemBean = messageSendLists.get(position);
        FreshListModel freshListModel = new FreshListModel();
        freshListModel.setDigg_count(listItemBean.sourceInfo.getDigg_count());
        freshListModel.setFeed_id(listItemBean.row_id);
        freshListModel.setIs_digg(listItemBean.sourceInfo.is_digg);
        IntentTool.weiboDetailsPage(this, freshListModel, listItemBean.channelId);
    }

    @Override
    public void tryAgin(View view) {
        loadData();
    }
}
