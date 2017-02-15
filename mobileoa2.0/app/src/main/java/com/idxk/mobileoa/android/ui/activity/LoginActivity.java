package com.idxk.mobileoa.android.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.LoginModel;
import com.idxk.mobileoa.utils.cache.db.achieve.UserPreference;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;

/**
 * Created by lenovo on 2015/3/4.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private InputMethodManager imm;
    private TextWatcher textWatcher;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        context = getApplicationContext();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        usernameEditText = (EditText) findViewById(R.id.username_EditText);
        usernameEditText.setText("");
        passwordEditText = (EditText) findViewById(R.id.password_EditText);
        passwordEditText.setText("");
        loginButton = (Button) findViewById(R.id.login_Button);
        findViewById(R.id.registBt).setOnClickListener(this);
        findViewById(R.id.findBt).setOnClickListener(this);
        findViewById(R.id.allLayout).setOnClickListener(this);
        initListener();
        loginButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.drawable.login_bt_bg_click);
                } else {
                    v.setBackgroundResource(R.drawable.login_bt_bg);
                }
                return false;
            }
        });

    }

    private void initListener() {
        loginButton.setOnClickListener(this);
        textWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (usernameEditText.getText() != null
                        && passwordEditText.getText() != null
                        && usernameEditText.getText().toString().length() > 0
                        && passwordEditText.getText().toString().length() > 0) {
                    loginButton.setTextColor(getResources().getColor(
                            R.color.white));
                } else {
                    loginButton.setTextColor(getResources().getColor(
                            R.color.edittext_noselected));
                }
            }
        };

        usernameEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setEditText();
    }

    private void setEditText() {
        String username = PreferceManager.getInsance().getValueBYkey(context, "loginUserName");
        String password = PreferceManager.getInsance().getValueBYkey(context, "loginPassword");

        if (null != username && !username.equals("")) {
            usernameEditText.setText(username);
            Selection.setSelection(usernameEditText.getText(), username.length());
        }

        if (null != password && !password.equals("")) {
            passwordEditText.setText(password);
            Logger.e(password);
            passwordEditText.requestFocus();
            Selection.setSelection(passwordEditText.getText(), password.length());
        }

    }

    private void getLogin() {
        String username = usernameEditText.getText().toString();
        if (username.equals("")) {
            ToastTool.show(getResources().getString(R.string.userHasNoValue));
            return;
        }


        String password = passwordEditText.getText().toString();
        if (password.equals("")) {
            ToastTool.show(getResources().getString(R.string.pwdHasNoValue));
            return;
        }
        imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);
        PreferceManager.getInsance().saveValueBYkey("loginUserName", username, context);
        PreferceManager.getInsance().saveValueBYkey("loginPassword", password, context);
        UserController.getInstance().login(username, password, new ViewCallBack());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_ImageButton:
                this.finish();
                break;
            case R.id.login_Button:
                getLogin();
                break;
            case R.id.registBt:
                ToastTool.showDev("注册");
                break;
            case R.id.findBt:
                ToastTool.showDev("找回密码");
                break;
            case R.id.allLayout:
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);


                break;
            default:
                break;
        }

    }

    //    Dialog commitDialog;
    @Override
    protected void initData() {
//        PushManager.stopWork(this);
    }

    /**
     * 页面函数回调
     */
    public class ViewCallBack implements ViewNetCallBack {
        @Override
        public void onConnectStart() {
//            if (!commitDialog.isShowing()){
//                commitDialog.show();
//            }

        }

        @Override
        public void onConnectEnd() {
//            if (commitDialog.isShowing()){
//                commitDialog.dismiss();
//            }
        }

        @Override
        public void onFail(Exception e) {
//            ToastTool.show(getResources().getString(R.string.loginFaile));
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            LoginModel loginModel = (LoginModel) result;
            if ("00001".equals(loginModel.getCode())) {
                ToastTool.show(StringTools.isNullOrEmpty(loginModel.getMessage()) ? getResources().getString(R.string.loginFaile) : loginModel.getMessage());
                return;
            }
            UserPreference.setTOKEN(loginModel.getOauth_token());
            UserPreference.setID(Integer.parseInt(loginModel.getUid()));
            PreferceManager.getInsance().saveValueBYkey("oauth_token", loginModel.getOauth_token(), context);
            PreferceManager.getInsance().saveValueBYkey("oauth_token_secret", loginModel.getOauth_token_secret(), context);
            PreferceManager.getInsance().saveValueBYkey("uid", loginModel.getUid(), context);
            PreferceManager.getInsance().saveValueBYkey("loginUserName", "", context);
            PreferceManager.getInsance().saveValueBYkey("loginPassword", "", context);
            ToastTool.show(getResources().getString(R.string.loginSuccess));
            IntentTool.homePage(LoginActivity.this);
            LoginActivity.this.finish();
        }

    }


}
