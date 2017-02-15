package com.idxk.mobileoa.android.ui.activity;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.android.ui.views.widget.PersonDetailTab;
import com.idxk.mobileoa.utils.common.android.ToastTool;

/**
 * 个人主页面的activity
 */
public class PersonInforActivity extends BaseActivity implements MainTitleView.OnTitleClick, PersonDetailTab.OnLayoutClick {
    MainTitleView maintitle;
    PersonDetailTab tab;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_person_infor);
        maintitle = (MainTitleView) id2v(R.id.mainTitle);
        maintitle.setOnTitleClickLisener(this);
        tab = (PersonDetailTab) id2v(R.id.tab);
        tab.setOnIntemClick(this);

        tab.setCurrentByIndex(PersonDetailTab.ALL);

    }

    @Override
    protected void initData() {


    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {
        ToastTool.showDev("查找");
    }

    @Override
    public void clickCenterTitle() {

    }


    @Override
    public void layoutClick(int index) {
        ToastTool.showDev("" + index);

    }
}
