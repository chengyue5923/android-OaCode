package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.widget.ImageView;
import com.idxk.mobileoa.R;
import com.squareup.picasso.Picasso;

/**
 * Created by lenovo on 2015/3/7.
 */
public class ShowBigImageActivity extends BaseActivity {
    private ImageView shwoBigPicture;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_showbigimage);
        Intent intent = getIntent();
        shwoBigPicture = (ImageView) id2v(R.id.activity_showbigimage);
        Picasso.with(this).load(intent.getStringExtra("url")).into(shwoBigPicture);
    }

    @Override
    protected void initData() {

    }

}
