package com.idxk.mobileoa.android.ui.activity;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;

/**
 * 设置页面
 */
public class SetActivity extends BaseActivity implements MainTitleView.OnTitleClick {
    MainTitleView mainTitle;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_set);
        mainTitle = (MainTitleView) findViewById(R.id.mainTitle);
        mainTitle.setOnTitleClickLisener(this);
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

    }

    @Override
    public void clickCenterTitle() {

    }
}
