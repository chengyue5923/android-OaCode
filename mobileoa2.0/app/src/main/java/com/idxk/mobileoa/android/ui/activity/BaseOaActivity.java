package com.idxk.mobileoa.android.ui.activity;

import android.os.Bundle;

import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;

/**
 * Created by admin on 2017/2/13.
 */

public abstract class BaseOaActivity extends BaseActivity implements ViewNetCallBack {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
        initEvent();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onConnectStart() {

    }
    protected abstract void initEvent();
    protected abstract int getLayoutId();
    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onData(Serializable result, boolean fromNet, Object o) {

    }
}
