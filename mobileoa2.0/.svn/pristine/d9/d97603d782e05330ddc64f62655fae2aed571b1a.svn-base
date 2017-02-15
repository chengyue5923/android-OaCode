package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.idxk.mobileoa.android.ui.views.widget.ViewListEmpty;
import com.idxk.mobileoa.exception.DefalutNetException;
import com.idxk.mobileoa.exception.TimeOutException;
import com.idxk.mobileoa.utils.common.android.UITool;

/**
 * 关于lietview的父类 activity
 */
public abstract class BaseListViewActivity extends BaseActivity {


    protected boolean lvNull(BaseAdapter adapter) {
        return UITool.checkListViewIsNull(adapter);
    }

    public abstract void tryAgin(View listView);

    protected void dealException(Exception e, BaseAdapter adapter, ListView listView) {
        if (!lvNull(adapter)) {
            return;
        }
        ViewListEmpty viewListEmpty = new ViewListEmpty(this, e);
        viewListEmpty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewListEmpty.setVisibility(View.GONE);
        if (e instanceof TimeOutException || e instanceof DefalutNetException) {
            viewListEmpty.setOnClickListener(new OnclickEmpty(listView));
        }
        ((ViewGroup) listView.getParent()).addView(viewListEmpty);
        listView.setEmptyView(viewListEmpty);
    }


    protected synchronized void dealException(Exception e, BaseAdapter adapter, ListView listView, boolean isShow) {
        if (!lvNull(adapter)) {
            return;
        }
        ViewListEmpty viewListEmpty = new ViewListEmpty(this, e);
        viewListEmpty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewListEmpty.setVisibility(View.GONE);
        if (e instanceof TimeOutException || e instanceof DefalutNetException) {
            viewListEmpty.setOnClickListener(new OnclickEmpty(listView));
        }

        ((ViewGroup) listView.getParent()).addView(viewListEmpty);


        if (listView.getEmptyView() == null) {
            if (isShow) {
                listView.setEmptyView(viewListEmpty);
            }
        } else {
            if (isShow) {
                listView.getEmptyView().setVisibility(View.VISIBLE);
            } else {
                listView.getEmptyView().setVisibility(View.GONE);
            }
        }

    }


    public class OnclickEmpty implements View.OnClickListener {


        ListView view;

        public OnclickEmpty(ListView view) {
            this.view = view;
        }

        @Override
        public void onClick(View v) {
            tryAgin(view);
        }
    }

}
