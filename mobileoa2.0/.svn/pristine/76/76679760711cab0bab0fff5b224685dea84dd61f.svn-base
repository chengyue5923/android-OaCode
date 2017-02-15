package com.idxk.mobileoa.android.ui.views.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.common.android.UITool;
import com.idxk.mobileoa.utils.common.java.StringTools;

/**
 * 系统title
 */
public class MainTitleView extends RelativeLayout implements OnClickListener {
    //-- 左右两个bt  的实例化 中心的title 实例化
    ImageView leftIv, rightIv, trigon;
    TextView titleTv, rightTv;
    OnTitleClick titleClick;
    LinearLayout leftLayout;
    FrameLayout rightLayout;

    private LinearLayout centerLayout;

    public MainTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.MainTitleView);

        initView(context, mTypedArray);
    }

    public MainTitleView(Context context) {
        super(context);


    }


    public View getLeftView() {
        return leftIv;
    }


    public void setCenter(String center) {
        titleTv.setText(center);
    }

    public void showRight() {
        rightTv.setVisibility(View.VISIBLE);
    }

    public void setRightTv(String text) {
        rightTv.setText(text);
    }


    /**
     * 对组建的实例化
     *
     * @param con
     */

    private void initView(Context con, TypedArray mTypedArray) {
        View view = UITool.getView(con, R.layout.view_main_title, this);
        leftIv = (ImageView) view.findViewById(R.id.mainTitleleftBt);
        rightIv = (ImageView) view.findViewById(R.id.mainTitRighttBt);
        leftLayout = (LinearLayout) view.findViewById(R.id.leftLayout);
        rightLayout = (FrameLayout) view.findViewById(R.id.rightLayout);
        trigon = (ImageView) view.findViewById(R.id.trigon);
        titleTv = (TextView) view.findViewById(R.id.mainCenTitleTv);
        rightTv = (TextView) view.findViewById(R.id.mainTitRightTv);
        centerLayout = (LinearLayout) view.findViewById(R.id.centerTitleLayout);

//        leftIv.setOnClickListener(this);
//        rightIv.setOnClickListener(this);
//        rightTv.setOnClickListener(this);
        leftLayout.setOnClickListener(this);
//        rightLayout.setOnClickListener(this);

        UITool.setViewVisiable(leftIv, mTypedArray.getBoolean(R.styleable.MainTitleView_showLeft, false));
        UITool.setViewVisiable(leftLayout, mTypedArray.getBoolean(R.styleable.MainTitleView_showLeft, false));
        UITool.setViewVisiable(rightLayout, mTypedArray.getBoolean(R.styleable.MainTitleView_showRight, false));
        UITool.setViewVisiable(rightIv, mTypedArray.getBoolean(R.styleable.MainTitleView_showRight, false));
        UITool.setViewVisiable(titleTv, mTypedArray.getBoolean(R.styleable.MainTitleView_showTitle, true));
        UITool.setViewVisiable(rightTv, mTypedArray.getBoolean(R.styleable.MainTitleView_showRightText, false));
        UITool.setViewVisiable(trigon, mTypedArray.getBoolean(R.styleable.MainTitleView_showTrigon, false));
        UITool.setViewVisiable(this, mTypedArray.getBoolean(R.styleable.MainTitleView_showSelf, true));
        String right = StringTools.toTrim(mTypedArray.getString(R.styleable.MainTitleView_rightText));
        rightTv.setText(StringTools.isNullOrEmpty(right) ? "确定" : right);
        titleTv.setText(mTypedArray.getString(R.styleable.MainTitleView_titleCenter));
        leftIv.setImageResource(mTypedArray.getResourceId(R.styleable.MainTitleView_leftImage, R.drawable.transparence));


        if (mTypedArray.getBoolean(R.styleable.MainTitleView_showRight, false)) {
            rightLayout.setOnClickListener(this);
        }

        if (rightTv.getVisibility() == View.VISIBLE) {
            rightIv.setVisibility(View.GONE);
        } else {
            final int rightNormal = mTypedArray.getResourceId(R.styleable.MainTitleView_rightImage,
                    R.drawable.transparence);
            rightIv.setImageResource(rightNormal);
        }

        final int leftNormal = mTypedArray.getResourceId(R.styleable.MainTitleView_leftImage,
                R.drawable.transparence);
        leftIv.setImageResource(leftNormal);

        if (trigon.getVisibility() == View.VISIBLE) {
            centerLayout.setOnClickListener(this);
        } else {
            titleTv.setOnClickListener(this);
        }
        mTypedArray.recycle();
    }

    public void setCenterTitle(String centerTitle) {
        titleTv.setText(centerTitle);
    }

    public void onClick(View view) {
        if (titleClick == null) {
            return;
        }

        if (view == leftIv || view == leftLayout) {
            titleClick.clickLeft();
            return;

        }
        if (view == rightIv || view == rightLayout) {
            titleClick.clickRight();
            return;
        }

        if (view == rightTv) {
            titleClick.clickRight();
            return;
        }
        if (view == titleTv) {
            titleClick.clickCenterTitle();
            return;
        }

        if (view == centerLayout) {
            titleClick.clickCenterTitle();
            return;
        }


    }


    public void setOnTitleClickLisener(OnTitleClick titleClic) {
        this.titleClick = titleClic;
    }

    /**
     * 关于 title 左右的点击事件
     *
     * @author linxi
     */
    public interface OnTitleClick {
        void clickLeft();

        void clickRight();

        void clickCenterTitle();

    }


}
