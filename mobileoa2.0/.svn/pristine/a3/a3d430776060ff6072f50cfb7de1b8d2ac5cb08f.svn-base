package com.idxk.mobileoa.android.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.idxk.mobileoa.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

import org.apache.http.Header;

/**
 *查看图片的工具
 */
public class ShowImageToolActivity extends BaseActivity{

    ImageView imageView;
    RotateAnimation refreshingAnimation;
    LinearLayout progressLayout ;
    ImageView progressBar;
    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    @Override
    protected void initView() {
        setContentView(R.layout.activity_show_image_tool);
        imageView =(ImageView) findViewById(R.id.zoom_image_view);
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                this, R.anim.rotating);

        progressLayout = (LinearLayout) findViewById(R.id.progressLayout);
        progressBar = (ImageView)findViewById(R.id.progressBar);
    }

    @Override
    protected void initData() {
        String url = getIntent().getStringExtra("url");
        asyncHttpClient.get(url, new BinaryHttpResponseHandler() {


            @Override
            public void onStart() {
                super.onStart();
                progressLayout.setVisibility(View.VISIBLE);
                progressBar.startAnimation(refreshingAnimation);
                imageView.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] data) {
                progressLayout.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                imageView.setImageBitmap(bitmap);
                refreshingAnimation.cancel();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });


    }




}
