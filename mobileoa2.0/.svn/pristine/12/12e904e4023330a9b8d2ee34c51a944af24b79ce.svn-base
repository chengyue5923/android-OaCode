package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.utils.common.android.IntentTool;

/**
 * Created by lenovo on 2015/6/10.
 */
public class NativeFilesActivity extends BaseActivity implements MainTitleView.OnTitleClick, View.OnClickListener {
    private MainTitleView mainTitleView;
    private RelativeLayout picture_layout, music_layout, phoneMemory_layout, sdCard_layout;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_nativefiles);
        mainTitleView = (MainTitleView) id2v(R.id.nativeFiles_titleHome);
        mainTitleView.setOnTitleClickLisener(this);

        picture_layout = (RelativeLayout) id2v(R.id.picture_layout);
        picture_layout.setOnClickListener(this);

        music_layout = (RelativeLayout) id2v(R.id.music_layout);
        music_layout.setOnClickListener(this);

        phoneMemory_layout = (RelativeLayout) id2v(R.id.phoneMemory_layout);
        phoneMemory_layout.setOnClickListener(this);

        sdCard_layout = (RelativeLayout) id2v(R.id.sdCard_layout);
        sdCard_layout.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            //0为图片 1为音乐
            case R.id.picture_layout:
                IntentTool.startAlbumActivity(this, 0, getResources().getString(R.string.picture));
                break;
            case R.id.music_layout:
                IntentTool.startAlbumActivity(this, 1, getResources().getString(R.string.music));
                break;
            case R.id.phoneMemory_layout:
                IntentTool.startAlbumActivity(this, 2, getResources().getString(R.string.phoneMemory));
                break;
            case R.id.sdCard_layout:
                IntentTool.startAlbumActivity(this, 3, getResources().getString(R.string.sdCard));
                break;
        }
    }
}
