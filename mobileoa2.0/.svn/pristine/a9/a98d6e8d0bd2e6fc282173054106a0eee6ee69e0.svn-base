package com.idxk.mobileoa.android.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.DealWithCommandAdapter;
import com.idxk.mobileoa.exception.WorkAlarmNoDataException;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/10.
 */
public class DealWithCommandFragment extends BaseV4ListFragment implements AdapterView.OnItemClickListener, ViewNetCallBack {
    private ListView dealWithCommandListView;
    private List<DealWithDiaryListItemBean> dealWithCommandListItemBeans;
    private DealWithCommandAdapter dealWithCommandAdapter;

    private int type = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dealwithdiarycommand, container, false);
        dealWithCommandListView = (ListView) view.findViewById(R.id.dealWithCommandListView);
        dealWithCommandListView.setOnItemClickListener(this);

        dealWithCommandListItemBeans = new ArrayList<DealWithDiaryListItemBean>();
        Bundle mBundle = getArguments();
        type = mBundle.getInt("type");
        dealWithCommandAdapter = new DealWithCommandAdapter(getActivity(), dealWithCommandListItemBeans, type);
        dealWithCommandListView.setAdapter(dealWithCommandAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (type == 0) {
            UserController.getInstance().getWaitForDealWithCommandList(this);
        } else if (type == 1) {
            UserController.getInstance().getExamineList(this);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //要传id
        DealWithDiaryListItemBean listItemBean = dealWithCommandListItemBeans.get(i);
        IntentTool.weiboDetailsPage(getActivity(), listItemBean.feed_id, listItemBean.channel_id);
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {

        dealException(e,dealWithCommandAdapter,dealWithCommandListView);
    }

    @Override
    public void onData(Serializable result,boolean b,Object o) {
        dealWithCommandListItemBeans = (List<DealWithDiaryListItemBean>) result;
        dealWithCommandAdapter.setDate(dealWithCommandListItemBeans);
        if (lvNull(dealWithCommandAdapter)&&b){
            onFail(new WorkAlarmNoDataException());
        }
    }

    @Override
    public void tryAgin(View listView) {
        if (type == 0) {
            UserController.getInstance().getWaitForDealWithCommandList(this);
        } else if (type == 1) {
            UserController.getInstance().getExamineList(this);
        }
    }
}


