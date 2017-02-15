package com.idxk.mobileoa.android.ui.views.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.common.android.IntentTool;

/**
 *
 */
public class WriteReplyDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    private Button agreeBtn;
    private Button disagreeBtn;
    private Button replyAgainBtn;
    private Button dialog_cancelBtn;
    private String id;

    private Object object;

    public WriteReplyDialog(Context context, String id, Object tag) {
        super(context, R.style.writeReplyTheme);
        this.activity = (Activity) context;
        this.id = id;
        this.object = tag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        init();
        initView();
        initListener();
    }

    private void init() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(lp);
    }

    private void initView() {
        agreeBtn = (Button) findViewById(R.id.dialog_agree);
        disagreeBtn = (Button) findViewById(R.id.dialog_disagree);
        replyAgainBtn = (Button) findViewById(R.id.dialog_replyAgain);
        dialog_cancelBtn = (Button) findViewById(R.id.dialog_cancel);
    }

    private void initListener() {
        agreeBtn.setOnClickListener(this);
        disagreeBtn.setOnClickListener(this);
        replyAgainBtn.setOnClickListener(this);
        dialog_cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_agree:
                IntentTool.replyPage(activity, 1, object);
                this.dismiss();
                break;
            case R.id.dialog_cancel:
                this.dismiss();
                break;
            case R.id.dialog_replyAgain:
                IntentTool.replyPage(activity, 3, object);
                this.dismiss();
                break;
            case R.id.dialog_disagree:
                IntentTool.replyPage(activity, 2, object);
                this.dismiss();
                break;
            default:
                break;
        }
    }
}
