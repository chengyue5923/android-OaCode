package com.idxk.mobileoa.android.ui.activity;

import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.DealWithDiaryAdapter;
import com.idxk.mobileoa.android.ui.views.widget.HomeCommonTitle;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/10.
 */
public class DealWithDiaryDetailsActivity extends BaseActivity implements HomeCommonTitle.OnTitleClick, ViewNetCallBack {
    private ListView diaryDetailsListView;
    private List<DealWithDiaryListItemBean> dealWithDiaryListItemBeanList;
    private DealWithDiaryAdapter dealWithDiaryAdapter;
    private int type = 1;

    private HomeCommonTitle homeCommonTitle;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_dealwithdiarydetails);
        homeCommonTitle = (HomeCommonTitle) id2v(R.id.diaryDetails_homeCommonTitle);
        homeCommonTitle.setOnTitleClickLisener(this);

        diaryDetailsListView = (ListView) id2v(R.id.diaryDetailsListView);
        dealWithDiaryListItemBeanList = new ArrayList<DealWithDiaryListItemBean>();
        dealWithDiaryAdapter = new DealWithDiaryAdapter(this, dealWithDiaryListItemBeanList, type);
        diaryDetailsListView.setAdapter(dealWithDiaryAdapter);

    }

    @Override
    protected void initData() {
        if (getIntent() != null) {
            UserController.getInstance().getWeiboDetails(getIntent().getStringExtra("id"), this);
        }

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
        dealWithDiaryListItemBeanList = (List<DealWithDiaryListItemBean>) result;
        dealWithDiaryAdapter.notifyDataSetChanged();
    }
}
