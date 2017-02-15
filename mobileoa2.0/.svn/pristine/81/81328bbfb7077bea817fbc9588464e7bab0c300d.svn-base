package com.idxk.mobileoa.android.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.DealWithDiaryAdapter;
import com.idxk.mobileoa.exception.WorkAlarmNoDataException;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/9.
 */
public class DealWithDiaryFragment extends BaseV4ListFragment implements AdapterView.OnItemClickListener, ViewNetCallBack {
    private ListView dealWithDiaryListView;
    private List<DealWithDiaryListItemBean> dealWithDiaryListItemBeans;
    private DealWithDiaryAdapter dealWithDiaryAdapter;

    private FreshListModel freshListModel = new FreshListModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dealwithdiary, container, false);

        dealWithDiaryListView = (ListView) view.findViewById(R.id.dealWithDiary_listView);
        dealWithDiaryListView.setOnItemClickListener(this);
        dealWithDiaryListItemBeans = new ArrayList<DealWithDiaryListItemBean>();
        dealWithDiaryAdapter = new DealWithDiaryAdapter(getActivity(), dealWithDiaryListItemBeans, 0);
        dealWithDiaryListView.setAdapter(dealWithDiaryAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        UserController.getInstance().getWaitForDealWithDiaryList(this, 0);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        DealWithDiaryListItemBean listItemBean = dealWithDiaryListItemBeans.get(position);
        freshListModel.setDigg_count(listItemBean.digg_count);
        freshListModel.setIs_digg(listItemBean.is_digg);
        freshListModel.setFeed_id(listItemBean.feed_id);
        freshListModel.setUid(listItemBean.uid);
        IntentTool.weiboDetailsPage(getActivity(), freshListModel, listItemBean.channel_id);
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {
        dealException(e,dealWithDiaryAdapter,dealWithDiaryListView);

    }

    @Override
    public void onData(Serializable result,boolean b,Object o) {
        dealWithDiaryListItemBeans = (List<DealWithDiaryListItemBean>) result;
        dealWithDiaryAdapter.setData(dealWithDiaryListItemBeans);
        if (lvNull(dealWithDiaryAdapter)&&b){
            onFail(new WorkAlarmNoDataException());
        }
    }

    @Override
    public void tryAgin(View listView) {
        UserController.getInstance().getWaitForDealWithDiaryList(this, 0);

    }
}
