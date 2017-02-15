package com.idxk.mobileoa.android.ui.views.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Wesley on 14-4-3.
 */
public class Sgrideview extends GridView {

    public Sgrideview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Sgrideview(Context context) {
        super(context);
    }

    public Sgrideview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
