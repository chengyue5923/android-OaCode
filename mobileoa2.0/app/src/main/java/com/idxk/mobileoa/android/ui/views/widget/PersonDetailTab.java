package com.idxk.mobileoa.android.ui.views.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.UITool;

/**
 * 对业务定制的view的开发 个人主页面的 tab view
 */
public class PersonDetailTab extends LinearLayout implements View.OnClickListener {
    public static final int ALL = 0;
    public static final int FEEDBACK = 1;
    public static final int COLLECT = 2;
    public static final int MY = 3;
    //--点击事件
    View[] layouts;
    Context context;
    OnLayoutClick onIntemClick;

    public PersonDetailTab(Context context) {
        super(context);


    }

    public PersonDetailTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();

    }

    private void initView() {
        View view = UITool.getView(context, R.layout.view_person_tab, this);
        layouts = new View[]{view.findViewById(R.id.allLayout),
                view.findViewById(R.id.feedBackLayout),
                view.findViewById(R.id.collectLayout),
                view.findViewById(R.id.myLayout)};
        for (View v : layouts) {
            v.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allLayout:
                setCurrentByIndex(ALL);
                break;
            case R.id.feedBackLayout:
                setCurrentByIndex(FEEDBACK);
                break;
            case R.id.collectLayout:
                setCurrentByIndex(COLLECT);
                break;
            case R.id.myLayout:
                setCurrentByIndex(MY);
                break;
        }

    }

    public void setCurrentByIndex(int index) {
        for (View view : layouts) {
            reStore(view);
        }
        try {
            setCurrent(layouts[index]);
        } catch (Exception e) {
            Logger.e("设置的索引在layouts中不存在 " + index);
        }
        onIntemClick.layoutClick(index);

    }

    /**
     * 回复正常
     *
     * @param view
     */
    private void reStore(View view) {

    }

    /**
     * 设置当前的
     *
     * @param view
     */
    private void setCurrent(View view) {

    }

    public void setOnIntemClick(OnLayoutClick onIntemClick) {
        this.onIntemClick = onIntemClick;
    }

    /**
     *
     */
    public interface OnLayoutClick {
        void layoutClick(int index);
    }
}
