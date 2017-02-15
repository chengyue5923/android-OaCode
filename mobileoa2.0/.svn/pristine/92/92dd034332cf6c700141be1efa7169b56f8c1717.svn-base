package com.idxk.mobileoa.android.ui.activity;


import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.utils.common.android.Common;


/**
 */
public class AboutActivity extends BaseActivity implements MainTitleView.OnTitleClick {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_about);
        ((MainTitleView) id2v(R.id.appAlarm_mainTitle)).setOnTitleClickLisener(this);
        ((TextView) id2v(R.id.content)).setText("v" + Common.getManifestVisionCode(this));
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
