package com.idxk.mobileoa.android.ui.views.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.exception.CommentNoDataException;
import com.idxk.mobileoa.exception.DefalutNetException;
import com.idxk.mobileoa.exception.NoDataException;
import com.idxk.mobileoa.exception.NotConnectException;
import com.idxk.mobileoa.exception.PraiseNoDataException;
import com.idxk.mobileoa.exception.TimeOutException;
import com.idxk.mobileoa.exception.WorkAlarmNoDataException;
import com.idxk.mobileoa.utils.common.android.UITool;
/**
 * Created by Wesley on 14-4-7.
 */
public class ViewListEmpty extends LinearLayout {

    TextView content;
    RelativeLayout noneLayout;
    Exception exception ;

    public ViewListEmpty(Context context, Exception exception) {
        super(context);
        this.exception = exception;
        View view = UITool.getView(context, R.layout.view_listempty, this);
        content = (TextView) view.findViewById(R.id.content);
        noneLayout = (RelativeLayout) view.findViewById(R.id.nonelayout);
        devideException();
    }

    public ViewListEmpty(Context context) {
        super(context);
        View view = UITool.getView(context, R.layout.view_listempty, this);
        content = (TextView) view.findViewById(R.id.content);
        noneLayout = (RelativeLayout) view.findViewById(R.id.nonelayout);

    }

    public ViewListEmpty(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void devideException() {
        if (exception instanceof NotConnectException) {
            content.setText("手机没有可用网络");
            return;
        }
        if (exception instanceof TimeOutException || exception instanceof DefalutNetException) {
            content.setText("网络超时了，点击重试");
            return;
        }
        if (exception instanceof NoDataException) {
            content.setText("还没有内容");
            return;
        }

        if (exception instanceof WorkAlarmNoDataException) {
            content.setText("还没有工作提醒");
            return;
        }

        if (exception instanceof CommentNoDataException) {
            content.setText("还没有人回复");
            return;
        }

        if (exception instanceof PraiseNoDataException) {
            content.setText("还没有人点赞");
            return;
        }
    }

    public void setContent(String content1) {
        content.setText(content1);
    }

    public void setNoneVisable() {
        noneLayout.setVisibility(VISIBLE);
    }


}
