package com.idxk.mobileoa.android.ui.activity;

import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.PraiseReceivedAdapter;
import com.idxk.mobileoa.android.ui.views.widget.HomeCommonTitle;
import com.idxk.mobileoa.model.bean.PraiseReceivedListItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/8.
 */
public class PraiseReceivedActivity extends BaseActivity implements HomeCommonTitle.OnTitleClick {
    private ListView praiseReceivedListView;
    private List<PraiseReceivedListItemBean> praiseReceivedLists;
    private HomeCommonTitle homeCommonTitle;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_praisereceived);
        praiseReceivedListView = (ListView) id2v(R.id.praiseReceivedListView);
        homeCommonTitle = (HomeCommonTitle) id2v(R.id.praiseReceivedHomeCommonTitle);

        homeCommonTitle.setOnTitleClickLisener(this);
    }

    @Override
    protected void initData() {
        praiseReceivedLists = new ArrayList<PraiseReceivedListItemBean>();
        PraiseReceivedListItemBean freshListItemBean = new PraiseReceivedListItemBean();
        freshListItemBean.personName = "test";
        freshListItemBean.shareContent = "eeeeeeeeeeee";
        freshListItemBean.showTime = "333333333";
        praiseReceivedLists.add(freshListItemBean);
        PraiseReceivedListItemBean freshListItemBean2 = new PraiseReceivedListItemBean();
        freshListItemBean2.personName = "test2";
        praiseReceivedLists.add(freshListItemBean2);
        praiseReceivedListView.setAdapter(new PraiseReceivedAdapter(this, praiseReceivedLists));
    }

    @Override
    public void clickLeft() {
        this.finish();
    }
}
