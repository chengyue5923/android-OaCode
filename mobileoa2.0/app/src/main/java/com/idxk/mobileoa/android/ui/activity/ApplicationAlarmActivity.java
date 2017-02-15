package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.ApplicationToastAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.logic.controller.NoticeController;
import com.idxk.mobileoa.model.bean.AppToastBean;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/3/5.
 */
public class ApplicationAlarmActivity extends BaseActivity implements MainTitleView.OnTitleClick,
        AdapterView.OnItemClickListener,ViewNetCallBack {
    private MainTitleView mainTitleView;
    ListView lv;
    ApplicationToastAdapter adapter ;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_applicationalarm);
        mainTitleView = (MainTitleView) id2v(R.id.appAlarm_mainTitle);
        lv = (ListView) id2v(R.id.lv);
        mainTitleView.setOnTitleClickLisener(this);
        adapter=new ApplicationToastAdapter(this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        NoticeController.getInstance().getAppToasList(this, -1);
    }

    @Override
    protected void initData() {


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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AppToastBean bean = adapter.getItem(position);
        String url = bean.getUrl();

        String oauth_token = PreferceManager.getInsance().getValueBYkey(this, "oauth_token");
        String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(this, "oauth_token_secret");
        IntentTool.webViewPage(this,
                url
                        + "&oauth_token=" + oauth_token + "&oauth_token_secret=" + oauth_token_secret, "处理提醒",false);
//        IntentTool.webViewPage(this,url,"",false);




        NoticeController.getInstance().readedToast(new ViewNetCallBack() {
            @Override
            public void onConnectStart() {

            }

            @Override
            public void onConnectEnd() {

            }

            @Override
            public void onFail(Exception e) {

            }

            @Override
            public void onData(Serializable result, boolean fromNet, Object o) {

            }
        }, bean.getId());
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onData(Serializable result, boolean fromNet, Object o) {
        List<AppToastBean> list =(List<AppToastBean>)result;

        adapter.setRes(list);
    }
}
