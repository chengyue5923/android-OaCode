package com.idxk.mobileoa.android.ui.activity;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.android.ui.views.widget.SubBottomWebView;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.StringTools;

/**
 * Created by lenovo on 2015/3/23.
 */
public class WebViewActivity extends BaseActivity implements MainTitleView.OnTitleClick {
    boolean showBottom;
    private SubBottomWebView webView;
    private MainTitleView mainTitleView;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_webview);

        mainTitleView = (MainTitleView) id2v(R.id.webViewMainTitle);
        mainTitleView.setOnTitleClickLisener(this);
        showBottom = getIntent().getBooleanExtra("showBottom", true);
        Logger.e("showBottom=" + showBottom);
        webView = (SubBottomWebView) id2v(R.id.webSubView);
        webView.setBottomVisiable(showBottom);
        String title = getIntent().getStringExtra("title");
        mainTitleView.setCenterTitle(StringTools.toTrim(title));
        webView.setOnWebViewLisener(new SubBottomWebView.OnWebViewLisener() {
            @Override
            public void onTitle(String title) {
                if (null != getIntent().getStringExtra("title")) {
                    mainTitleView.setCenter(getIntent().getStringExtra("title"));
                }

            }
        });


    }


    @Override

    protected void initData() {
        String url = getIntent().getStringExtra("url");
        if (StringTools.isNullOrEmpty(url)) {
            webView.loadUrlOut(getResources().getString(R.string.zhongMinTouUrl));
            return;
        }
        Logger.e("-url--" + url);
//        url="http://hr.kingdee.com/static/app/main.html?view=/assigns&appid=10022&eid=HR000010&username=B&timestamp=1436799096938&authPattern=openToken&opentoken=db9765540f55174127257f57a325595b4a6eb14a#/assigns";
        webView.loadUrl(url);
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


}
