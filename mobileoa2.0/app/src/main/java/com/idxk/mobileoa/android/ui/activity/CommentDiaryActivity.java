package com.idxk.mobileoa.android.ui.activity;

import android.widget.TextView;
import com.idxk.mobileoa.R;

/**
 * Created by lenovo on 2015/3/10.
 */
public class CommentDiaryActivity extends BaseActivity {
    private TextView commentDiaryTitle;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_commentdiary);

        commentDiaryTitle = (TextView) id2v(R.id.title_common);
        commentDiaryTitle.setText(R.string.commentDiary);
    }

    @Override
    protected void initData() {

    }
}
