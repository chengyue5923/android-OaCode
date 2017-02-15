package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.android.ui.fragment.ColleagueFragment;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.SerializableMap;
import com.idxk.mobileoa.utils.common.android.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2015/3/20.
 */
public class SelectPersonActivity extends FragmentActivity implements ColleagueFragment.ISelectedColleague, MainTitleView.OnTitleClick, TextView.OnEditorActionListener {

    private MainTitleView mainTitleView;

    private TextView selectScope_hasSelected;

    private Map<String, ContactBean> map;

    private int personCount = 0;

    private StringBuilder stringBuilder;

    private Intent totalIntent;

    private RelativeLayout selectScope_Layout;

    private EditText selectPersonSearch;

    private List<ContactBean> contactBeanList;

    private Handler contactHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                ColleagueFragment.adapter.setRes(contactBeanList);
            } catch (Exception E) {

            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectperson);

        mainTitleView = (MainTitleView) findViewById(R.id.selectPerson_mainTitle);
        mainTitleView.setOnTitleClickLisener(this);
        totalIntent = getIntent();
        if (totalIntent != null && totalIntent.getStringExtra("commentPerson") != null) {
            mainTitleView.setCenter(totalIntent.getStringExtra("commentPerson"));
        }

        selectScope_Layout = (RelativeLayout) findViewById(R.id.selectScope_Layout);

        selectScope_hasSelected = (TextView) findViewById(R.id.selectScope_hasSelected);

        if (String.valueOf(IConstant.TYPECOMMAND).equals(totalIntent.getStringExtra("command"))) {
            mainTitleView.showRight();
            selectScope_Layout.setVisibility(View.VISIBLE);
        }

//        selectPersonSearch = (EditText) findViewById(R.id.selectPersonSearch);
//        selectPersonSearch.setOnEditorActionListener(this);
//        selectPersonSearch.addTextChangedListener(new Wather());


        map = new HashMap<String, ContactBean>();
        stringBuilder = new StringBuilder();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.content_Layout,
                new ColleagueFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobileApplication.getInstance().addActivity(this);

    }

    @Override
    public void freshSelectedColleague(ContactBean contactBean, String position) {
        if (totalIntent != null && String.valueOf(IConstant.TYPECOMMAND).equals(totalIntent.getStringExtra("command"))) {
            stringBuilder.setLength(0);
            Logger.e(contactBean.getUname() + ">>>>>>>>>>" + contactBean.getUid() + "<>>>>>>>>>>>>>>>>>" + position);
            if (contactBean.isChecked) {
                map.put(String.valueOf(position), contactBean);
                personCount++;
            } else {
                map.remove(String.valueOf(position));
                personCount--;
            }

            if (personCount == 1) {
                for (Map.Entry<String, ContactBean> entry : map.entrySet()) {
                    stringBuilder.append(entry.getValue().getUname());
                }
            }

            if (personCount > 1) {
                stringBuilder.append(personCount + "个同事");
            }
            selectScope_hasSelected.setText(getResources().getString(R.string.hasSelected) + stringBuilder.toString());
        } else {
            map.put(String.valueOf(position), contactBean);
            SerializableMap myMap = new SerializableMap();
            myMap.setMap(map);
            Intent intent = new Intent();
            intent.putExtra("map", myMap);
            setResult(RESULT_OK, intent);
            this.finish();
        }
    }

    @Override
    public void clickLeft() {
        this.finish();
    }

    @Override
    public void clickRight() {
        SerializableMap myMap = new SerializableMap();
        myMap.setMap(map);
        Intent intent = new Intent();
        intent.putExtra("map", myMap);
        setResult(RESULT_OK, intent);
        this.finish();
    }

    @Override
    public void clickCenterTitle() {

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        loadData(textView.getText().toString());
        return false;
    }

    private void loadData(final String name) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                contactBeanList = ContactController.getInstance().getConstacts(name);
                contactHandler.sendEmptyMessage(0);
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

//    public class Wather implements TextWatcher {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            String string = s.toString();
//            loadData(string);
//
//
//        }
//    }


}
