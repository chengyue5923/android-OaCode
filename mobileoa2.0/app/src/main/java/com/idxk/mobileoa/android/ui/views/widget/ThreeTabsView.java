package com.idxk.mobileoa.android.ui.views.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.common.android.UITool;

/**
 * tab的tab
 */
public class ThreeTabsView extends LinearLayout implements View.OnClickListener{
    LinearLayout fristLayout,sencondLayout,thridLayout;


    int backNormalColor,textNomalColor,textOnColor,driverColor;
    ImageView driverFrist,driverSend,driverThrid;
    TextView  fristTv,secondTv,thridTv;
    public ThreeTabsView(Context context) {
        super(context);
    }

    public ThreeTabsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view =  UITool.getView(context, R.layout.view_three_tabs,this);
        fristLayout = (LinearLayout)view.findViewById(R.id.fristLayout);
        sencondLayout = (LinearLayout)view.findViewById(R.id.sencondLayout);
        thridLayout = (LinearLayout)view.findViewById(R.id.thridLayout);
        fristLayout.setOnClickListener(this);
        sencondLayout.setOnClickListener(this);
        thridLayout.setOnClickListener(this);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.ThreeTabsView);
        backNormalColor = getResources().getColor(mTypedArray.getColor(R.styleable.ThreeTabsView_BackGroudNormal,R.color.white));
        textNomalColor = getResources().getColor(mTypedArray.getColor(R.styleable.ThreeTabsView_TextColorOff,R.color.tabTextNormal));
        textOnColor = getResources().getColor(mTypedArray.getColor(R.styleable.ThreeTabsView_TextColorOff,R.color.tab_text_on_color));
        driverColor = getResources().getColor(mTypedArray.getColor(R.styleable.ThreeTabsView_TextColorOff,R.color.tab_text_on_color));
        setBackgroundColor(backNormalColor);
        driverFrist= (ImageView)view.findViewById(R.id.driverFrist);
        driverSend= (ImageView)view.findViewById(R.id.driverSend);
        driverThrid= (ImageView)view.findViewById(R.id.driverThrid);

        driverFrist.setBackgroundColor(driverColor);
        driverSend.setBackgroundColor(driverColor);
        driverThrid.setBackgroundColor(driverColor);

        fristTv = (TextView)view.findViewById(R.id.fristTv);
        secondTv = (TextView)view.findViewById(R.id.secondTv);
        thridTv = (TextView)view.findViewById(R.id.thridTv);
        fristTv.setText(mTypedArray.getString(R.styleable.ThreeTabsView_titleFrist));
        secondTv.setText(mTypedArray.getString(R.styleable.ThreeTabsView_titleSecond));
        thridTv.setText(mTypedArray.getString(R.styleable.ThreeTabsView_titleThrid));

        setCurrent(0);

    }

    public ThreeTabsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.fristLayout:
                    setCurrent(0);
                    break;
                case R.id.sencondLayout:
                    setCurrent(1);
                    break;
                case R.id.thridLayout:
                    setCurrent(2);
                    break;
            }
    }

    public interface OnChanageTabLisener{
        int getCurrentTabIndex(int dex);
    }

    OnChanageTabLisener lisener;

    public OnChanageTabLisener getLisener() {
        return lisener;
    }

    public void setLisener(OnChanageTabLisener lisener) {
        this.lisener = lisener;
    }


    private void setDefault(){
        driverFrist.setVisibility(GONE);
        driverSend.setVisibility(GONE);
        driverThrid.setVisibility(GONE);
        fristTv.setTextColor(textNomalColor);
        secondTv.setTextColor(textNomalColor);
        thridTv.setTextColor(textNomalColor);

    }

    public void setCurrent(int index){
        setDefault();
        if (lisener!=null){
            lisener.getCurrentTabIndex(index);
        }
        switch (index){
            case 0:
                fristTv.setTextColor(textOnColor);
                driverFrist.setVisibility(VISIBLE);
            break;
            case 1:
                secondTv.setTextColor(textOnColor);
                driverSend.setVisibility(VISIBLE);
                break;
            case 2:
                thridTv.setTextColor(textOnColor);
                driverThrid.setVisibility(VISIBLE);
                break;
        }

    }
}
