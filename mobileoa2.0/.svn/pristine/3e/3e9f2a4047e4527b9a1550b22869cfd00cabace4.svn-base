package com.idxk.mobileoa;

import android.app.Activity;
import android.os.Bundle;

import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView.OnTitleClick;
import com.idxk.mobileoa.utils.common.android.ToastTool;


public class MyActivity extends Activity implements OnTitleClick {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_person);
        MainTitleView title = ((MainTitleView) findViewById(R.id.mainTitle));
        title.setOnTitleClickLisener(this);

    }

    @Override
    public void clickLeft() {
        ToastTool.showDev("左面");
    }

    @Override
    public void clickRight() {
        ToastTool.showDev("右面");

    }

    @Override
    public void clickCenterTitle() {

    }
}
