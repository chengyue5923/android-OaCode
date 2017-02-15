package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.PraiseReplyGridViewAdapter;
import com.idxk.mobileoa.android.ui.views.widget.HomeCommonTitle;
import com.idxk.mobileoa.model.bean.PraiseReplyGridItemBean;
import com.idxk.mobileoa.utils.common.android.ToastTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/12.
 */
public class PraiseReplyActivity extends BaseListViewActivity implements AdapterView.OnItemClickListener, HomeCommonTitle.OnTitleClick {
    private GridView praiseReplyGridView;
    private PraiseReplyGridViewAdapter praiseReplyGridViewAdapter;
    private List<PraiseReplyGridItemBean> praiseReplyGridItemBeans;
    private HomeCommonTitle homeCommonTitle;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_praisereply);
        homeCommonTitle = (HomeCommonTitle) id2v(R.id.praiseReplyTitle);
        homeCommonTitle.setOnTitleClickLisener(this);

        praiseReplyGridView = (GridView) id2v(R.id.praiseReply_gridView);
        praiseReplyGridView.setOnItemClickListener(this);


        praiseReplyGridItemBeans = new ArrayList<PraiseReplyGridItemBean>();
        PraiseReplyGridItemBean bean = new PraiseReplyGridItemBean();
        bean.personName = "text";
        praiseReplyGridItemBeans.add(bean);
        praiseReplyGridItemBeans.add(bean);
        praiseReplyGridItemBeans.add(bean);
        praiseReplyGridItemBeans.add(bean);
        praiseReplyGridItemBeans.add(bean);
        praiseReplyGridItemBeans.add(bean);

        praiseReplyGridViewAdapter = new PraiseReplyGridViewAdapter(this, praiseReplyGridItemBeans);
        praiseReplyGridView.setAdapter(praiseReplyGridViewAdapter);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ToastTool.showDev("click");
    }

    @Override
    public void clickLeft() {
        this.finish();
    }


    @Override
    public void tryAgin(View view) {

    }
}
