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
public class DepartmentAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<DepartMentBean> commandListItemBeans;
    private ViewHolder viewHolder;
    private Context context;

    private int type;


    public DepartmentAdapter(Context context, int type) {
        mLayoutInflater = LayoutInflater.from(context);
        commandListItemBeans = new ArrayList<DepartMentBean>();
        this.context = context;
        this.type = type;
    }

    public void setResult(List<DepartMentBean> commandListItemBeans) {
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

        DepartMentBean departMentBean = commandListItemBeans.get(position);
        if (departMentBean.getpCount() == 0) {
            viewHolder.pCount.setVisibility(View.GONE);
        } else {
            viewHolder.pCount.setVisibility(View.VISIBLE);
            viewHolder.pCount.setText(departMentBean.getpCount() + "人");
        }

        viewHolder.personName.setText(departMentBean.getName());

        if (type == IConstant.CONTACTDEPARTMENT) {
            viewHolder.selectPic.setVisibility(View.GONE);
            viewHolder.rightRow.setVisibility(View.VISIBLE);
        } else if (type == IConstant.SCOPEDEPARTMENT) {
            viewHolder.selectPic.setVisibility(View.VISIBLE);
            viewHolder.rightRow.setVisibility(View.GONE);

            if (departMentBean.isChecked) {
                viewHolder.selectPic.setImageResource(R.drawable.btn_check_sel);
            } else {
                viewHolder.selectPic.setImageResource(R.drawable.btn_check_nor);
            }
        }
        return convertView;
    }


    public static final class ViewHolder {
        public TextView personName; //显示名字相关信息
        public TextView pCount; //显示名字相关信息
        public ImageView rightRow; //右侧箭头

        public ImageView selectPic;

    }
}
