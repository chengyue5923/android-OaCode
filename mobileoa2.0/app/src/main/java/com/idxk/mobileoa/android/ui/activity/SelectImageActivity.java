package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.StringTools;

import java.io.File;

/**
 * Created by Administrator on 2015/3/26.
 */
public class SelectImageActivity extends BaseActivity {


    boolean cut = false;
    private String path;
    String ordinalPath;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_select_image);
        cut = getIntent().getBooleanExtra("cut", false);
         ordinalPath=getIntent().getStringExtra("imagePaths");
        if (!StringTools.isNullOrEmpty(ordinalPath)){
            findViewById(R.id.showImageBt).setVisibility(View.VISIBLE);
           findViewById(R.id.showImageBt).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Logger.e("ordinal="+ordinalPath);
                   IntentTool.startImageToolShowActivity(SelectImageActivity.this, ordinalPath);
                   finish();
               }
           });
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    public void caramClick(View view) {
        path = Common.takePhoto1(this, IConstant.CAMERA);
    }


    public void albumClick(View view) {
        path = Common.ambluePhoto(this, IConstant.CHOOSEPICTURE);
    }


    public void cancleClick(View view) {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IConstant.CHOOSEPICTURE) {
            Uri uri = data.getData();
            if (cut) {
                try {
                    Common.startPhotoZoom(this, uri, IConstant.CutRescode);
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage(), e);
                }
                return;
            }
            if (data != null) {
                if (uri != null) {
                    Logger.e("-------" + uri);
                    String[] imgs = {MediaStore.Images.Media.DATA};//将图片URI转换成存储路径
                    try {
                        Cursor cursor = this.managedQuery(uri, imgs, null, null, null);
                        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        String paths = cursor.getString(index);
                        Logger.e("-----path---" + paths);
                        Intent intent = new Intent();
                        intent.putExtra("path", paths);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (Exception e) {
                    } finally {
                    }

                }
            }


        }

        if (resultCode == RESULT_OK && requestCode == IConstant.CAMERA) {
            if (cut) {
                try {
                    File file = new File(path);
                    Uri uri = Uri.fromFile(file);
                    Common.startPhotoZoom(this, uri, IConstant.CutRescode);
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage(), e);
                }
                return;
            }
            Intent intent = data;
            intent.putExtra("path", path);
            setResult(RESULT_OK, intent);
            finish();
        }


        if (resultCode == RESULT_OK && requestCode == IConstant.CutRescode) {
            try {
                String cutPath = Common.saveBm(data);
                Logger.e("-----cutPath---" + cutPath);
                Intent intent = new Intent();
                intent.putExtra("path", cutPath);
                setResult(RESULT_OK, intent);
                finish();
            } catch (Exception e) {

            }


        }


    }
}
