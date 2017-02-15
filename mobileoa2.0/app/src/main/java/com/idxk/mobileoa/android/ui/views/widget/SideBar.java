package com.idxk.mobileoa.android.ui.views.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.idxk.mobileoa.utils.common.java.ListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SideBar extends View {
    OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    List<String> b = new ArrayList<String>();
    int choose = -1;
    Paint paint = new Paint();
    boolean showBkg = true;

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#b9b2b0"));
        int singleHeight;
        int height = getHeight();
        int width = getWidth();
        if (ListUtil.isNullOrEmpty(b)) {
            singleHeight = 0;
        } else {
            singleHeight = height / b.size();
        }


//        Rect rect = new Rect(0,0,width,height);
//        canvas.clipRect(rect);


        for (int i = 0; i < b.size(); i++) {
            paint.setColor(Color.WHITE);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            if (i == choose) {
                paint.setColor(Color.parseColor("#000000"));
                paint.setFakeBoldText(true);
            }
            float xPos = width / 2 - paint.measureText(b.get(i)) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b.get(i), xPos, yPos, paint);
            paint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        int c = ((int) (y / getHeight() * b.size()));
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBkg = true;
                if (oldChoose != c && listener != null) {

                    if (c < b.size()) {
                        listener.onTouchingLetterChanged(b.get(c));
                        choose = c;
                        invalidate();
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null) {
                    if (c < b.size()) {
                        listener.onTouchingLetterChanged(b.get(c));
                        choose = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBkg = true;
                choose = -1;
                invalidate();
                break;
        }
        return true;
    }

    public void setStringArray(String[] stringArray) {
        b = Arrays.asList(stringArray);
        invalidate();
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }
}