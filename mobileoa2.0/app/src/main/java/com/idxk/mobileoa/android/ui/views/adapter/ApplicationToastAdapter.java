package com.idxk.mobileoa.android.ui.views.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.AppToastBean;

import java.util.ArrayList;
import java.util.List;

/**
 * app提醒的列表
 */
public class ApplicationToastAdapter extends BaseAdapter{

    Activity context;

    List<AppToastBean> beans;


    public ApplicationToastAdapter(Activity context) {
        this.context = context;
        beans = new ArrayList<>();
    }
    public void setRes(List<AppToastBean> beans){
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public AppToastBean getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  context.getLayoutInflater().inflate(R.layout.adapter_application_toast_item,null);
        TextView content = (TextView) view.findViewById(R.id.content);
        AppToastBean bean =  getItem(position);
        content.setText(bean.getShortContent());
        if (bean.getStatus()==0){
            content.setTextColor(Color.BLACK);
        }else{
            content.setTextColor(Color.GRAY);
        }
        return view;
    }
}
