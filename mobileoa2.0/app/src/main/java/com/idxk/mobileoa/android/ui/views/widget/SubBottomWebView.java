package com.idxk.mobileoa.android.ui.views.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.config.enums.FileType;
import com.idxk.mobileoa.utils.common.android.FileTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.UITool;
import com.idxk.mobileoa.utils.common.java.MD5Util;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.FileDownListener;
import com.idxk.mobileoa.utils.net.connect.http.file.DownFileWithNoticeBarThread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 带有底部栏的
 */
public class SubBottomWebView extends RelativeLayout implements View.OnClickListener{

    ImageView ivBack,ivNextg,ivRresh;
    ProgressWebView webView;
    Context context;
    View error_layout;
    OnWebViewLisener onWebViewLisener;
    LinearLayout bottomLayout;
    private WebViewClient webviewClient = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    public SubBottomWebView(Context context) {
        super(context);
        initView(context);
    }

    public SubBottomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SubBottomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }



    public void initView(Context context){
        this.context = context;
        View view = UITool.getView(context, R.layout.view_subwebview, this);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ivNextg = (ImageView) view.findViewById(R.id.ivNextg);
        ivRresh = (ImageView) view.findViewById(R.id.ivRresh);
        error_layout =  view.findViewById(R.id.error_layout);
        webView = (ProgressWebView) view.findViewById(R.id.webView);
        bottomLayout = (LinearLayout)view.findViewById(R.id.bottomLayout);
        webView.setLisener(new ProgressWebView.OnLoadEndLisener() {

            @Override
            public void onFinish() {
                changeControlSrc();
            }
        });
        initOnlickLinener();

    }
    public void setBottomVisiable(boolean show){
        bottomLayout.setVisibility(show ? VISIBLE : INVISIBLE);
    }

    private void changeControlSrc() {
        if (webView.canGoBack()) {
            ivBack.setImageResource(R.drawable.back);
        } else {
            ivBack.setImageResource(R.drawable.back_no);
        }
        if (webView.canGoForward()) {
            ivNextg.setImageResource(R.drawable.next);

        } else {
            ivNextg.setImageResource(R.drawable.next_no);
        }


    }

    public void initOnlickLinener() {
        ivBack.setOnClickListener(this);
        ivNextg.setOnClickListener(this);
        ivRresh.setOnClickListener(this);
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键
                        webView.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

        webView.setWebChromeClient(webView.new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                onWebViewLisener.onTitle(title);
            }
        });


        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // 设置点击网页里面的链接还是在当前的webview里跳转
////                view.loadUrl(url);
//                return true;
//            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, android.net.http.SslError error) {
                handler.proceed();
            }

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                error_layout.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                WebResourceResponse response = null;
                if (url.contains("hr-logo")) {
                    try {
                        InputStream localCopy = context.getAssets().open("replace_bg.png");
                        response = new WebResourceResponse("image/png", "UTF-8", localCopy);
                    } catch (IOException e) {
                        Logger.e(e.getLocalizedMessage(),e);
                    }
                }
                return response;
            }
        });

        webView.setDownloadListener(new FileWebViewDownLoadListener());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                webView.goBack();
                break;
            case R.id.ivNextg:
                webView.goForward();
                break;
            case R.id.ivRresh:
                webView.reload();
                break;
        }
    }

    /**
     * 跳转
     * @param url
     */
    public void loadUrlOut(String url){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(webviewClient);
        webView.setDownloadListener(new WebViewDownLoadListener(context));
        webView.loadUrl(url);
    }

    /**
     * 不跳转的家在
     * @param url
     */
    public void loadUrl(String url){
        webView.loadUrl(url);
    }

    public void setOnWebViewLisener(OnWebViewLisener onWebViewLisener) {
        this.onWebViewLisener = onWebViewLisener;
    }


    public interface OnWebViewLisener {
        void onTitle(String title);
    }

    private class WebViewDownLoadListener implements DownloadListener {
        Context context;
        private WebViewDownLoadListener( Context context) {
            this.context = context;

        }

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }

    private class FileWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Logger.e("-url-- "+url);
            Logger.e("-url--userAgent "+userAgent);
            Logger.e("-url--contentDisposition "+contentDisposition);
            Logger.e("-url--mimetype "+mimetype);
            Logger.e("-url--contentLength "+contentLength);

            String fileName="";

            if (url.endsWith("apk")){

                fileName= MD5Util.string2MD5(url)+".apk";
            }else{
                try {
                    String[] splits = contentDisposition.split("filename=");
                    if (splits==null||splits.length==1){
                        fileName="temp";
                    }else{
                        fileName = splits[splits.length-1];
                    }
                }catch (Exception e){
                    Logger.e(e.getLocalizedMessage(),e);
                }
            }

            String localPath= FileTool.getInstance().getPathByType(fileName, FileType.office);
            DownFileWithNoticeBarThread thread = new DownFileWithNoticeBarThread(context);
            thread.setDownListener(new FileDownListener() {
                @Override
                public void starDownLoad() {

                }

                @Override
                public void endDownLoad(File file, boolean isSuess) {
                    if (isSuess&&file.getName().endsWith("apk")){
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");//向用户显示数据
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//以新压入栈
                        intent.addCategory("android.intent.category.DEFAULT");
                        Uri fileUri = Uri.fromFile(file);
                        intent.setDataAndType(fileUri,
                                "application/vnd.android.package-archive");
                        context.startActivity(intent);
                    }
                }
            });
            thread.execute(url, StringTools.toTrim(localPath));
        }
    }






}
