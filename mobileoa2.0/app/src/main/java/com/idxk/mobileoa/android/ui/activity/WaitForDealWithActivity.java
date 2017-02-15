package com.idxk.mobileoa.android.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.android.ui.views.adapter.TabPageIndicatorAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.android.ui.views.widget.ThreeTabsView;
import com.idxk.mobileoa.logic.controller.WorkAlarmController;
import com.idxk.mobileoa.model.bean.WaitForDealWithNumModel;
import com.idxk.mobileoa.utils.common.android.LogUtils;

import java.io.Serializable;

/**
 * Created by lenovo on 2015/3/8.
 */
public class WaitForDealWithActivity extends FragmentActivity
        implements MainTitleView.OnTitleClick, ThreeTabsView.OnChanageTabLisener, ViewPager.OnPageChangeListener {
    private MainTitleView homeCommonTitle;
    private ThreeTabsView indicator;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobileApplication.getInstance().addActivity(this);

    }

    protected void initView() {
        setContentView(R.layout.activity_waitfordealwith);
        MobileApplication.getInstance().addActivity(this);


        homeCommonTitle = (MainTitleView) findViewById(R.id.dealWith_homeCommonTitle);
        homeCommonTitle.setOnTitleClickLisener(this);

        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager(), getApplicationContext(), 0);
        pager = (ViewPager) findViewById(R.id.dealWith_pager);
        pager.setAdapter(adapter);

        //实例化TabPageIndicator然后设置ViewPager与之关联
        indicator = (ThreeTabsView) findViewById(R.id.dealWith_indicator);
        indicator.setLisener(this);
        pager.setOnPageChangeListener(this);
        initData();
    }

    private void initData() {
        WorkAlarmController.getInstance().getWaitForDealWithNumber(new ViewNetCallBackResult());
    }


    @Override
    public void clickLeft() {
        this.finish();
    }

    @Override
    public void clickRight() {

    }

    @Override
    public void clickCenterTitle() {

    }

    @Override
    public int getCurrentTabIndex(int dex) {
        pager.setCurrentItem(dex);
        return dex;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        indicator.setCurrent(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    class ViewNetCallBackResult implements com.idxk.mobileoa.utils.net.callback.ViewNetCallBack {

        @Override
        public void onConnectStart() {
        }

        @Override
        public void onConnectEnd() {
        }

        @Override
        public void onFail(Exception e) {
            e.printStackTrace();
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            WaitForDealWithNumModel waitForDealWithNumModel = (WaitForDealWithNumModel) result;
            LogUtils.e(waitForDealWithNumModel.toString());


        }
    }
}
