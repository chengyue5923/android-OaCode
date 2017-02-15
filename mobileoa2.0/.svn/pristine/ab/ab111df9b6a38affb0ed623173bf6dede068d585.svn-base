package com.idxk.mobileoa.android.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.utils.common.android.Logger;

/**
 * Created by lenovo on 2015/3/4.
 */
public abstract class BaseActivity extends Activity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileApplication.getInstance().addActivity(this);
        try {
            initView();
            initData();
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobileApplication.getInstance().addActivity(this);
    }

    /**
     * 实例化view
     */
    protected abstract void initView();

    /**
     * 实例化相应的data
     */
    protected abstract void initData();

    /**
     * 快速实例化
     *
     * @param resId 资源的id
     * @return
     */
    protected View id2v(int resId) {
        return findViewById(resId);

    }

}
