package com.idxk.mobileoa.android.ui.views.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.common.android.UITool;

/**
 * Created by lenovo on 2015/3/8.
 */
public class HomeCommonTitle extends RelativeLayout implements View.OnClickListener {
    OnTitleClick titleClick;
    private ImageView leftImage;
    private TextView titleView;
    private LinearLayout leftLayout;


    public HomeCommonTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.HomeCommonTitle);
        initView(context, mTypedArray);
    }

    private void initView(Context con, TypedArray mTypedArray) {
        View view = UITool.getView(con, R.layout.title_homecommon_layout, this);
        leftImage = (ImageView) view.findViewById(R.id.titleHomeCommonPic);
        titleView = (TextView) view.findViewById(R.id.titleHomeCommonTitle);
        leftLayout = (LinearLayout) view.findViewById(R.id.leftLayout);

        leftImage.setOnClickListener(this);
        leftLayout.setOnClickListener(this);


        titleView.setText(mTypedArray.getString(R.styleable.HomeCommonTitle_homeTitle));
        leftImage.setImageResource(mTypedArray.getResourceId(R.styleable.HomeCommonTitle_homeLeftImage, R.drawable.transparence));
    }

    public void setCenterTitle(String centerTitle) {
        titleView.setText(centerTitle);
    }

    @Override
    public void onClick(View view) {
        if (titleClick == null) {
            return;
        }


        if (view == leftImage || view == leftLayout) {

            titleClick.clickLeft();
        }
    }

    public void setOnTitleClickLisener(OnTitleClick titleClic) {
        this.titleClick = titleClic;
    }

    public interface OnTitleClick {
        void clickLeft();
    }
}
