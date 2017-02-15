package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.model.bean.DepartMentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/9.
 */
public class DepartmentSunAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> commandListItemBeans;
    private ViewHolder viewHolder;
    private Context context;

    private int type;


    public DepartmentSunAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        commandListItemBeans = new ArrayList<String>();
        this.context = context;

    }

    public void setResult(List<String> commandListItemBeans) {
        this.commandListItemBeans = commandListItemBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (commandListItemBeans != null)
            return commandListItemBeans.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return commandListItemBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.view_contact_department_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.content);
            viewHolder.pCount = (TextView) convertView.findViewById(R.id.pCount);
            viewHolder.rightRow = (ImageView) convertView.findViewById(R.id.departmentRightRow);
            viewHolder.selectPic = (ImageView) convertView.findViewById(R.id.department_ischecked);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String departMentBean = commandListItemBeans.get(position);
        viewHolder.pCount.setVisibility(View.GONE);
        viewHolder.personName.setText(departMentBean);
        return convertView;
    }


    public static final class ViewHolder {
        public TextView personName; //显示名字相关信息
        public TextView pCount; //显示名字相关信息
        public ImageView rightRow; //右侧箭头

        public ImageView selectPic;

    }
}
