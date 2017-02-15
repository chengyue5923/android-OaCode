package com.idxk.mobileoa.android.ui.views.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.common.android.UITool;

/**
 * Created by lenovo on 2015/3/21.
 */
public class BottomBar extends LinearLayout implements View.OnClickListener {

    private OnBottomClick onBottomClick;
    private ImageView photo;
    private ImageView attach;
    private ImageView atPerson;

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context con) {
        View view = UITool.getView(con, R.layout.bottombar_common_layout, this);
        photo = (ImageView) view.findViewById(R.id.activity_sendshare_addPicture);
        attach = (ImageView) view.findViewById(R.id.activity_sendshare_addattachment);
        atPerson = (ImageView) view.findViewById(R.id.fresh_colleague);

        photo.setOnClickListener(this);
        attach.setOnClickListener(this);
        atPerson.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (onBottomClick == null) {
            return;
        }

        if (view == photo) {
            onBottomClick.clickLeft();
        }

        if (view == attach) {
            onBottomClick.clickMiddle();
        }

        if (view == atPerson) {
            onBottomClick.clickRight();
        }
    }


    public void setOnBottomClickListener(OnBottomClick titleClick) {
        this.onBottomClick = titleClick;
    }

    public void setItemVisible(int index) {
        switch (index) {
            case 0:
                photo.setVisibility(VISIBLE);
                break;
            case 1:
                attach.setVisibility(VISIBLE);
                break;
            case 2:
                atPerson.setVisibility(VISIBLE);
                break;

        }
    }

    public void setItemGone(int index) {

        switch (index) {
            case 0:
                photo.setVisibility(GONE);
                break;
            case 1:
                attach.setVisibility(GONE);
                break;
            case 2:
                atPerson.setVisibility(GONE);
                break;

        }
    }

    public interface OnBottomClick {
        void clickLeft();

        void clickMiddle();

        void clickRight();
    }
}
