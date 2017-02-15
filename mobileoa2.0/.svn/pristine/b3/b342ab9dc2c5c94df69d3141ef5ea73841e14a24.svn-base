package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.android.ui.fragment.ColleagueFragment;
import com.idxk.mobileoa.android.ui.views.adapter.TabPageIndicatorSelectAdapter;
import com.idxk.mobileoa.android.ui.views.widget.ThreeTabsView;
import com.idxk.mobileoa.logic.controller.CachController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.SerializableMap;
import com.idxk.mobileoa.utils.common.android.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 选择范围
 */
public class SelectSendScopeActivity extends FragmentActivity implements OnClickListener,
        ColleagueFragment.ISelectedColleague, ThreeTabsView.OnChanageTabLisener, ViewPager.OnPageChangeListener {
    private ThreeTabsView indicator;
    private ViewPager pager;
    private TextView titleCenter;
    private TextView titleCancel;
    private RelativeLayout selectScope_Layout;
    private TextView selectScope_hasSelected;
    private Map<String, ContactBean> map;
    private TextView titleSend;

    private int personCount = 0;

    private int departmentCount = 0;

    private StringBuilder stringBuilder = null;

    private int hasAll = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileApplication.getInstance().addActivity(this);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobileApplication.getInstance().addActivity(this);

    }

    protected void initView() {
        setContentView(R.layout.activity_selectsendscope);


        indicator = (ThreeTabsView) findViewById(R.id.selectSendScope_indicator);
        pager = (ViewPager) findViewById(R.id.selectSendScope_pager);

        boolean sendRange = getIntent().getBooleanExtra("sendRange",false);
        int type = getIntent().getIntExtra("type",0);


        TabPageIndicatorSelectAdapter adapter = new TabPageIndicatorSelectAdapter(getSupportFragmentManager(),
                getApplicationContext(), 0);
        adapter.setSendRange(sendRange);
        adapter.setSendType(type);

        pager.setAdapter(adapter);
        indicator.setLisener(this);
        pager.setOnPageChangeListener(this);


        titleCenter = (TextView) findViewById(R.id.title_common);
        if (getIntent() != null && getIntent().getStringExtra("atScope") != null) {
            titleCenter.setText(getIntent().getStringExtra("atScope"));
            indicator.setCurrent(1);
        } else {
            titleCenter.setText("选择范围");
        }

        titleCancel = (TextView) findViewById(R.id.titleCancel);
        titleCancel.setOnClickListener(this);


        selectScope_Layout = (RelativeLayout) findViewById(R.id.selectScope_Layout);
        selectScope_Layout.setOnClickListener(this);

        selectScope_hasSelected = (TextView) findViewById(R.id.selectScope_hasSelected);

        titleSend = (TextView) findViewById(R.id.titleSend);
        titleSend.setText(R.string.sure);
        titleSend.setOnClickListener(this);


    }

    protected void initData() {
        map = new HashMap<String, ContactBean>();
        stringBuilder = new StringBuilder();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleCancel:
                this.finish();
                break;
            case R.id.selectScope_Layout:
                break;
            case R.id.titleSend:
                SerializableMap myMap = new SerializableMap();
                myMap.setMap(map);
                CachController.getInstance().saveReCach(map);
                Intent intent = new Intent();
                intent.putExtra("map", myMap);
                setResult(RESULT_OK, intent);
                this.finish();
                break;
        }
    }

    @Override
    public void freshSelectedColleague(ContactBean contactBean, String position) {
        Logger.e(contactBean.getUname() + ">>>>>>>>>>" + contactBean.getUid() + "<>>>>>>>>>>>>>>>>>" + position);
        stringBuilder.setLength(0);
        if (contactBean.isChecked) {
            if (contactBean.isDepartment) {
                map.put("d_" + position, contactBean);
                departmentCount++;
            } else {
                if (contactBean.getUid().startsWith("all")) {
                    map.put("all", contactBean);
                    hasAll++;
                } else {
                    map.put(position, contactBean);
                    personCount++;
                }

            }

        } else {
            if (contactBean.isDepartment) {
                map.remove("d_" + position);
                departmentCount--;
            } else {
                if (contactBean.getUid().startsWith("all")) {
                    map.remove("all");
                    hasAll--;
                } else {
                    map.remove(position);
                    personCount--;
                }
            }
        }

        if (departmentCount == 1) {
            for (Map.Entry<String, ContactBean> entry : map.entrySet()) {
                if (entry.getKey().startsWith("d_")) {
                    stringBuilder.append(entry.getValue().getUname());
                }
            }
        } else if (departmentCount > 1) {
            stringBuilder.append(departmentCount + "个部门");
        }

        if (personCount == 1) {
            for (Map.Entry<String, ContactBean> entry : map.entrySet()) {
                if (!entry.getKey().startsWith("d_")) {
                    stringBuilder.append(entry.getValue().getUname());
                }
            }
        } else if (personCount > 1) {
            stringBuilder.append(personCount + "个同事");
        }


        if (hasAll == 1) {
            if (!stringBuilder.toString().contains("全公司")) {
                stringBuilder.append("全公司");
            }
        }

        selectScope_hasSelected.setText(getResources().getString(R.string.hasSelected) + stringBuilder.toString());



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


}
