package com.idxk.mobileoa.android.ui.activity.approval;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.activity.BaseActivity;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.android.ui.views.widget.ProgressWebView;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Logger;

/**
 *
 */
public class ApprovalEditeActivity extends BaseActivity implements
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
    String htmlUrl;
    int htmlId;
    @Override
    protected void initData() {
        String oauth_token = PreferceManager.getInsance().getValueBYkey(this, "oauth_token");
        String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(this, "oauth_token_secret");

        htmlUrl = getIntent().getStringExtra("url");
        htmlId = getIntent().getIntExtra("id",0);
        String url = htmlUrl+"&oauth_token="+oauth_token
                +"&oauth_token_secret="+oauth_token_secret+"&form_id="+htmlId;
        Logger.e("-url--"+url);
        pwv.loadUrl(url);

        pwv.addJavascriptInterface(new WebAppInterface(this), "Approval");

        pwv.setWebChromeClient(pwv.new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                titleView.setCenterTitle(title);
            }
        });
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
            Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();

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
