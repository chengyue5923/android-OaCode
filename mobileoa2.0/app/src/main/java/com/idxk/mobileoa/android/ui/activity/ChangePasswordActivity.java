package com.idxk.mobileoa.android.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;

/**
 * Created by Wesley on 2015/3/13.
 */
public class ChangePasswordActivity extends BaseActivity implements MainTitleView.OnTitleClick, ViewNetCallBack {
    EditText affirmPwEdit, oldPwEdit, newPwEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_changepassword);
        newPwEdit = (EditText) id2v(R.id.newPwEdit);
        affirmPwEdit = (EditText) id2v(R.id.affirmPwEdit);
        oldPwEdit = (EditText) id2v(R.id.oldPwEdit);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void clickRight() {
        if (canPass()) {
            UserController.getInstance().changePassword(this);
        }

    }

    @Override
    public void clickCenterTitle() {

    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {
        //todo 失败
        ToastTool.show(getResources().getString(R.string.change_password_fail));
    }

    @Override
    public void onData(Serializable result,boolean b,Object o) {
        //todo 成功
    }

    private boolean canPass() {
        if (StringTools.isNullOrEmpty(oldPwEdit.getText().toString())) {
            ToastTool.show(getResources().getString(R.string.change_password_old_empty));
            return false;
        }
        if (StringTools.isNullOrEmpty(newPwEdit.getText().toString())) {
            ToastTool.show(getResources().getString(R.string.change_password_new_empty));
            return false;
        }
        if (StringTools.isNullOrEmpty(affirmPwEdit.getText().toString())) {
            ToastTool.show(getResources().getString(R.string.change_password_affirm_empty));
            return false;
        }
        if (!StringTools.isEqual(newPwEdit.getText().toString(), affirmPwEdit.getText().toString())) {
            ToastTool.show(getResources().getString(R.string.change_password_affirm_new_notEqual));
            return false;
        }
        return true;
    }
}
