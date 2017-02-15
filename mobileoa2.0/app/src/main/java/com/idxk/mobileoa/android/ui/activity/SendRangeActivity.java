package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.SendRangeAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.model.bean.SendRangesBean;


/**
 * Created by Administrator on 2015/4/1.
 */
public class SendRangeActivity extends BaseActivity implements MainTitleView.OnTitleClick {

    ListView listView;
    SendRangeAdapter dadpter;
    MainTitleView title;
    @Override
    protected void initView() {

        setContentView(R.layout.activity_sendranage);
        listView = (ListView)findViewById(R.id.items);
        dadpter = new SendRangeAdapter(this);
        listView.setAdapter(dadpter);
        title = (MainTitleView) id2v(R.id.appAlarm_mainTitle);
        title.setOnTitleClickLisener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        SendRangesBean beans = (SendRangesBean)intent.getSerializableExtra("files");
        dadpter.setRes(beans.getList());
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
