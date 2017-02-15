package com.idxk.mobileoa.utils.common.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class UITool {
    /**
     * 从viewGroup中获取 view
     *
     * @param mContext
     * @param res
     * @param parent
     * @return
     */
    public static View getView(Context mContext, int res, ViewGroup parent) {
        View contentView = LayoutInflater.from(mContext).inflate(res, parent);
        return contentView;
    }


    /**
     * 控制组件的显示和隐藏
     *
     * @param view   组件
     * @param isShow 是否显示
     */
    public static void setViewVisiable(View view, boolean isShow) {
        if (isShow) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }


    /**
     * 判断listview 是不是空的
     * @param adapter
     * @return
     */
    public static boolean checkListViewIsNull(BaseAdapter adapter){
        if (adapter==null){
            return true;
        }
        return adapter.getCount()==0;
    }


}
