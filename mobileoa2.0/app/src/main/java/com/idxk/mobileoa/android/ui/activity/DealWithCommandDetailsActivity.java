package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.idxk.mobileoa.R;

/**
 * Created by lenovo on 2015/3/11.
 */
public class DealWithCommandDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backView;
    private TextView title;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_dealwithcommanddetails);

        backView = (ImageView) id2v(R.id.titleHomeCommonPic);
        backView.setOnClickListener(this);

        title = (TextView) id2v(R.id.titleHomeCommonTitle);
        title.setText(R.string.command);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleHomeCommonPic:
                this.finish();
                break;
        }
    }
}
