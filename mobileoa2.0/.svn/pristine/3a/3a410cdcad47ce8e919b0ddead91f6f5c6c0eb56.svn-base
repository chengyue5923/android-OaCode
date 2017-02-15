package com.idxk.mobileoa.android.ui.fragment;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.idxk.mobileoa.android.ui.views.widget.ViewListEmpty;
import com.idxk.mobileoa.exception.DefalutNetException;
import com.idxk.mobileoa.exception.TimeOutException;
import com.idxk.mobileoa.utils.common.android.UITool;

/**
 * Created by Administrator on 2015/4/10.
 */
public abstract class BaseListFragment extends Fragment {
    protected boolean lvNull(BaseAdapter adapter) {
        return UITool.checkListViewIsNull(adapter);
    }

    public abstract void tryAgin(View listView);

    protected void dealException(Exception e, BaseAdapter adapter, ListView listView) {
        if (!lvNull(adapter)) {
            return;
        }
        ViewListEmpty viewListEmpty = new ViewListEmpty(getActivity(), e);
        viewListEmpty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewListEmpty.setVisibility(View.GONE);
        if (e instanceof TimeOutException || e instanceof DefalutNetException) {
            viewListEmpty.setOnClickListener(new OnclickEmpty(listView));
        }
        ((ViewGroup) listView.getParent()).addView(viewListEmpty);
        listView.setEmptyView(viewListEmpty);
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
