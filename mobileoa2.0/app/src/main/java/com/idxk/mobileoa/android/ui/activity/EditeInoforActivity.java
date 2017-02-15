package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.CircleImageView;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.model.bean.PersonBean;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.squareup.picasso.Picasso;

/**
 * 编辑资料
 */
public class EditeInoforActivity extends BaseActivity implements MainTitleView.OnTitleClick {
    MainTitleView maintitle;
    CircleImageView imageView;
    TextView nameValue, accountValue, companyValue, departmentValue, postValue;

    @Override
    protected void initData() {
        PersonBean info = (PersonBean) getIntent().getSerializableExtra("value");
        Picasso.with(this).load(info.getAvatar_original()).into(imageView);
        nameValue.setText(info.getUname());
        accountValue.setText(info.getLogin());
        companyValue.setText("中民投");
        departmentValue.setText(info.getProfile().getDepartment().getValue());
        postValue.setText(info.getProfile().getIntro().getValue());

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_edite);
        maintitle = (MainTitleView) id2v(R.id.mainTitle);
        maintitle.setOnTitleClickLisener(this);
        imageView = (CircleImageView) id2v(R.id.iv);
        nameValue = (TextView) id2v(R.id.nameValue);
        accountValue = (TextView) id2v(R.id.accountValue);
        companyValue = (TextView) id2v(R.id.companyValue);
        departmentValue = (TextView) id2v(R.id.departmentValue);
        postValue = (TextView) id2v(R.id.postValue);

    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {

    }

    @Override
    public void clickCenterTitle() {

    }

    public void layClick(View view) {
        switch (view.getId()) {
            case R.id.imageLayout:
                break;
            case R.id.editPasswordLayout:
                IntentTool.startChangePasswordActivity(this);
                break;
            case R.id.bossLayout:

                break;
        }

    }


}
