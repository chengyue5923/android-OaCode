package com.idxk.mobileoa.android.ui.activity.approval;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.activity.BaseActivity;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.android.ui.views.widget.ProgressWebView;
import com.idxk.mobileoa.model.bean.ApprovalTypesBean;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.JsonTool;

/**
 *
 */
public class ApprovalInputActivity extends BaseActivity implements
       MainTitleView.OnTitleClick{

    MainTitleView titleView;
    /**
     *
     */
    ProgressWebView pwv;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_approval_show);
        pwv = (ProgressWebView)id2v(R.id.webView);
        titleView =(MainTitleView) findViewById(R.id.appAlarm_mainTitle);
        titleView.setOnTitleClickLisener(this);
        pwv.getSettings().setJavaScriptEnabled(true);
        pwv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        pwv.getSettings().setDatabaseEnabled(false);
        pwv.getSettings().setDomStorageEnabled(false);
        pwv.getSettings().setAppCacheEnabled(false);


    }
    ApprovalTypesBean bean ;
    @Override
    protected void initData() {
        String oauth_token = PreferceManager.getInsance().getValueBYkey(this, "oauth_token");
        String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(this, "oauth_token_secret");

        bean = (ApprovalTypesBean) getIntent().getSerializableExtra("type");
        String url = bean.getInput_url()+"&oauth_token="+oauth_token
                +"&oauth_token_secret="+oauth_token_secret;

        Logger.e("-url--"+url);
        pwv.loadUrl(url);
        titleView.setCenterTitle(bean.getName());
        pwv.addJavascriptInterface(new WebAppInterface(this), "Approval");
    }

    public class WebAppInterface
    {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c)
        {
            mContext = c;
        }

        /** Show a toast from the web page */

         @JavascriptInterface
        public void load(String toast)
        {
            // Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
//            Logger.e("toast="+toast);


                IntentTool.startSendExamineActivity(ApprovalInputActivity.this, JsonTool.getInt(toast,"form_id"),
                        bean.getInput_url());
                finish();


        }
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
