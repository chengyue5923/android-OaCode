package com.idxk.mobileoa.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;

/**
 * 编辑 个人个性签名
 */
public class EditeUserSignature extends BaseActivity implements MainTitleView.OnTitleClick,ViewNetCallBack{

    EditText signture_content;
    MainTitleView mainTitleView;
    @Override
    protected void initView() {
     setContentView(R.layout.activity_edit_signature);
        signture_content =(EditText) findViewById(R.id.signture_content);
        mainTitleView =(MainTitleView) findViewById(R.id.mainTitleView);
        mainTitleView.setOnTitleClickLisener(this);
    }

    @Override
    protected void initData() {

        String content = getIntent().getStringExtra("content");
        signture_content.setText(StringTools.toTrim(content));
    }


    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {

        //--保存

        if (inflate()){
            UserController.getInstance().editeUserInfor(signture_content.getText().toString(),this);
        }

    }

    private boolean inflate(){
       String   infor= StringTools.toTrim(signture_content.getText().toString());
       if (StringTools.isNullOrEmpty(infor)){
           ToastTool.show("您的签名不能为空！");
            return false;
       }
        return true;


    }
    @Override
    public void clickCenterTitle() {
    }
    @Override
    public void onConnectStart() {
    }

    @Override
    public void onConnectEnd() {
    }

    @Override
    public void onFail(Exception e) {
    }

    @Override
    public void onData(Serializable result, boolean fromNet, Object o) {
        Intent intent =  new  Intent();
        intent.putExtra("content",signture_content.getText().toString());
        setResult( Activity.RESULT_OK,intent);
        finish();
    }
}
