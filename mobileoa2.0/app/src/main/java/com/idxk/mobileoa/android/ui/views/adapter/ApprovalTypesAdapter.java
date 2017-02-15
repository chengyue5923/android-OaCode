package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.ApprovalTypesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于审批类型的适配器
 */
public class ApprovalTypesAdapter extends BaseAdapter {
    Context context;
    List<ApprovalTypesBean> list;
    private LayoutInflater mLayoutInflater;

    public ApprovalTypesAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);

    }

    public void setList(List<ApprovalTypesBean> list) {
        this.list = list;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ApprovalTypesBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_approval_types, parent, false);

        }
        TextView textView = (TextView) convertView.findViewById(R.id.title);
        textView.setText(getItem(position).getName());
        return convertView;
    }
}
