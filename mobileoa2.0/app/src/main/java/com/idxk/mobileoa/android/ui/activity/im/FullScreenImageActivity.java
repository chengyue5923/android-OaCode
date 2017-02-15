package com.idxk.mobileoa.android.ui.activity.im;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.im.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/8/4.
 */
public class FullScreenImageActivity extends Activity implements View.OnTouchListener {
    @Bind(R.id.image)
    ImageView image;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_full_image);
        ButterKnife.bind(this);
        image.setOnTouchListener(this);
        url = getIntent().getStringExtra("url");
        ImageLoader.getInstance(this, R.drawable.default_error).displayImage(url, image);
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        finish();
        return true;
    }

}
