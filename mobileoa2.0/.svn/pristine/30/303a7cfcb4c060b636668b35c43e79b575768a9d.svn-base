package com.idxk.mobileoa.android.ui.activity.approval;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.activity.BaseActivity;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.android.ui.views.widget.ProgressWebView;

/**
 *
 */
public class ApprovalShowActivity extends BaseActivity implements
       MainTitleView.OnTitleClick{

    MainTitleView titleView;

    ProgressWebView pwv;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_approval_show);
        pwv = (ProgressWebView)id2v(R.id.webView);
        titleView =(MainTitleView) findViewById(R.id.appAlarm_mainTitle);
        titleView.setOnTitleClickLisener(this);
//        pwv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        pwv.getSettings().setDatabaseEnabled(false);
//        pwv.getSettings().setDomStorageEnabled(false);
//        pwv.getSettings().setAppCacheEnabled(false);
    }
    String url;
    @Override
    protected void initData() {

        url = getIntent().getStringExtra("url");
        pwv.loadUrl(url);
        titleView.setCenterTitle("审批单");

//        pwv.setWebChromeClient(pwv.new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                titleView.setCenterTitle(title);
//            }
//        });


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
